package mx.edu.utng.appdiario.ui.screens.administrador.dashboardAdministrador.Repository

import mx.edu.utng.appdiario.local.dao.TarjetaDao
import mx.edu.utng.appdiario.local.dao.UsuarioDao
import mx.edu.utng.appdiario.local.dao.DiarioTextoDao
import mx.edu.utng.appdiario.local.dao.DiarioAudioDao
import mx.edu.utng.appdiario.local.entity.Tarjeta.TipoTarjeta

class DashboardRepository(
    private val usuarioDao: UsuarioDao,
    private val tarjetaDao: TarjetaDao,
    private val diarioTextoDao: DiarioTextoDao,
    private val diarioAudioDao: DiarioAudioDao
) {
    // Métodos existentes (sin cambios)
    suspend fun getCantidadUsuarios(): Int {
        return try {
            usuarioDao.obtenerTodos().size
        } catch (e: Exception) {
            0
        }
    }

    suspend fun getTipoTarjetaMasUsado(): TipoTarjeta? {
        return try {
            val tarjetas = tarjetaDao.obtenerTodas()
            tarjetas.groupingBy { it.tipo }
                .eachCount()
                .maxByOrNull { it.value }?.key
        } catch (e: Exception) {
            null
        }
    }

    // Nuevos métodos para las estadísticas

    suspend fun getCantidadTotalTarjetas(): Int {
        return try {
            tarjetaDao.obtenerTodas().size
        } catch (e: Exception) {
            0
        }
    }

    suspend fun getCantidadDiariosTexto(): Int {
        return try {
            diarioTextoDao.obtenerTodos().size
        } catch (e: Exception) {
            0
        }
    }

    suspend fun getCantidadDiariosAudio(): Int {
        return try {
            diarioAudioDao.obtenerTodos().size
        } catch (e: Exception) {
            0
        }
    }

    suspend fun getTotalContenido(): Int {
        return try {
            getCantidadDiariosTexto() + getCantidadDiariosAudio()
        } catch (e: Exception) {
            0
        }
    }

    suspend fun getDistribucionPorTipoTarjeta(): Map<TipoTarjeta, Int> {
        return try {
            tarjetaDao.obtenerTodas()
                .groupingBy { it.tipo }
                .eachCount()
        } catch (e: Exception) {
            emptyMap()
        }
    }

    suspend fun getPromedioContenidoPorTarjeta(): Double {
        return try {
            val totalTarjetas = getCantidadTotalTarjetas()
            if (totalTarjetas == 0) 0.0
            else {
                val totalContenido = getTotalContenido()
                totalContenido.toDouble() / totalTarjetas
            }
        } catch (e: Exception) {
            0.0
        }
    }

    suspend fun getUsuarioConMasTarjetas(): Pair<String, Int>? {
        return try {
            val usuarios = usuarioDao.obtenerTodos()
            val tarjetas = tarjetaDao.obtenerTodas()

            val usuarioTarjetaCount = usuarios.map { usuario ->
                val cantidad = tarjetas.count { it.idUsua == usuario.idUsua }
                "${usuario.nombre} ${usuario.apellidoPa}" to cantidad
            }

            usuarioTarjetaCount.maxByOrNull { it.second }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getEstadisticasCompletas(): DashboardEstadisticas {
        return DashboardEstadisticas(
            totalUsuarios = getCantidadUsuarios(),
            totalTarjetas = getCantidadTotalTarjetas(),
            totalDiariosTexto = getCantidadDiariosTexto(),
            totalDiariosAudio = getCantidadDiariosAudio(),
            tipoTarjetaMasUsado = getTipoTarjetaMasUsado(),
            distribucionTarjetas = getDistribucionPorTipoTarjeta(),
            promedioContenidoPorTarjeta = getPromedioContenidoPorTarjeta(),
            usuarioMasActivo = getUsuarioConMasTarjetas()
        )
    }
}

// Data class para encapsular todas las estadísticas
data class DashboardEstadisticas(
    val totalUsuarios: Int,
    val totalTarjetas: Int,
    val totalDiariosTexto: Int,
    val totalDiariosAudio: Int,
    val tipoTarjetaMasUsado: TipoTarjeta?,
    val distribucionTarjetas: Map<TipoTarjeta, Int>,
    val promedioContenidoPorTarjeta: Double,
    val usuarioMasActivo: Pair<String, Int>?
)