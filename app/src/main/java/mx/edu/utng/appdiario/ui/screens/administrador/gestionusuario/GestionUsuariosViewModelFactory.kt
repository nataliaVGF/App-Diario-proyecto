package mx.edu.utng.appdiario.ui.screens.administrador.gestionusuario

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.utng.appdiario.Repository.UsuarioRepository
import mx.edu.utng.appdiario.local.database.AppDatabase

class GestionUsuariosViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GestionUsuariosViewModel::class.java)) {
            val database = AppDatabase.getDatabase(context)
            val repository = UsuarioRepository(database.usuarioDao())
            return GestionUsuariosViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}