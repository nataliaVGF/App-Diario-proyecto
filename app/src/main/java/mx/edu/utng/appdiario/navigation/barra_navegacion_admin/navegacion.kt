package mx.edu.utng.appdiario.navigation.barra_navegacion_admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mx.edu.utng.appdiario.ui.screens.administrador.gestionusuario.GestionUsuarios
import mx.edu.utng.appdiario.ui.screens.administrador.reportes_para_administrador.ReportesAdmin

// 🔹 Pantalla principal con barra inferior
@Composable
fun NavegacionAdmin(navController: NavHostController) {
    // NavController para la barra inferior
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(bottomNavController) }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = "home",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable("home") { HomeScreen(bottomNavController) }
            composable("usuarios") { UsuariosScreen(bottomNavController) }
            composable("reportes") { ReportesScreen(bottomNavController) }
        }
    }
}


// 🔹 Barra inferior
@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        NavItem("home", "🏠", "Home"),
        NavItem("usuarios", "👥", "Usuarios"),
        NavItem("reportes", "📊", "Reportes")
    )

    NavigationBar(containerColor = Color(0xFF6D3B1A)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Text(item.icon, fontSize = 22.sp) },
                label = { Text(item.label, color = Color.White) }
            )
        }
    }
}

// 🔹 Modelos y pantallas internas
data class NavItem(val route: String, val icon: String, val label: String)

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            navController.navigate("login") {
                popUpTo(0) { inclusive = true }
            }
        }) {
            Text("Cerrar sesión")
        }
    }
}

@Composable
fun UsuariosScreen(navController: NavController) {
    GestionUsuarios(navController = navController)
}

@Composable
fun ReportesScreen(navController: NavController) {
    ReportesAdmin(navController = navController)
}
