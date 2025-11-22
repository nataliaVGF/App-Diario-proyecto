package mx.edu.utng.appdiario.local.dao

import androidx.room.*
import mx.edu.utng.appdiario.local.entity.Diario.DiarioAudio

@Dao
interface DiarioAudioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(diarioAudio: DiarioAudio)

    @Update
    suspend fun actualizar(diarioAudio: DiarioAudio)

    @Delete
    suspend fun eliminar(diarioAudio: DiarioAudio)

    @Query("SELECT * FROM diario_audio")
    suspend fun obtenerTodos(): List<DiarioAudio>

    @Query("SELECT * FROM diario_audio WHERE idTarjeta = :tarjetaId")
    suspend fun obtenerPorTarjeta(tarjetaId: Int): List<DiarioAudio>
}