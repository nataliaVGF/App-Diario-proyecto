package mx.edu.utng.appdiario.ui.screens.auth.loginusuario

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.local.entity.Ususario.TipoUsuario

class LoginViewModel(
    private val repository: UsuarioRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _navigateToRegister = MutableLiveData<Boolean>()
    val navigateToRegister: LiveData<Boolean> = _navigateToRegister

    private val _navigateToAdmin = MutableLiveData<Boolean>()
    val navigateToAdmin: LiveData<Boolean> = _navigateToAdmin

    private val _navigateToUser = MutableLiveData<Boolean>()
    val navigateToUser: LiveData<Boolean> = _navigateToUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    ////////////////////////////////////////////////////////////////////////////////
    // NUEVAS FUNCIONES PARA EVITAR "Unresolved reference"
    fun onEmailChanged(newEmail: String) {
        onLoginChanged(newEmail, _password.value ?: "")
    }

    fun onPasswordChanged(newPassword: String) {
        onLoginChanged(_email.value ?: "", newPassword)
    }
    ////////////////////////////////////////////////////////////////////////////////

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password

        // Validaciones en tiempo real
        val emailValid = isValidEmail(email)
        val passwordValid = isValidPassword(password)

        _emailError.value = if (email.isEmpty()) "El email es obligatorio"
        else if (!emailValid) "Formato de email inválido"
        else null

        _passwordError.value = if (password.isEmpty()) "La contraseña es obligatoria"
        else if (!passwordValid) "Mínimo 6 caracteres"
        else null

        _loginEnable.value = emailValid && passwordValid
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean =
        password.length >= 6

    /////////////////////////////////////////////////////////////////////////////
    // LOGIN CON SQLite
    fun loginUsuario() {
        val currentEmail = _email.value ?: ""
        val currentPassword = _password.value ?: ""

        // Validar antes de proceder
        if (!isValidEmail(currentEmail) || !isValidPassword(currentPassword)) {
            _errorMessage.value = "Por favor completa los campos correctamente"
            return
        }

        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            try {
                val usuario = repository.obtenerUsuarioPorEmail(currentEmail)

                if (usuario == null) {
                    _errorMessage.value = "Usuario no encontrado"
                    _isLoading.value = false
                    return@launch
                }

                if (usuario.password != currentPassword) {
                    _errorMessage.value = "Contraseña incorrecta"
                    _isLoading.value = false
                    return@launch
                }

                // Guardar sesión
                println("DEBUG: Login exitoso, guardando userId: ${usuario.idUsua}")
                sessionManager.saveUserId(usuario.idUsua)

                _isLoading.value = false

                when (usuario.tipo) {
                    TipoUsuario.ADMIN -> _navigateToAdmin.value = true
                    TipoUsuario.NORMAL -> _navigateToUser.value = true
                    else -> _errorMessage.value = "Tipo de usuario no reconocido"
                }

            } catch (e: Exception) {
                _errorMessage.value = "Error al iniciar sesión: ${e.message}"
                _isLoading.value = false
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    // LIMPIAR ESTADOS DE NAVEGACIÓN
    fun onNavigateToRegisterCompleted() {
        _navigateToRegister.value = false
    }

    fun onNavigateToAdminCompleted() {
        _navigateToAdmin.value = false
    }

    fun onNavigateToUserCompleted() {
        _navigateToUser.value = false
    }

    /////////////////////////////////////////////////////////////////////////////
    fun navigateToRegister() {
        _navigateToRegister.value = true
    }
}
