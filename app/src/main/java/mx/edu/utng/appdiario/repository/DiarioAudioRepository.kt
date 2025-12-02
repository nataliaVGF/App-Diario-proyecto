package mx.edu.utng.appdiario.repository

import mx.edu.utng.appdiario.local.dao.DiarioAudioDao
import mx.edu.utng.appdiario.local.entity.Diario.DiarioAudio

class DiarioAudioRepository(
    private val diarioAudioDao: DiarioAudioDao
) {
    suspend fun insertarDiarioAudio(diarioAudio: DiarioAudio) {
        diarioAudioDao.insertar(diarioAudio)
    }

    suspend fun actualizarDiarioAudio(diarioAudio: DiarioAudio) {
        diarioAudioDao.actualizar(diarioAudio)
    }

    suspend fun eliminarDiarioAudio(diarioAudio: DiarioAudio) {
        diarioAudioDao.eliminar(diarioAudio)
    }

    suspend fun obtenerTodosLosDiariosAudio(): List<DiarioAudio> {
        return diarioAudioDao.obtenerTodos()
    }

    suspend fun obtenerDiarioAudioPorId(id: Int): DiarioAudio? {
        return diarioAudioDao.obtenerTodos().find { it.idDiarioAudio == id }
    }

    suspend fun obtenerDiariosAudioPorTarjeta(tarjetaId: Int): List<DiarioAudio> {
        return diarioAudioDao.obtenerPorTarjeta(tarjetaId)
    }

    suspend fun buscarDiariosAudioPorTitulo(titulo: String): List<DiarioAudio> {
        return diarioAudioDao.obtenerTodos().filter {
            it.titulo.contains(titulo, ignoreCase = true)
        }
    }

    suspend fun obtenerDiariosAudioPorDuracion(minDuracion: Int, maxDuracion: Int): List<DiarioAudio> {
        return diarioAudioDao.obtenerTodos().filter {
            it.audioDuracion in minDuracion..maxDuracion
        }
    }
}