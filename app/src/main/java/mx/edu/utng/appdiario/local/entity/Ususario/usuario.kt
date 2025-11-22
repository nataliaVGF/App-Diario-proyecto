package mx.edu.utng.appdiario.local.entity.Ususario

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(
    tableName = "usuario",
    indices = [Index(value = ["email"], unique = true)] // 🔹 Hace único el email
)
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val idUsua: Int = 0,
    val nombre: String,
    val apellidoMa: String,
    val apellidoPa: String,
    val fechNaci: String,
    val email: String,
    val password: String,
    val fechaRegistro: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        .format(Date()),
    val tipo: TipoUsuario
)
