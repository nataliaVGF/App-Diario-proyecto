package mx.edu.utng.appdiario.ui.screens.cliente.diariotexto.detallediarioscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.repository.DiarioTextoRepository
import mx.edu.utng.appdiario.repository.TarjetaRepository
import mx.edu.utng.appdiario.local.entity.Diario.DiarioTexto
import mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta
import java.text.SimpleDateFormat
import java.util.*

class DetalleDiarioTextoViewModel(
    private val diarioTextoRepository: DiarioTextoRepository,
    private val tarjetaRepository: TarjetaRepository
) : ViewModel() {

    // Estados para la UI
    private val _diarioTexto = MutableStateFlow<DiarioTexto?>(null)
    val diarioTexto: StateFlow<DiarioTexto?> = _diarioTexto.asStateFlow()

    private val _tarjeta = MutableStateFlow<Tarjeta?>(null)
    val tarjeta: StateFlow<Tarjeta?> = _tarjeta.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // Cargar datos del diario de texto
    fun cargarDiarioTexto(idDiarioTexto: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                println("📝 [DetalleTextoVM] Cargando diario texto ID: $idDiarioTexto")

                // 1. Obtener el diario de texto
                val texto = diarioTextoRepository.obtenerDiarioTextoPorId(idDiarioTexto)

                if (texto == null) {
                    _error.value = "No se encontró el diario solicitado"
                    println("❌ [DetalleTextoVM] Diario no encontrado para ID: $idDiarioTexto")
                    return@launch
                }

                _diarioTexto.value = texto
                println("📝 [DetalleTextoVM] Diario cargado: ${texto.titulo}")
                println("📝 [DetalleTextoVM] Contenido: ${texto.texto.take(50)}...")

                // 2. Obtener la tarjeta asociada
                val tarjetaData = tarjetaRepository.obtenerTarjetaPorId(texto.idTarjeta)
                _tarjeta.value = tarjetaData
                println("📝 [DetalleTextoVM] Tarjeta cargada: ${tarjetaData?.titulo}")

            } catch (e: Exception) {
                println("❌ [DetalleTextoVM] Error cargando datos: ${e.message}")
                _error.value = "Error al cargar los datos: ${e.message}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Eliminar diario de texto
    fun eliminarDiarioTexto(onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            try {
                val texto = _diarioTexto.value
                if (texto != null) {
                    diarioTextoRepository.eliminarDiarioTexto(texto)
                    println("✅ [DetalleTextoVM] Diario de texto eliminado: ${texto.titulo}")
                    onSuccess()
                }
            } catch (e: Exception) {
                println("❌ [DetalleTextoVM] Error eliminando diario: ${e.message}")
                _error.value = "Error al eliminar el diario: ${e.message}"
            }
        }
    }

    // Actualizar diario de texto
    fun actualizarDiarioTexto(nuevoTitulo: String? = null, nuevoContenido: String? = null) {
        viewModelScope.launch {
            try {
                val textoActual = _diarioTexto.value
                if (textoActual != null) {
                    val textoActualizado = textoActual.copy(
                        titulo = nuevoTitulo ?: textoActual.titulo,
                        texto = nuevoContenido ?: textoActual.texto
                        // Nota: Tu entidad no tiene fechaActualizacion, solo fechaCreacion
                    )

                    diarioTextoRepository.actualizarDiarioTexto(textoActualizado)
                    _diarioTexto.value = textoActualizado

                    println("✅ [DetalleTextoVM] Diario de texto actualizado: ${textoActualizado.titulo}")
                }
            } catch (e: Exception) {
                println("❌ [DetalleTextoVM] Error actualizando diario: ${e.message}")
                _error.value = "Error al actualizar el diario: ${e.message}"
            }
        }
    }

    fun limpiarError() {
        _error.value = null
    }

    // Reiniciar estado
    fun reiniciarEstado() {
        _diarioTexto.value = null
        _tarjeta.value = null
        _error.value = null
    }

    // Verificar si los datos están cargados
    fun datosCargados(): Boolean {
        return _diarioTexto.value != null
    }

    // Obtener información formateada para la UI
    fun obtenerFechaCreacionFormateada(): String {
        val fecha = _diarioTexto.value?.fechaCreacion
        return if (fecha != null) {
            try {
                // Tu fecha tiene formato "yyyy-MM-dd HH:mm:ss"
                val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val outputFormat = SimpleDateFormat("dd/MM/yyyy 'a las' HH:mm", Locale.getDefault())
                val date = inputFormat.parse(fecha)
                outputFormat.format(date ?: Date())
            } catch (e: Exception) {
                // Si falla el parsing, intentar con formato simple
                try {
                    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val date = inputFormat.parse(fecha)
                    outputFormat.format(date ?: Date())
                } catch (e2: Exception) {
                    "Fecha no disponible"
                }
            }
        } else {
            "Fecha no disponible"
        }
    }

    fun obtenerContenidoConFormato(): String {
        return _diarioTexto.value?.texto ?: "No hay contenido disponible"
    }

    fun obtenerTitulo(): String {
        return _diarioTexto.value?.titulo ?: "Sin título"
    }

    fun obtenerTipoTarjeta(): String {
        return _tarjeta.value?.tipo?.name ?: "Diario"
    }

    // Obtener estadísticas del contenido
    fun obtenerEstadisticasContenido(): Map<String, Int> {
        val contenido = _diarioTexto.value?.texto ?: ""
        return mapOf(
            "caracteres" to contenido.length,
            "palabras" to contenido.split("\\s+".toRegex()).count { it.isNotBlank() },
            "lineas" to contenido.split("\n").size
        )
    }

    // Obtener fecha de creación simple (solo fecha)
    fun obtenerFechaCreacionSimple(): String {
        val fecha = _diarioTexto.value?.fechaCreacion
        return if (fecha != null) {
            try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val date = inputFormat.parse(fecha)
                outputFormat.format(date ?: Date())
            } catch (e: Exception) {
                "Fecha no disponible"
            }
        } else {
            "Fecha no disponible"
        }
    }
}