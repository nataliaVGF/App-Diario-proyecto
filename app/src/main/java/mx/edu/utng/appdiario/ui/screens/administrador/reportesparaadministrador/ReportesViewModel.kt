package mx.edu.utng.appdiario.ui.screens.administrador.reportesparaadministrador

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.local.entity.Ususario.TipoUsuario
import mx.edu.utng.appdiario.local.entity.Ususario.Usuario
import java.text.SimpleDateFormat
import java.util.*

data class ReporteState(
    val usuariosPromedioPorMes: Map<String, Int> = emptyMap(), // total usuarios por mes
    val usuariosPorTipo: Map<String, Int> = emptyMap(),         // usuarios ADMIN vs NORMAL
    val isLoading: Boolean = false
)

class ReportesViewModel(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ReporteState())
    val state: StateFlow<ReporteState> = _state

    init {
        cargarDatos()
    }

    private fun cargarDatos() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            val usuarios = usuarioRepository.obtenerTodosLosUsuarios()
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val calendar = Calendar.getInstance()

            // 🔹 Contar usuarios por mes según fechaRegistro
            val usuariosPorMes = mutableMapOf<String, MutableList<Usuario>>()
            usuarios.forEach { usuario ->
                try {
                    val fecha = sdf.parse(usuario.fechaRegistro)
                    if (fecha != null) {
                        calendar.time = fecha
                        val mes = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                        usuariosPorMes.getOrPut(mes) { mutableListOf() }.add(usuario)
                    }
                } catch (e: Exception) {
                    // ignorar errores de parseo
                }
            }

            val usuariosPromedioPorMes = usuariosPorMes.mapValues { it.value.size }

            // 🔹 Contar usuarios por tipo
            val usuariosPorTipo = mapOf(
                "Administradores" to usuarios.count { it.tipo == TipoUsuario.ADMIN },
                "Usuarios" to usuarios.count { it.tipo == TipoUsuario.NORMAL }
            )

            _state.value = ReporteState(
                usuariosPromedioPorMes = usuariosPromedioPorMes,
                usuariosPorTipo = usuariosPorTipo,
                isLoading = false
            )
        }
    }
}
