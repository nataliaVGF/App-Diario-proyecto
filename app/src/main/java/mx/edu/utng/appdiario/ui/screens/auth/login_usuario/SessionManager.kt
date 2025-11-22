package mx.edu.utng.appdiario.ui.screens.auth.login_usuario

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SessionManager(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore(name = "user_prefs")
        private val USER_ID_KEY = intPreferencesKey("user_id")
    }

    val userIdFlow: Flow<Int?> = context.dataStore.data.map { prefs ->
        prefs[USER_ID_KEY].also { userId ->
            println("DEBUG: userIdFlow emitido: $userId")
        }
    }

    suspend fun saveUserId(id: Int) {
        println("DEBUG: Guardando userId: $id")
        context.dataStore.edit { prefs ->
            prefs[USER_ID_KEY] = id
        }
        println("DEBUG: userId guardado exitosamente")
    }

    suspend fun clearUserSession() {
        println("DEBUG: Limpiando sesión")
        context.dataStore.edit { prefs ->
            prefs.remove(USER_ID_KEY)
        }
        println("DEBUG: Sesión limpiada exitosamente")
    }
}