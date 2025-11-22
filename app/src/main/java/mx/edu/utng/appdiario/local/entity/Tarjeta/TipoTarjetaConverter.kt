package mx.edu.utng.appdiario.local.entity.Tarjeta

import androidx.room.TypeConverter

class TipoTarjetaConverter {

    @TypeConverter
    fun fromTipoTarjeta(tipo: TipoTarjeta): String {
        return tipo.name
    }

    @TypeConverter
    fun toTipoTarjeta(value: String): TipoTarjeta {
        return TipoTarjeta.valueOf(value)
    }
}