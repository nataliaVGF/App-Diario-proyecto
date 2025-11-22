package mx.edu.utng.appdiario.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.Repository.DiarioTextoRepository
import mx.edu.utng.appdiario.Repository.TarjetaRepository
import mx.edu.utng.appdiario.local.entity.Diario.DiarioTexto
import mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta
import mx.edu.utng.appdiario.ui.screens.auth.login_usuario.SessionManager

class ListaDiariosViewModel(
    private val tarjetaRepository: TarjetaRepository,
    private val diarioTextoRepository: DiarioTextoRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _tarjetas = MutableStateFlow<List<Tarjeta>>(emptyList())
    val tarjetas: StateFlow<List<Tarjeta>> = _tarjetas.asStateFlow()

    private val _diariosPorTarjeta = MutableStateFlow<Map<Int, List<DiarioTexto>>>(emptyMap())
    val diariosPorTarjeta: StateFlow<Map<Int, List<DiarioTexto>>> = _diariosPorTarjeta.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _userId = MutableStateFlow<Int?>(null)
    val userId: StateFlow<Int?> = _userId.asStateFlow()

    init {
        println("🔄 [ViewModel] INIT - Inicializando ListaDiariosViewModel")
        println("🔄 [ViewModel] Repositories inyectados:")
        println("   - TarjetaRepository: ${tarjetaRepository::class.simpleName}")
        println("   - DiarioTextoRepository: ${diarioTextoRepository::class.simpleName}")
        println("   - SessionManager: ${sessionManager::class.simpleName}")

        // Observar el ID del usuario cuando se inicializa el ViewModel
        viewModelScope.launch {
            println("👀 [ViewModel] Iniciando observación del userIdFlow...")
            sessionManager.userIdFlow.collect { userId ->
                println("📥 [ViewModel] userIdFlow emitido: $userId")
                _userId.value = userId
                println("✅ [ViewModel] _userId actualizado a: $userId")

                // Si hay usuario, cargar datos automáticamente
                if (userId != null) {
                    println("🚀 [ViewModel] Usuario autenticado detectado, cargando datos iniciales...")
                    // Probar con diferentes tipos para debug
                    cargarDiariosPorTipo("RESETAS") // Usar el tipo exacto de la BD
                } else {
                    println("⚠️ [ViewModel] userId es null - Usuario no autenticado")
                }
            }
        }
    }

    fun cargarDiariosPorTipo(tipo: String) {
        println("\n📋 [ViewModel] cargarDiariosPorTipo() llamado")
        println("   📝 Parámetros: tipo='$tipo'") // CORREGIDO: usar 'tipo' no 'type'

        val currentUserId = _userId.value
        println("   👤 Estado actual - userId: $currentUserId")

        if (currentUserId == null) {
            println("❌ [ViewModel] ERROR: Usuario no autenticado - No se pueden cargar diarios")
            _error.value = "Usuario no autenticado"
            _isLoading.value = false
            return
        }

        println("✅ [ViewModel] Usuario autenticado, procediendo a cargar datos...")
        _isLoading.value = true
        _error.value = null
        println("   🎯 Estado - isLoading: ${_isLoading.value}, error: ${_error.value}")

        viewModelScope.launch {
            try {
                println("\n🔍 [ViewModel] Iniciando carga de datos...")
                println("   👤 Usuario ID: $currentUserId")
                println("   🏷️  Tipo solicitado: '$tipo'")

                // DEBUG: Primero obtener TODAS las tarjetas para ver qué hay
                println("   🔎 [DEBUG] Obteniendo TODAS las tarjetas del usuario...")
                val todasLasTarjetas = tarjetaRepository.obtenerTarjetasPorUsuario(currentUserId)
                println("   🔎 [DEBUG] Total tarjetas del usuario: ${todasLasTarjetas.size}")
                todasLasTarjetas.forEachIndexed { index, tarjeta ->
                    println("      📄 Tarjeta $index:")
                    println("         ID: ${tarjeta.idTarjeta}")
                    println("         Título: '${tarjeta.titulo}'")
                    println("         Tipo: '${tarjeta.tipo}'")
                    println("         Usuario ID: ${tarjeta.idUsua}") // CORREGIDO: usar usuarioId
                }

                // 1. Obtener tarjetas por tipo y usuario
                println("   📦 [Paso 1] Obteniendo tarjetas por tipo y usuario...")
                val tarjetasObtenidas = tarjetaRepository.obtenerTarjetasPorTipoYUsuario(tipo, currentUserId)
                println("   ✅ [Paso 1] Tarjetas obtenidas con filtro: ${tarjetasObtenidas.size}")

                // Debug detallado de cada tarjeta obtenida
                tarjetasObtenidas.forEachIndexed { index, tarjeta ->
                    println("      📄 Tarjeta filtrada $index:")
                    println("         ID: ${tarjeta.idTarjeta}")
                    println("         Título: '${tarjeta.titulo}'")
                    println("         Tipo: '${tarjeta.tipo}'")
                    println("         Usuario ID: ${tarjeta.idUsua}")
                }

                // Actualizar estado de tarjetas
                println("   📤 [Paso 2] Actualizando _tarjetas StateFlow...")
                _tarjetas.value = tarjetasObtenidas
                println("   ✅ [Paso 2] _tarjetas actualizado con ${_tarjetas.value.size} elementos")

                // 2. Obtener diarios de texto para cada tarjeta
                println("   📦 [Paso 3] Obteniendo diarios para cada tarjeta...")
                val diariosMap = mutableMapOf<Int, List<DiarioTexto>>()

                if (tarjetasObtenidas.isEmpty()) {
                    println("   ℹ️  [Paso 3] No hay tarjetas, saltando obtención de diarios")
                } else {
                    tarjetasObtenidas.forEach { tarjeta ->
                        println("      🔍 Obteniendo diarios para tarjeta ID: ${tarjeta.idTarjeta}")
                        val diarios = diarioTextoRepository.obtenerDiariosTextoPorTarjeta(tarjeta.idTarjeta)
                        println("      ✅ Diarios obtenidos para tarjeta ${tarjeta.idTarjeta}: ${diarios.size}")

                        // Debug detallado de cada diario
                        diarios.forEachIndexed { diarioIndex, diario ->
                            println("         📝 Diario $diarioIndex:")
                            println("            ID: ${diario.idDiarioTexto}")
                            println("            Tarjeta ID: ${diario.idTarjeta}") // CORREGIDO: usar idTarjeta
                            println("            Título: '${diario.titulo}'")
                            println("            Texto: '${diario.texto?.take(50)}...'")
                            println("            Fecha: ${diario.fechaCreacion}")
                        }

                        diariosMap[tarjeta.idTarjeta] = diarios
                    }
                }

                // Actualizar estado de diarios
                println("   📤 [Paso 4] Actualizando _diariosPorTarjeta StateFlow...")
                _diariosPorTarjeta.value = diariosMap
                println("   ✅ [Paso 4] _diariosPorTarjeta actualizado con ${_diariosPorTarjeta.value.size} entradas")

                // Resumen final
                println("\n🎉 [ViewModel] CARGA COMPLETADA EXITOSAMENTE")
                println("   📊 Resumen:")
                println("      • Tarjetas cargadas: ${_tarjetas.value.size}")
                println("      • Mapeo diarios/tarjeta: ${_diariosPorTarjeta.value.size}")
                println("      • Total diarios: ${_diariosPorTarjeta.value.values.flatten().size}")

            } catch (e: Exception) {
                println("\n💥 [ViewModel] ERROR durante la carga de datos")
                println("   🚨 Excepción: ${e.javaClass.simpleName}")
                println("   📄 Mensaje: ${e.message}")
                println("   📍 StackTrace:")
                e.printStackTrace()

                _error.value = "Error al cargar los diarios: ${e.message}"
                println("   ❗ Error guardado en StateFlow: ${_error.value}")

            } finally {
                _isLoading.value = false
                println("   🏁 Estado final - isLoading: ${_isLoading.value}")
            }
        }
    }

    // En tu ListaDiariosViewModel, agrega esta función:
    fun eliminarDiario(diario: DiarioTexto) {
        println("🗑️ [ViewModel] Eliminando diario: ${diario.idDiarioTexto} - '${diario.titulo}'")

        viewModelScope.launch {
            try {
                diarioTextoRepository.eliminarDiarioTexto(diario)
                println("✅ [ViewModel] Diario eliminado exitosamente")

                // ACTUALIZACIÓN INMEDIATA: Remover el diario eliminado de los StateFlows
                val diariosMapActualizado = _diariosPorTarjeta.value.toMutableMap()

                // Buscar en todas las tarjetas y eliminar el diario
                diariosMapActualizado.forEach { (tarjetaId, diarios) ->
                    val diariosFiltrados = diarios.filter { it.idDiarioTexto != diario.idDiarioTexto }
                    diariosMapActualizado[tarjetaId] = diariosFiltrados
                }

                // Actualizar el StateFlow - esto hará que la UI se actualice automáticamente
                _diariosPorTarjeta.value = diariosMapActualizado

                println("🔄 [ViewModel] StateFlows actualizados después de eliminar")

            } catch (e: Exception) {
                println("❌ [ViewModel] Error al eliminar diario: ${e.message}")
                _error.value = "Error al eliminar la nota: ${e.message}"
            }
        }
    }

    // Método para cargar todos los tipos disponibles (para debug)
    fun debugTiposDisponibles() {
        val currentUserId = _userId.value ?: return

        viewModelScope.launch {
            println("\n🔍 [DEBUG] Tipos de tarjetas disponibles para usuario $currentUserId:")
            val todasTarjetas = tarjetaRepository.obtenerTarjetasPorUsuario(currentUserId)
            val tiposUnicos = todasTarjetas.map { it.tipo.name }.distinct()
            tiposUnicos.forEach { tipo ->
                println("   🏷️  Tipo: '$tipo'")
            }
        }
    }

    // ... (los demás métodos se mantienen igual)
    fun limpiarError() {
        println("\n🧹 [ViewModel] limpiarError() llamado")
        println("   📝 Estado anterior - error: ${_error.value}")
        _error.value = null
        println("   ✅ Estado actual - error: ${_error.value}")
    }

    fun obtenerPrimerDiario(tarjetaId: Int): DiarioTexto? {
        println("\n🔍 [ViewModel] obtenerPrimerDiario() llamado")
        println("   📝 Parámetros: tarjetaId=$tarjetaId")

        val diarios = _diariosPorTarjeta.value[tarjetaId]
        println("   📦 Diarios encontrados para tarjeta $tarjetaId: ${diarios?.size ?: 0}")

        val primerDiario = diarios?.firstOrNull()
        println("   ✅ Primer diario: ${primerDiario?.let { "ID: ${it.idDiarioTexto}, Título: ${it.titulo}" } ?: "NULO"}")

        return primerDiario
    }

    fun obtenerTodosDiarios(tarjetaId: Int): List<DiarioTexto> {
        println("\n🔍 [ViewModel] obtenerTodosDiarios() llamado")
        println("   📝 Parámetros: tarjetaId=$tarjetaId")

        val diarios = _diariosPorTarjeta.value[tarjetaId] ?: emptyList()
        println("   ✅ Diarios obtenidos: ${diarios.size}")

        diarios.forEachIndexed { index, diario ->
            println("      📄 Diario $index: ID=${diario.idDiarioTexto}, Título=${diario.titulo}")
        }

        return diarios
    }

    fun isUserAuthenticated(): Boolean {
        val isAuthenticated = _userId.value != null
        println("\n🔐 [ViewModel] isUserAuthenticated() = $isAuthenticated (userId: ${_userId.value})")
        return isAuthenticated
    }

    fun debugEstado() {
        println("\n📊 [ViewModel] DEBUG ESTADO ACTUAL")
        println("   👤 userId: ${_userId.value}")
        println("   📋 tarjetas: ${_tarjetas.value.size} elementos")
        println("   📝 diariosPorTarjeta: ${_diariosPorTarjeta.value.size} mapeos")
        println("   ⏳ isLoading: ${_isLoading.value}")
        println("   ❗ error: ${_error.value}")

        _tarjetas.value.forEachIndexed { index, tarjeta ->
            println("      📄 Tarjeta $index: ID=${tarjeta.idTarjeta}, Título='${tarjeta.titulo}'")
            val diarios = _diariosPorTarjeta.value[tarjeta.idTarjeta] ?: emptyList()
            println("         📝 Diarios asociados: ${diarios.size}")
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("\n🗑️ [ViewModel] onCleared() - ListaDiariosViewModel destruido")
    }
}