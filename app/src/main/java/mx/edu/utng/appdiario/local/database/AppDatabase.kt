package mx.edu.utng.appdiario.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mx.edu.utng.appdiario.local.dao.*
import mx.edu.utng.appdiario.local.entity.*
import mx.edu.utng.appdiario.local.entity.Diario.DiarioAudio
import mx.edu.utng.appdiario.local.entity.Diario.DiarioTexto
import mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta
import mx.edu.utng.appdiario.local.entity.Tarjeta.TipoTarjetaConverter
import mx.edu.utng.appdiario.local.entity.Ususario.TipoUsuarioConverter
import mx.edu.utng.appdiario.local.entity.Ususario.Usuario

@Database(
    entities = [
        Usuario::class,
        Tarjeta::class,
        DiarioTexto::class,
        DiarioAudio::class
    ],
    version = 7, // 🔹 Incrementé la versión por los cambios
    exportSchema = false
)
@TypeConverters(
    TipoUsuarioConverter::class,
    TipoTarjetaConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun tarjetaDao(): TarjetaDao
    abstract fun diarioTextoDao(): DiarioTextoDao
    abstract fun diarioAudioDao(): DiarioAudioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "DiarioDB"
                )
                    .fallbackToDestructiveMigration() // 🔹 Borra y recrea la DB si cambia la versión
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}