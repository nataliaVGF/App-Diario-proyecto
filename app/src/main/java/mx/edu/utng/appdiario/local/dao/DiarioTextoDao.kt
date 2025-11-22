package mx.edu.utng.appdiario.local.dao

import androidx.room.*
import mx.edu.utng.appdiario.local.entity.Diario.DiarioTexto

@Dao
interface DiarioTextoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(diarioTexto: DiarioTexto)

    @Update
    suspend fun actualizar(diarioTexto: DiarioTexto)

    @Delete
    suspend fun eliminar(diarioTexto: DiarioTexto)

    @Query("SELECT * FROM diario_texto")
    suspend fun obtenerTodos(): List<DiarioTexto>

    @Query("SELECT * FROM diario_texto WHERE idDiarioTexto = :id LIMIT 1")
    suspend fun obtenerPorId(id: Int): DiarioTexto? //////

    @Query("SELECT * FROM diario_texto WHERE idTarjeta = :tarjetaId")
    suspend fun obtenerPorTarjeta(tarjetaId: Int): List<DiarioTexto>/////

}