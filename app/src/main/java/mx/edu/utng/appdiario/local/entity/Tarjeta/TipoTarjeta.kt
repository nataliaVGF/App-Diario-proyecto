package mx.edu.utng.appdiario.local.entity.Tarjeta

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class TipoTarjeta {
    PERSONAL,
    RESETAS,
    ACTIVIDADES
}

@Entity(tableName = "tarjeta")
data class Tarjeta(
    @PrimaryKey(autoGenerate = true)
    val idTarjeta: Int = 0,
    val titulo: String,
    val tipo: TipoTarjeta,
    val idUsua: Int // llave foránea a Usuario
)