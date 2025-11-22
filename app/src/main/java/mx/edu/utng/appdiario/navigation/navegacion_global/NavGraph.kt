package mx.edu.utng.appdiario.navigation.navegacion_global

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import mx.edu.utng.appdiario.Repository.UsuarioRepository
import mx.edu.utng.appdiario.local.database.AppDatabase
import mx.edu.utng.appdiario.navigation.barra_navegacion_admin.NavegacionAdmin
import mx.edu.utng.appdiario.navigation.barra_navegacion_cliente.NavegacionCliente
import mx.edu.utng.appdiario.ui.screens.auth.login_usuario.LoginScreen
import mx.edu.utng.appdiario.ui.screens.administrador.dashboardAdministrador.AdminHome
import mx.edu.utng.appdiario.ui.screens.auth.registro_usuario.Registro
import mx.edu.utng.appdiario.ui.screens.administrador.gestionusuario.GestionUsuarios
import mx.edu.utng.appdiario.ui.screens.administrador.reportes_para_administrador.ReportesAdmin
import mx.edu.utng.appdiario.ui.screens.auth.PasswordRecoveryScreen
import mx.edu.utng.appdiario.ui.screens.auth.CodeVerificationScreen
import mx.edu.utng.appdiario.ui.screens.auth.RecuperacionContrasena.ResetPasswordScreen

object NavRoutes {
    // AUTH ##################################
    const val LOGIN = "login"
    const val REGISTRO = "registro"
    const val PASSWORD_RECOVERY = "password_recovery"
    const val CODE_VERIFICATION = "code_verification"
    const val RESET_PASSWORD = "reset_password"

    // ADMIN ################################
    const val ADMIN_HOME = "adminHome"
    const val USUARIOS = "usuarios"
    const val REPORTES = "reportes"
    const val DASHBOARD_ADMIN = "dashboard_admin"

    // CLIENTE ##########################
    const val HOME_NORMAL = "homeNormal"
    const val BARRA_CLIENTE = "barraCliente"

    // NUEVAS PANTALLAS PARA DIARIOS
    const val DIARIO_TEXTO = "diario_texto"
    const val DIARIO_AUDIO = "diario_audio"
}

@Composable
fun NavegacionApp(navController: NavHostController) {

    // 🔹 SOLO PARA LAS PANTALLAS DE RECUPERACIÓN
    val context = LocalContext.current
    val usuarioRepository = remember {
        UsuarioRepository(AppDatabase.getDatabase(context).usuarioDao())
    }

    // ✅ ELIMINADO: GmailHelper - Ya no se necesita

    NavHost(
        navController = navController,
        startDestination = NavRoutes.LOGIN
    ) {
        // ==================== AUTH SCREENS ====================
        // Pantalla de login
        composable(NavRoutes.LOGIN) {
            LoginScreen(navController = navController)
        }

        // Registro de usuario
        composable(NavRoutes.REGISTRO) {
            Registro(navController = navController)
        }

        // ✅ CORREGIDO: PasswordRecoveryScreen ya no necesita gmailHelper
        composable(NavRoutes.PASSWORD_RECOVERY) {
            PasswordRecoveryScreen(
                navController = navController,
                usuarioRepository = usuarioRepository
                // ✅ ELIMINADO: gmailHelper parameter
            )
        }

        // ✅ CodeVerificationScreen NO necesita usuarioRepository - lo crea internamente
        composable(
            route = "${NavRoutes.CODE_VERIFICATION}/{email}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            CodeVerificationScreen(
                navController = navController,
                email = email
            )
        }

        // ✅ CORREGIDO: ResetPasswordScreen ya no necesita usuarioRepository
        composable(
            route = "${NavRoutes.RESET_PASSWORD}/{email}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            ResetPasswordScreen(
                navController = navController,
                email = email
                // ✅ ELIMINADO: usuarioRepository parameter
            )
        }

        // ==================== ADMIN SCREENS ====================
        // Dashboard del admin con barra inferior
        composable(NavRoutes.DASHBOARD_ADMIN) {
            NavegacionAdmin(navController = navController)
        }

        // Pantalla de administración específica
        composable(NavRoutes.ADMIN_HOME) {
            AdminHome(navController = navController)
        }

        // Pantalla Usuarios
        composable(NavRoutes.USUARIOS) {
            GestionUsuarios(navController = navController)
        }

        // Pantalla Reportes
        composable(NavRoutes.REPORTES) {
            ReportesAdmin(navController = navController)
        }

        // ==================== CLIENTE SCREENS ====================
        // Pantalla casa Cliente (redirige a la barra de navegación)
        composable(NavRoutes.HOME_NORMAL) {
            navController.navigate(NavRoutes.BARRA_CLIENTE) {
                popUpTo(NavRoutes.BARRA_CLIENTE) { inclusive = true }
            }
        }

        // Navegación principal del cliente con barra inferior
        composable(NavRoutes.BARRA_CLIENTE) {
            NavegacionCliente(globalNavController = navController)
        }

        // ==================== DIARIOS SCREENS ====================
        composable(NavRoutes.DIARIO_TEXTO) {
            // Redirige a la barra del cliente donde está el flujo de diarios
            navController.navigate(NavRoutes.BARRA_CLIENTE)
        }

        composable(NavRoutes.DIARIO_AUDIO) {
            // Redirige a la barra del cliente donde está el flujo de diarios
            navController.navigate(NavRoutes.BARRA_CLIENTE)
        }
    }
}