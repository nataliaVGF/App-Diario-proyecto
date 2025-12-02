package mx.edu.utng.appdiario.ui.screens.auth.recuperacioncontrasena

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.ui.screens.auth.AutoEmailSender

class PasswordRecoveryViewModelFactory(
    private val context: Context,
    private val usuarioRepository: UsuarioRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PasswordRecoveryViewModel::class.java)) {
            val recoveryCodeManager = RecoveryCodeManager(context)
            val autoEmailSender = AutoEmailSender(context) // ✅ Crear AutoEmailSender aquí

            return PasswordRecoveryViewModel(
                usuarioRepository = usuarioRepository,
                recoveryCodeManager = recoveryCodeManager,
                autoEmailSender = autoEmailSender // ✅ Pasar al ViewModel
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}