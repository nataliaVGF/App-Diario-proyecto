package mx.edu.utng.appdiario.ui.screens.auth.RecuperacionContrasena

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.Repository.UsuarioRepository
import mx.edu.utng.appdiario.ui.screens.auth.AutoEmailSender

// ✅ ELIMINADO: Estados de Gmail (ya no los necesitamos)
data class PasswordRecoveryState(
    val email: String = "",
    val verificationCode: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val successMessage: String = ""
    // ✅ ELIMINADO: gmailAccount y requiresGmailAuth
)

class PasswordRecoveryViewModel(
    private val usuarioRepository: UsuarioRepository,
    private val recoveryCodeManager: RecoveryCodeManager,
    private val autoEmailSender: AutoEmailSender // ✅ CAMBIADO: Usar AutoEmailSender
) : ViewModel() {

    private val _uiState = MutableStateFlow(PasswordRecoveryState())
    val uiState: StateFlow<PasswordRecoveryState> = _uiState.asStateFlow()

    // ✅ ELIMINADO: Estados de autenticación Gmail

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email, errorMessage = "")
    }

    fun updateVerificationCode(code: String) {
        _uiState.value = _uiState.value.copy(verificationCode = code, errorMessage = "")
    }

    fun initiatePasswordRecovery(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = "")

            try {
                // Verificar si el email existe en tu base de datos
                val usuario = usuarioRepository.obtenerUsuarioPorEmail(_uiState.value.email)

                if (usuario != null) {
                    // Email existe, generar código
                    val recoveryCode = generateRecoveryCode()
                    Log.d("PasswordRecovery", "✅ Código generado: $recoveryCode para email: ${_uiState.value.email}")

                    // 🔹 GUARDAR EL CÓDIGO USANDO RECOVERYCODE MANAGER
                    val saved = recoveryCodeManager.saveRecoveryCode(_uiState.value.email, recoveryCode)

                    if (saved) {
                        Log.d("PasswordRecovery", "✅ Código guardado correctamente")

                        // 🔹 ENVIAR EMAIL AUTOMÁTICO CON AutoEmailSender
                        val emailSent = autoEmailSender.sendPasswordRecoveryEmail(_uiState.value.email, recoveryCode)

                        if (emailSent) {
                            _uiState.value = _uiState.value.copy(
                                successMessage = "✅ Código enviado a ${_uiState.value.email}",
                                isLoading = false
                            )
                            Log.d("PasswordRecovery", "✅ Email AUTOMÁTICO enviado exitosamente")
                            onSuccess()
                        } else {
                            _uiState.value = _uiState.value.copy(
                                errorMessage = "❌ Error al enviar el email. Verifica la configuración SMTP.",
                                isLoading = false
                            )
                            Log.e("PasswordRecovery", "❌ Error al enviar email automático")
                        }
                    } else {
                        _uiState.value = _uiState.value.copy(
                            errorMessage = "❌ Error al guardar el código de recuperación",
                            isLoading = false
                        )
                        Log.e("PasswordRecovery", "❌ Error al guardar código")
                    }
                } else {
                    _uiState.value = _uiState.value.copy(
                        errorMessage = "❌ No existe una cuenta con este email",
                        isLoading = false
                    )
                    Log.e("PasswordRecovery", "❌ Email no encontrado: ${_uiState.value.email}")
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "❌ Error: ${e.message}",
                    isLoading = false
                )
                Log.e("PasswordRecovery", "❌ Error general: ${e.message}", e)
            }
        }
    }

    // ✅ ELIMINADO: Método trySendRecoveryEmail (ya no es necesario)

    fun verifyCode(email: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            // 🔹 VERIFICAR EL CÓDIGO USANDO RECOVERYCODE MANAGER
            val isValid = recoveryCodeManager.verifyRecoveryCode(email, _uiState.value.verificationCode)

            if (isValid) {
                _uiState.value = _uiState.value.copy(isLoading = false)
                Log.d("PasswordRecovery", "✅ Código verificado correctamente")
                onSuccess()
            } else {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "❌ Código inválido o expirado",
                    isLoading = false
                )
                Log.e("PasswordRecovery", "❌ Código inválido: ${_uiState.value.verificationCode}")
            }
        }
    }

    fun resendCode(email: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val newCode = generateRecoveryCode()
                Log.d("PasswordRecovery", "🔄 Reenviando código: $newCode")

                val saved = recoveryCodeManager.saveRecoveryCode(email, newCode)

                if (saved) {
                    // 🔹 ENVIAR EMAIL AUTOMÁTICO CON AutoEmailSender
                    val emailSent = autoEmailSender.sendPasswordRecoveryEmail(email, newCode)

                    if (emailSent) {
                        _uiState.value = _uiState.value.copy(
                            successMessage = "✅ Nuevo código enviado",
                            isLoading = false
                        )
                        Log.d("PasswordRecovery", "✅ Nuevo código enviado exitosamente")
                    } else {
                        _uiState.value = _uiState.value.copy(
                            errorMessage = "❌ Error al reenviar el código.",
                            isLoading = false
                        )
                    }
                } else {
                    _uiState.value = _uiState.value.copy(
                        errorMessage = "❌ Error al generar nuevo código",
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "❌ Error: ${e.message}",
                    isLoading = false
                )
            }
        }
    }

    fun resetPassword(email: String, newPassword: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val usuario = usuarioRepository.obtenerUsuarioPorEmail(email)
                usuario?.let { user ->
                    // Actualizar la contraseña del usuario
                    val usuarioActualizado = user.copy(password = newPassword)
                    usuarioRepository.actualizarUsuario(usuarioActualizado)

                    // 🔹 LIMPIAR EL CÓDIGO DESPUÉS DE USARLO
                    recoveryCodeManager.clearRecoveryCode()

                    Log.d("PasswordRecovery", "✅ Contraseña actualizada para: $email")
                    onResult(true)
                } ?: run {
                    Log.e("PasswordRecovery", "❌ Usuario no encontrado para reset: $email")
                    onResult(false)
                }
            } catch (e: Exception) {
                Log.e("PasswordRecovery", "❌ Error resetting password: ${e.message}", e)
                onResult(false)
            }
        }
    }

    private fun generateRecoveryCode(): String {
        return (100000..999999).random().toString()
    }


    fun clearMessages() {
        _uiState.value = _uiState.value.copy(
            errorMessage = "",
            successMessage = ""
        )
    }
}