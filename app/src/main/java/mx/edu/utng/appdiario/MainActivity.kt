package mx.edu.utng.appdiario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import mx.edu.utng.appdiario.navigation.navegacionglobal.NavegacionApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🔹 Habilita el modo "Edge to Edge" (sin barra superior opaca)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.Transparent.toArgb()
        window.navigationBarColor = Color.Transparent.toArgb()

        // 🔹 Contenido principal de Compose
        setContent {
            val navController = rememberNavController()
            NavegacionApp(navController = navController)
        }
    }
}


