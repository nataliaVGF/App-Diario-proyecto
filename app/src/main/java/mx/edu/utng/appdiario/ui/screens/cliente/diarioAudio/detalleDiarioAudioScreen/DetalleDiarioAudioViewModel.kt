package mx.edu.utng.appdiario.ui.screens.cliente.diarioAudio.detalleDiarioAudioScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.Repository.DiarioAudioRepository
import mx.edu.utng.appdiario.Repository.TarjetaRepository
import mx.edu.utng.appdiario.local.entity.Diario.DiarioAudio
import mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta
import java.io.File

class DetalleDiarioAudioViewModel(
    private val diarioAudioRepository: DiarioAudioRepository,
    private val tarjetaRepository: TarjetaRepository
) : ViewModel() {

    // Estados para la UI
    private val _diarioAudio = MutableStateFlow<DiarioAudio?>(null)
    val diarioAudio: StateFlow<DiarioAudio?> = _diarioAudio.asStateFlow()

    private val _tarjeta = MutableStateFlow<Tarjeta?>(null)
    val tarjeta: StateFlow<Tarjeta?> = _tarjeta.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _currentPosition = MutableStateFlow(0)
    val currentPosition: StateFlow<Int> = _currentPosition.asStateFlow()

    // Cargar datos del diario de audio
    fun cargarDiarioAudio(idDiarioAudio: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                println("🎵 [DetalleAudioVM] Cargando diario audio ID: $idDiarioAudio")

                // 1. Obtener el diario de audio
                val audio = diarioAudioRepository.obtenerDiarioAudioPorId(idDiarioAudio)

                if (audio == null) {
                    _error.value = "No se encontró el audio solicitado"
                    println("❌ [DetalleAudioVM] Audio no encontrado para ID: $idDiarioAudio")
                    return@launch
                }

                _diarioAudio.value = audio
                println("🎵 [DetalleAudioVM] Audio cargado: ${audio.titulo}")

                // 2. Obtener la tarjeta asociada
                val tarjetaData = tarjetaRepository.obtenerTarjetaPorId(audio.idTarjeta)
                _tarjeta.value = tarjetaData
                println("🎵 [DetalleAudioVM] Tarjeta cargada: ${tarjetaData?.titulo}")

                // 3. Verificar que el archivo existe
                val archivo = File(audio.archivo)
                if (!archivo.exists()) {
                    println("⚠️ [DetalleAudioVM] Archivo no encontrado: ${audio.archivo}")
                    _error.value = "El archivo de audio no se encuentra disponible"
                } else {
                    println("✅ [DetalleAudioVM] Archivo verificado: ${archivo.length()} bytes")
                }

            } catch (e: Exception) {
                println("❌ [DetalleAudioVM] Error cargando datos: ${e.message}")
                _error.value = "Error al cargar los datos: ${e.message}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Eliminar diario de audio
    fun eliminarDiarioAudio(onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            try {
                val audio = _diarioAudio.value
                if (audio != null) {
                    diarioAudioRepository.eliminarDiarioAudio(audio)

                    // Eliminar archivo físico
                    val archivo = File(audio.archivo)
                    if (archivo.exists()) {
                        archivo.delete()
                        println("🗑️ [DetalleAudioVM] Archivo eliminado: ${audio.archivo}")
                    }

                    println("✅ [DetalleAudioVM] Diario de audio eliminado: ${audio.titulo}")
                    onSuccess()
                }
            } catch (e: Exception) {
                println("❌ [DetalleAudioVM] Error eliminando audio: ${e.message}")
                _error.value = "Error al eliminar el audio: ${e.message}"
            }
        }
    }

    // Actualizar diario de audio
    fun actualizarDiarioAudio(nuevoTitulo: String? = null) {
        viewModelScope.launch {
            try {
                val audioActual = _diarioAudio.value
                if (audioActual != null) {
                    val audioActualizado = audioActual.copy(
                        titulo = nuevoTitulo ?: audioActual.titulo
                    )

                    diarioAudioRepository.actualizarDiarioAudio(audioActualizado)
                    _diarioAudio.value = audioActualizado

                    println("✅ [DetalleAudioVM] Diario de audio actualizado: ${audioActualizado.titulo}")
                }
            } catch (e: Exception) {
                println("❌ [DetalleAudioVM] Error actualizando audio: ${e.message}")
                _error.value = "Error al actualizar el audio: ${e.message}"
            }
        }
    }

    // Control de reproducción
    fun setPlayingState(playing: Boolean) {
        _isPlaying.value = playing
    }

    fun updateCurrentPosition(position: Int) {
        _currentPosition.value = position
    }

    fun limpiarError() {
        _error.value = null
    }

    // Reiniciar estado
    fun reiniciarEstado() {
        _diarioAudio.value = null
        _tarjeta.value = null
        _isPlaying.value = false
        _currentPosition.value = 0
        _error.value = null
    }

    // Verificar si los datos están cargados
    fun datosCargados(): Boolean {
        return _diarioAudio.value != null
    }

    // Obtener información formateada para la UI
    fun obtenerDuracionFormateada(): String {
        val duracion = _diarioAudio.value?.audioDuracion ?: 0
        return "$duracion segundos"
    }

    fun obtenerTamañoArchivoFormateado(): String {
        val archivoPath = _diarioAudio.value?.archivo
        return if (archivoPath != null) {
            val archivo = File(archivoPath)
            if (archivo.exists()) {
                val tamañoKB = archivo.length() / 1024
                "$tamañoKB KB"
            } else {
                "No disponible"
            }
        } else {
            "No disponible"
        }
    }

    fun obtenerNombreArchivo(): String {
        return _diarioAudio.value?.archivo?.substringAfterLast("/") ?: "audio.m4a"
    }
}