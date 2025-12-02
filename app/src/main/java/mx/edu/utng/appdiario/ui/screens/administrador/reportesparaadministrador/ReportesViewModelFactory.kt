package mx.edu.utng.appdiario.ui.screens.administrador.reportesparaadministrador

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.utng.appdiario.repository.UsuarioRepository

class ReportesViewModelFactory(
    private val usuarioRepository: UsuarioRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReportesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReportesViewModel(usuarioRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
