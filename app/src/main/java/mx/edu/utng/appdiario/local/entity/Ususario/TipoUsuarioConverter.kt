package mx.edu.utng.appdiario.local.entity.Ususario

import androidx.room.TypeConverter

class TipoUsuarioConverter {

    @TypeConverter
    fun fromTipoUsuario(tipo: TipoUsuario): String {
        return tipo.name  // Guarda el enum como texto ("ADMIN" o "NORMAL")
    }

    @TypeConverter
    fun toTipoUsuario(value: String): TipoUsuario {
        return TipoUsuario.valueOf(value)  // Convierte el texto de nuevo al enum
    }
}