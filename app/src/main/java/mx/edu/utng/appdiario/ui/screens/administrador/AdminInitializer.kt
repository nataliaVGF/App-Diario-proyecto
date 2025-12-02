package mx.edu.utng.appdiario.ui.screens.administrador

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.repository.UsuarioRepository

class AdminInitializer(
    private val context: Context,
    private val usuarioRepository: UsuarioRepository
) {
    private val prefs: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    private val scope = CoroutineScope(Dispatchers.IO)

    companion object {
        private const val KEY_ADMIN_CREATED = "admin_created"
    }

    fun initializeAdminIfNeeded() {
        scope.launch {
            try {
                // Verificar si ya se creó el admin en una instalación previa
                val adminCreated = prefs.getBoolean(KEY_ADMIN_CREATED, false)

                if (!adminCreated) {
                    // Verificar si ya existe un admin en la base de datos
                    val existeAdmin = usuarioRepository.existeAdmin()

                    if (!existeAdmin) {
                        // Crear el usuario admin
                        usuarioRepository.crearUsuarioAdmin()
                        println("✅ Usuario admin creado exitosamente")
                    }

                    // Marcar que ya se intentó crear el admin
                    prefs.edit().putBoolean(KEY_ADMIN_CREATED, true).apply()
                }
            } catch (e: Exception) {
                println("❌ Error al inicializar admin: ${e.message}")
            }
        }
    }

    // Método para resetear (útil para testing)
    fun resetAdminFlag() {
        prefs.edit().putBoolean(KEY_ADMIN_CREATED, false).apply()
    }
}