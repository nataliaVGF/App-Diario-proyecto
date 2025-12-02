package mx.edu.utng.appdiario.ui.screens.auth.loginusuario

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.local.database.AppDatabase

class LoginViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            val database = AppDatabase.getDatabase(context)
            val usuarioDao = database.usuarioDao()
            val repository = UsuarioRepository(usuarioDao)
            val sessionManager = SessionManager(context) // ✅ Añadido SessionManager
            return LoginViewModel(repository, sessionManager) as T // ✅ Pasar sessionManager
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}