package mx.edu.utng.appdiario.ui.screens.cliente.diarioAudio.DiarioAudioScreen

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
import mx.edu.utng.appdiario.local.entity.Tarjeta.TipoTarjeta
import java.text.SimpleDateFormat
import java.util.*

class DiarioAudioViewModel(
    private val diarioAudioRepository: DiarioAudioRepository,
    private val tarjetaRepository: TarjetaRepository
) : ViewModel() {

    // Estado para la UI
    private val _uiState = MutableStateFlow(DiarioAudioUiState())
    val uiState: StateFlow<DiarioAudioUiState> = _uiState.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    data class DiarioAudioUiState(
        val titulo: String = "",
        val archivo: String = "",
        val audioDuracion: Int = 0,
        val idTarjeta: Int? = null
    )

    fun actualizarTitulo(titulo: String) {
        _uiState.value = _uiState.value.copy(titulo = titulo)
    }

    fun actualizarArchivo(archivo: String) {
        _uiState.value = _uiState.value.copy(archivo = archivo)
    }

    fun actualizarDuracion(duracion: Int) {
        _uiState.value = _uiState.value.copy(audioDuracion = duracion)
    }

    // Método principal para crear diario de audio
    fun crearDiarioAudio(usuarioId: Int, tipo: TipoTarjeta) {
        viewModelScope.launch {
            try {
                println("🎵 [AudioViewModel] Creando diario de audio para usuario: $usuarioId, tipo: $tipo")

                // 1. Buscar o crear tarjeta
                val tarjeta = obtenerOCrearTarjeta(usuarioId, tipo)
                println("🎵 [AudioViewModel] Tarjeta obtenida/creada: ${tarjeta.idTarjeta} - ${tarjeta.titulo}")

                // 2. Crear el diario de audio
                val diarioAudio = DiarioAudio(
                    titulo = _uiState.value.titulo,
                    archivo = _uiState.value.archivo,
                    audioDuracion = _uiState.value.audioDuracion,
                    idTarjeta = tarjeta.idTarjeta
                )

                // 3. Guardar en la base de datos
                diarioAudioRepository.insertarDiarioAudio(diarioAudio)
                println("🎵 [AudioViewModel] Diario de audio creado exitosamente: ${diarioAudio.titulo}")

                // 4. Limpiar el estado
                _uiState.value = DiarioAudioUiState()

            } catch (e: Exception) {
                println("❌ [AudioViewModel] Error al crear diario de audio: ${e.message}")
                _error.value = "Error al crear el diario de audio: ${e.message}"
            }
        }
    }

    // Método para editar diario existente
    fun actualizarDiarioAudio() {
        viewModelScope.launch {
            try {
                val currentState = _uiState.value
                if (currentState.idTarjeta != null) {
                    // Aquí implementarías la actualización si es necesario
                    println("🎵 [AudioViewModel] Actualizando diario de audio")
                }
            } catch (e: Exception) {
                println("❌ [AudioViewModel] Error al actualizar diario de audio: ${e.message}")
                _error.value = "Error al actualizar el diario de audio: ${e.message}"
            }
        }
    }

    // Método para cargar un diario existente (para edición)
    fun cargarDiarioAudio(idDiarioAudio: Int) {
        viewModelScope.launch {
            try {
                val diario = diarioAudioRepository.obtenerDiarioAudioPorId(idDiarioAudio)
                diario?.let {
                    _uiState.value = _uiState.value.copy(
                        titulo = it.titulo,
                        archivo = it.archivo,
                        audioDuracion = it.audioDuracion,
                        idTarjeta = it.idTarjeta
                    )
                    println("🎵 [AudioViewModel] Diario de audio cargado: ${it.titulo}")
                }
            } catch (e: Exception) {
                println("❌ [AudioViewModel] Error al cargar diario de audio: ${e.message}")
                _error.value = "Error al cargar el diario de audio: ${e.message}"
            }
        }
    }

    // Función para obtener o crear tarjeta (igual que en texto)
    private suspend fun obtenerOCrearTarjeta(usuarioId: Int, tipo: TipoTarjeta): Tarjeta {
        // Buscar tarjetas existentes del usuario
        val tarjetasUsuario = tarjetaRepository.obtenerTarjetasPorUsuario(usuarioId)

        // Buscar si ya existe una tarjeta del tipo solicitado
        val tarjetaExistente = tarjetasUsuario.find { it.tipo == tipo }

        return if (tarjetaExistente != null) {
            println("🎵 [AudioViewModel] Tarjeta existente encontrada: ${tarjetaExistente.titulo}")
            tarjetaExistente
        } else {
            // Crear nueva tarjeta
            val nuevaTarjeta = Tarjeta(
                titulo = when (tipo) {
                    TipoTarjeta.PERSONAL -> "Notas Personales"
                    TipoTarjeta.RESETAS -> "Mis Recetas"
                    TipoTarjeta.ACTIVIDADES -> "Mis Actividades"
                },
                tipo = tipo,
                idUsua = usuarioId,
            )

            val tarjetaId = tarjetaRepository.insertarTarjeta(nuevaTarjeta)
            nuevaTarjeta.copy(idTarjeta = tarjetaId).also {
                println("🎵 [AudioViewModel] Nueva tarjeta creada: ${it.titulo}")
            }
        }
    }

    fun limpiarError() {
        _error.value = null
    }
}