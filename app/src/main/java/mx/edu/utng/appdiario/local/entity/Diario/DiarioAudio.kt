package mx.edu.utng.appdiario.local.entity.Diario

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "diario_audio")
data class DiarioAudio(
    @PrimaryKey(autoGenerate = true)
    val idDiarioAudio: Int = 0,
    val titulo: String,
    val archivo: String, // ruta o nombre del archivo
    val audioDuracion: Int, // duración en segundos
    val fechaCreacion: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        .format(Date()),
    val idTarjeta: Int // llave foránea a Tarjeta
)