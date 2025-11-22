package mx.edu.utng.appdiario.ui.screens.auth.RecuperacionContrasena

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecoveryCodeManager(private val context: Context) {

    companion object {
        private const val PREFS_NAME = "recovery_codes"
        private const val KEY_CODE = "recovery_code"
        private const val KEY_EMAIL = "recovery_email"
        private const val KEY_TIMESTAMP = "recovery_timestamp"
        private const val CODE_EXPIRATION_TIME = 15 * 60 * 1000 // 15 minutos
    }

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    suspend fun saveRecoveryCode(email: String, code: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                prefs.edit().apply {
                    putString(KEY_CODE, code)
                    putString(KEY_EMAIL, email)
                    putLong(KEY_TIMESTAMP, System.currentTimeMillis())
                    apply()
                }
                true
            } catch (e: Exception) {
                false
            }
        }
    }

    suspend fun verifyRecoveryCode(email: String, code: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val savedCode = prefs.getString(KEY_CODE, null)
                val savedEmail = prefs.getString(KEY_EMAIL, null)
                val timestamp = prefs.getLong(KEY_TIMESTAMP, 0)

                // Verificar si el código ha expirado
                val isExpired = System.currentTimeMillis() - timestamp > CODE_EXPIRATION_TIME

                if (isExpired) {
                    return@withContext false
                }

                // Verificar que coincidan email y código
                savedCode == code && savedEmail == email
            } catch (e: Exception) {
                false
            }
        }
    }

    suspend fun clearRecoveryCode() {
        withContext(Dispatchers.IO) {
            prefs.edit().clear().apply()
        }
    }

    suspend fun isCodeExpired(): Boolean {
        return withContext(Dispatchers.IO) {
            val timestamp = prefs.getLong(KEY_TIMESTAMP, 0)
            System.currentTimeMillis() - timestamp > CODE_EXPIRATION_TIME
        }
    }
}