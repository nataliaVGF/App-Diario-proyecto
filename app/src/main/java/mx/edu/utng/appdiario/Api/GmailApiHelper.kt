package mx.edu.utng.appdiario.ui.screens.auth

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Properties
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class AutoEmailSender(private val context: Context) {

    companion object {
        private const val TAG = "AutoEmailSender"
        // 🔹 CONFIGURA ESTOS DATOS CON TU EMAIL GMAIL
        private const val SMTP_HOST = "smtp.gmail.com"
        private const val SMTP_PORT = "587"
        private const val FROM_EMAIL = "androoz706@gmail.com" // 🔹 REEMPLAZA CON TU EMAIL
        private const val FROM_PASSWORD = "nafi qwvi uuen hygo" // 🔹 CONTRASEÑA DE APLICACIÓN
    }

    //  Obtener la cuenta actualmente autenticada (mantenido por compatibilidad)
    fun getLastSignedInAccount(): Nothing? {
        return null // Ya no usamos Google Sign-In
    }

    // Obtener intent para iniciar sesión (mantenido por compatibilidad)
    fun getSignInIntent(): Nothing? {
        return null // Ya no usamos Google Sign-In
    }

    // Cerrar sesión (mantenido por compatibilidad)
    suspend fun signOut() {
        // No necesita implementación para SMTP
    }


    suspend fun sendPasswordRecoveryEmail(
        toEmail: String,
        recoveryCode: String
    ): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                Log.d(TAG, "🔹 Iniciando envío automático a: $toEmail")

                val props = Properties().apply {
                    put("mail.smtp.host", SMTP_HOST)
                    put("mail.smtp.port", SMTP_PORT)
                    put("mail.smtp.auth", "true")
                    put("mail.smtp.starttls.enable", "true")
                    put("mail.smtp.ssl.trust", SMTP_HOST)
                }

                val session = Session.getInstance(props, object : Authenticator() {
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD)
                    }
                })

                val message = MimeMessage(session).apply {
                    setFrom(InternetAddress(FROM_EMAIL, "App Diario"))
                    setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail))
                    subject = "Código de Recuperación - App Diario"
                    setText(createEmailBody(recoveryCode), "utf-8", "html")
                }

                Transport.send(message)
                Log.d(TAG, "Email AUTOMÁTICO enviado exitosamente a: $toEmail")
                true

            } catch (e: AuthenticationFailedException) {
                Log.e(TAG, "Error de autenticación SMTP: ${e.message}")
                Log.e(TAG, "Verifica FROM_EMAIL y FROM_PASSWORD en AutoEmailSender")
                false
            } catch (e: MessagingException) {
                Log.e(TAG, "Error de mensajería: ${e.message}")
                false
            } catch (e: Exception) {
                Log.e(TAG, "Error general enviando email: ${e.message}")
                e.printStackTrace()
                false
            }
        }
    }

    private fun createEmailBody(recoveryCode: String): String {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <style>
                    body { 
                        font-family: 'Arial', sans-serif; 
                        background-color: #f5f5f5; 
                        margin: 0;
                        padding: 20px;
                        color: #333;
                    }
                    .container { 
                        max-width: 600px;
                        margin: 0 auto;
                        background: white; 
                        padding: 30px;
                        border-radius: 15px;
                        box-shadow: 0 4px 6px rgba(0,0,0,0.1);
                        border: 2px solid #6d3b1a;
                    }
                    .header {
                        text-align: center;
                        background: linear-gradient(135deg, #6d3b1a, #8B5A2B);
                        color: white;
                        padding: 20px;
                        border-radius: 10px 10px 0 0;
                        margin: -30px -30px 20px -30px;
                    }
                    .code-container {
                        background: #f5e6d3;
                        border: 2px dashed #6d3b1a;
                        border-radius: 10px;
                        padding: 20px;
                        margin: 20px 0;
                        text-align: center;
                    }
                    .code { 
                        font-size: 32px; 
                        font-weight: bold; 
                        color: #6d3b1a; 
                        letter-spacing: 5px;
                        font-family: 'Courier New', monospace;
                    }
                    .footer {
                        margin-top: 30px;
                        padding-top: 20px;
                        border-top: 1px solid #ddd;
                        text-align: center;
                        color: #666;
                        font-size: 12px;
                    }
                    .warning {
                        background: #fff3cd;
                        border: 1px solid #ffeaa7;
                        border-radius: 5px;
                        padding: 10px;
                        margin: 15px 0;
                        color: #856404;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>App Diario</h1>
                        <h2>Recuperación de Contraseña</h2>
                    </div>
                    
                    <p>Hola,</p>
                    
                    <p>Has solicitado recuperar tu contraseña en <strong>App Diario</strong>.</p>
                    
                    <p>Tu código de verificación es:</p>
                    
                    <div class="code-container">
                        <div class="code">$recoveryCode</div>
                    </div>
                    
                    <p>Ingresa este código de 6 dígitos en la aplicación para continuar con el proceso de recuperación.</p>
                    
                    <div class="warning">
                           <strong>Importante:</strong> 
                        <ul>
                            <li>El código expirará en 15 minutos</li>
                            <li>No compartas este código con nadie</li>
                            <li>Si no reconoces esta solicitud, ignora este email</li>
                        </ul>
                    </div>
                    
                    <p>Si tienes problemas con el código, puedes solicitar uno nuevo desde la aplicación.</p>
                    
                    <div class="footer">
                        <p>Saludos,<br>
                        <strong>El equipo de App Diario</strong></p>
                        <p>Este email fue enviado automáticamente, por favor no respondas</p>
                    </div>
                </div>
            </body>
            </html>
        """.trimIndent()
    }

    // Métodos de compatibilidad (pueden llamarse pero no hacen nada)
    fun getGmailService(account: Nothing?): Nothing? = null

    private fun String.toBase64Url(): String {
        return android.util.Base64.encodeToString(
            this.toByteArray(Charsets.UTF_8),
            android.util.Base64.URL_SAFE or android.util.Base64.NO_WRAP
        )
    }
}