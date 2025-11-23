package mx.edu.utng.appdiario.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.Repository.UsuarioRepository
import mx.edu.utng.appdiario.local.dao.*
import mx.edu.utng.appdiario.local.entity.*
import mx.edu.utng.appdiario.local.entity.Diario.DiarioAudio
import mx.edu.utng.appdiario.local.entity.Diario.DiarioTexto
import mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta
import mx.edu.utng.appdiario.local.entity.Tarjeta.TipoTarjetaConverter
import mx.edu.utng.appdiario.local.entity.Ususario.TipoUsuarioConverter
import mx.edu.utng.appdiario.local.entity.Ususario.Usuario
import mx.edu.utng.appdiario.ui.screens.administrador.AdminInitializer

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

                // 🔹 INICIALIZAR ADMIN DESPUÉS DE CREAR LA BASE DE DATOS
                initializeAdmin(context, instance)

                instance
            }
        }

        private fun initializeAdmin(context: Context, database: AppDatabase) {
            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                try {
                    val usuarioRepository = UsuarioRepository(database.usuarioDao())
                    val adminInitializer = AdminInitializer(context, usuarioRepository)
                    adminInitializer.initializeAdminIfNeeded()
                } catch (e: Exception) {
                    println("❌ Error al inicializar admin en AppDatabase: ${e.message}")
                }
            }
        }
    }
}