package mx.edu.utng.appdiario.ui.screens.cliente.diarioAudio.listaDiariosAudioScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.Repository.DiarioAudioRepository
import mx.edu.utng.appdiario.Repository.TarjetaRepository
import mx.edu.utng.appdiario.local.entity.Diario.DiarioAudio
import mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta
import mx.edu.utng.appdiario.ui.screens.auth.login_usuario.SessionManager

class ListaDiariosAudioViewModel(
    private val tarjetaRepository: TarjetaRepository,
    private val diarioAudioRepository: DiarioAudioRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _tarjetas = MutableStateFlow<List<Tarjeta>>(emptyList())
    val tarjetas: StateFlow<List<Tarjeta>> = _tarjetas.asStateFlow()

    private val _audiosPorTarjeta = MutableStateFlow<Map<Int, List<DiarioAudio>>>(emptyMap())
    val audiosPorTarjeta: StateFlow<Map<Int, List<DiarioAudio>>> = _audiosPorTarjeta.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _userId = MutableStateFlow<Int?>(null)
    val userId: StateFlow<Int?> = _userId.asStateFlow()

    private var selectedFilter = "RESETAS"

    init {
        println("🎵 [AudioListViewModel] INIT - Inicializando ListaDiariosAudioViewModel")

        viewModelScope.launch {
            sessionManager.userIdFlow.collect { userId ->
                _userId.value = userId
                println("🎵 [AudioListViewModel] userId actualizado: $userId")

                if (userId != null) {
                    println("🎵 [AudioListViewModel] Cargando datos iniciales de audio...")
                    cargarAudiosPorTipo("RESETAS")
                }
            }
        }
    }

    fun cargarAudiosPorTipo(tipo: String) {
        selectedFilter = tipo

        val currentUserId = _userId.value
        if (currentUserId == null) {
            _error.value = "Usuario no autenticado"
            return
        }

        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                println("🎵 [AudioListViewModel] Cargando audios para usuario: $currentUserId, tipo: $tipo")

                // 1. Obtener tarjetas por tipo y usuario
                val tarjetasObtenidas = tarjetaRepository.obtenerTarjetasPorTipoYUsuario(tipo, currentUserId)
                _tarjetas.value = tarjetasObtenidas

                println("🎵 [AudioListViewModel] Tarjetas obtenidas: ${tarjetasObtenidas.size}")

                // 2. Obtener audios para cada tarjeta
                val audiosMap = mutableMapOf<Int, List<DiarioAudio>>()
                tarjetasObtenidas.forEach { tarjeta ->
                    val audios = diarioAudioRepository.obtenerDiariosAudioPorTarjeta(tarjeta.idTarjeta)
                    audiosMap[tarjeta.idTarjeta] = audios
                    println("🎵 [AudioListViewModel] Tarjeta ${tarjeta.idTarjeta} - Audios: ${audios.size}")
                }
                _audiosPorTarjeta.value = audiosMap

            } catch (e: Exception) {
                _error.value = "Error al cargar los audios: ${e.message}"
                println("❌ [AudioListViewModel] Error al cargar audios: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Método para eliminar audio
    fun eliminarAudio(audio: DiarioAudio) {
        println("🗑️ [AudioListViewModel] Eliminando audio: ${audio.idDiarioAudio} - '${audio.titulo}'")

        viewModelScope.launch {
            try {
                diarioAudioRepository.eliminarDiarioAudio(audio)
                println("✅ [AudioListViewModel] Audio eliminado exitosamente")

                // Actualizar StateFlow inmediatamente
                val audiosMapActualizado = _audiosPorTarjeta.value.toMutableMap()
                audiosMapActualizado.forEach { (tarjetaId, audios) ->
                    val audiosFiltrados = audios.filter { it.idDiarioAudio != audio.idDiarioAudio }
                    audiosMapActualizado[tarjetaId] = audiosFiltrados
                }
                _audiosPorTarjeta.value = audiosMapActualizado

            } catch (e: Exception) {
                println("❌ [AudioListViewModel] Error al eliminar audio: ${e.message}")
                _error.value = "Error al eliminar el audio: ${e.message}"
            }
        }
    }

    fun limpiarError() {
        _error.value = null
    }

    // Método para obtener el primer audio de una tarjeta
    fun obtenerPrimerAudio(tarjetaId: Int): DiarioAudio? {
        return _audiosPorTarjeta.value[tarjetaId]?.firstOrNull()
    }

    // Debug method
    fun debugTiposDisponibles() {
        val currentUserId = _userId.value ?: return

        viewModelScope.launch {
            println("\n🔍 [AudioListViewModel] Tipos de tarjetas disponibles para usuario $currentUserId:")
            val todasTarjetas = tarjetaRepository.obtenerTarjetasPorUsuario(currentUserId)
            val tiposUnicos = todasTarjetas.map { it.tipo.name }.distinct()
            tiposUnicos.forEach { tipo ->
                println("   🏷️  Tipo: '$tipo'")
            }
        }
    }
}