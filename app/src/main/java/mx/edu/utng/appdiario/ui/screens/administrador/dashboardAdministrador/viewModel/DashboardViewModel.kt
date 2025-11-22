package mx.edu.utng.appdiario.ui.screens.administrador.dashboardAdministrador.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.edu.utng.appdiario.ui.screens.administrador.dashboardAdministrador.Repository.DashboardRepository

class DashboardViewModel(
    private val repository: DashboardRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> = _state.asStateFlow()

    init {
        // 🔹 Cargar datos iniciales
        loadDashboardData()
        // 🔹 Refrescar automáticamente cada 5 segundos
        autoRefreshDashboard()
    }

    /** Carga inicial de datos **/
    fun loadDashboardData() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val usuarios = withContext(Dispatchers.IO) { repository.getCantidadUsuarios() }
                val tarjetaPopular = withContext(Dispatchers.IO) { repository.getTipoTarjetaMasUsado() }

                _state.value = DashboardState(
                    cantidadUsuarios = usuarios,
                    tipoTarjetaMasUsado = tarjetaPopular?.name ?: "Sin datos",
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Error al cargar: ${e.message}"
                )
            }
        }
    }

    /** 🔄 Refresca los datos manualmente o desde el Composable **/
    fun actualizarDatosDashboard() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val usuarios = repository.getCantidadUsuarios()
                val tarjetaPopular = repository.getTipoTarjetaMasUsado()

                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy(
                        cantidadUsuarios = usuarios,
                        tipoTarjetaMasUsado = tarjetaPopular?.name ?: "Sin datos",
                        error = null
                    )
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy(error = "Error: ${e.message}")
                }
            }
        }
    }

    /** 🔁 Actualización automática cada 5 segundos **/
    private fun autoRefreshDashboard() {
        viewModelScope.launch {
            while (true) {
                actualizarDatosDashboard()
                delay(5000) // cada 5 segundos
            }
        }
    }
}

data class DashboardState(
    val cantidadUsuarios: Int = 0,
    val tipoTarjetaMasUsado: String = "Cargando...",
    val isLoading: Boolean = false,
    val error: String? = null
)
