package mx.edu.utng.appdiario.Repository


import mx.edu.utng.appdiario.local.dao.DiarioTextoDao
import mx.edu.utng.appdiario.local.entity.Diario.DiarioTexto

class DiarioTextoRepository(
    private val diarioTextoDao: DiarioTextoDao
) {
    suspend fun insertarDiarioTexto(diarioTexto: DiarioTexto) {
        diarioTextoDao.insertar(diarioTexto)
    }

    suspend fun actualizarDiarioTexto(diarioTexto: DiarioTexto) {
        diarioTextoDao.actualizar(diarioTexto)
    }

    suspend fun eliminarDiarioTexto(diarioTexto: DiarioTexto) {
        diarioTextoDao.eliminar(diarioTexto)
    }

    suspend fun obtenerTodosLosDiariosTexto(): List<DiarioTexto> {
        return diarioTextoDao.obtenerTodos()
    }

    suspend fun obtenerDiarioTextoPorId(id: Int): DiarioTexto? {
        return diarioTextoDao.obtenerTodos().find { it.idDiarioTexto == id }
    }

    suspend fun obtenerDiariosTextoPorTarjeta(tarjetaId: Int): List<DiarioTexto> {
        return diarioTextoDao.obtenerPorTarjeta(tarjetaId)
    }

    suspend fun buscarDiariosTextoPorTitulo(titulo: String): List<DiarioTexto> {
        return diarioTextoDao.obtenerTodos().filter {
            it.titulo.contains(titulo, ignoreCase = true)
        }
    }
}