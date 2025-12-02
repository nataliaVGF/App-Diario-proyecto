package mx.edu.utng.appdiario.ui.screens.administrador.dashboardadministrador.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.utng.appdiario.local.database.AppDatabase
import mx.edu.utng.appdiario.ui.screens.administrador.dashboardadministrador.repository.DashboardRepository

class DashboardViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            val database = AppDatabase.getDatabase(context)
            val repository = DashboardRepository(
                usuarioDao = database.usuarioDao(),
                tarjetaDao = database.tarjetaDao(),
                diarioTextoDao = database.diarioTextoDao(),
                diarioAudioDao = database.diarioAudioDao()
            )
            return DashboardViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}