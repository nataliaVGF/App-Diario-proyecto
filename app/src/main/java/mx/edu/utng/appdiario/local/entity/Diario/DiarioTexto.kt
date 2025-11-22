package mx.edu.utng.appdiario.local.entity.Diario

import androidx.room.Entity
import androidx.room.PrimaryKey
import mx.edu.utng.appdiario.local.entity.Tarjeta.TipoTarjeta
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "diario_texto")
data class DiarioTexto(
    @PrimaryKey(autoGenerate = true)
    val idDiarioTexto: Int = 0,
    val titulo: String,
    val texto: String,
    val fechaCreacion: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        .format(Date()),
    val idTarjeta: Int,
)