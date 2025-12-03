package mx.edu.utng.appdiario.navigation.barranavegacioncliente

import DiarioTextoScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.local.database.AppDatabase
import mx.edu.utng.appdiario.ui.screens.auth.loginusuario.SessionManager
import mx.edu.utng.appdiario.ui.screens.cliente.diarioaudio.DiarioAudioScreen
import mx.edu.utng.appdiario.ui.screens.cliente.diariotexto.diariotextoscreen.DiarioTextoViewModel
import mx.edu.utng.appdiario.ui.screens.cliente.dashboardcliente.DashboardScreen
import mx.edu.utng.appdiario.ui.screens.cliente.diarioaudio.detallediarioaudioscreen.DetalleDiarioAudioScreen
import mx.edu.utng.appdiario.ui.screens.cliente.diarioaudio.listadiariosaudioscreen.ListaDiariosAudioScreen
import mx.edu.utng.appdiario.ui.screens.cliente.diariotexto.detallediarioscreen.DetalleDiarioScreen
import mx.edu.utng.appdiario.ui.screens.cliente.diariotexto.listadiarioscreen.ListaDiariosScreen
import mx.edu.utng.appdiario.ui.screens.cliente.perfilUsuario.PerfilUsuario

@Composable
fun NavegacionCliente(globalNavController: NavHostController) {

    val navController = rememberNavController()
    val showBottomBar = rememberSaveable { mutableStateOf(true) }
    val context = LocalContext.current

    // Obtener los DAOs
    val diarioTextoDao = AppDatabase.getDatabase(context).diarioTextoDao()
    val tarjetaDao = AppDatabase.getDatabase(context).tarjetaDao()
    val sessionManager = SessionManager(context)

    // Inicializar el ViewModel del diario texto
    val diarioTextoRepo = mx.edu.utng.appdiario.repository.DiarioTextoRepository(diarioTextoDao)
    val tarjetaRepo = mx.edu.utng.appdiario.repository.TarjetaRepository(tarjetaDao, context)
    val diarioTextoViewModel = remember { DiarioTextoViewModel(diarioTextoRepo, tarjetaRepo) }

    Scaffold(
        bottomBar = { if (showBottomBar.value) BottomNavBar(navController) }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {

            // ---------- HOME ----------
            composable("home") {
                showBottomBar.value = true
                DashboardScreen(
                    localNavController = navController,
                    globalNavController = globalNavController
                )
            }

            // ---------- PERFIL ----------
            composable("usuario") {
                showBottomBar.value = true
                val usuarioDao = AppDatabase.getDatabase(context).usuarioDao()
                val usuarioRepository = UsuarioRepository(usuarioDao)

                PerfilUsuario(
                    navController = navController,
                    showBottomBar = showBottomBar,
                    globalNavController = globalNavController
                )
            }

            // ---------- DIARIO TEXTO ----------

            // LISTA DIARIOS TEXTO - Pasar los DAOs como parámetros
            composable("lista_diarios") {
                showBottomBar.value = true
                ListaDiariosScreen(
                    navController = navController,
                    tarjetaDao = tarjetaDao,
                    diarioTextoDao = diarioTextoDao
                )
            }

            // DETALLE DIARIO TEXTO - Recibe idTarjeta como parámetro
            composable("detalle_diario/{diarioTextoId}") { backStackEntry ->
                showBottomBar.value = true
                val diarioTextoId = backStackEntry.arguments?.getString("diarioTextoId")?.toIntOrNull() ?: 0
                DetalleDiarioScreen(
                    navController = navController,
                    diarioTextoId = diarioTextoId
                )
            }

            // CREAR DIARIO TEXTO - Recibe idTarjeta
            composable("crear_diario_texto/{idTarjeta}") { backStackEntry ->
                showBottomBar.value = false
                val idTarjeta = backStackEntry.arguments?.getString("idTarjeta")?.toIntOrNull()
                DiarioTextoScreen(
                    navController = navController,
                    viewModel = diarioTextoViewModel,
                    sessionManager = sessionManager,
                    modoEdicion = false
                )
            }

            // EDITAR DIARIO TEXTO - Recibe idDiarioTexto
            composable("editar_diario_texto/{idDiarioTexto}") { backStackEntry ->
                showBottomBar.value = false
                val idDiarioTexto = backStackEntry.arguments?.getString("idDiarioTexto")?.toIntOrNull()
                DiarioTextoScreen(
                    navController = navController,
                    viewModel = diarioTextoViewModel,
                    sessionManager = sessionManager,
                    idDiarioTexto = idDiarioTexto,
                    modoEdicion = true
                )
            }

            // DIARIO TEXTO (pantalla principal sin parámetros)
            composable("diario_texto") {
                showBottomBar.value = true
                DiarioTextoScreen(
                    navController = navController,
                    viewModel = diarioTextoViewModel,
                    sessionManager = sessionManager
                )
            }

            // ---------- DIARIO AUDIO ----------
            composable("diario_audio") {
                showBottomBar.value = true
                DiarioAudioScreen(navController = navController)
            }

            // LISTA AUDIO
            composable("lista_diarios_audio") {
                showBottomBar.value = true
                ListaDiariosAudioScreen(navController = navController)
            }

            // DETALLE AUDIO
// En tu archivo de navegación (barra_navegacion_cliente)
            composable("detalle_diario_audio/{audioId}") { backStackEntry ->
                showBottomBar.value = true
                val audioId = backStackEntry.arguments?.getString("audioId")?.toIntOrNull() ?: 0
                DetalleDiarioAudioScreen(
                    navController = navController,
                    audioId = audioId
                )
            }

            // CREAR AUDIO
            composable("crear_diario_audio/{idTarjeta}") { backStackEntry ->
                showBottomBar.value = false
                val idTarjeta = backStackEntry.arguments?.getString("idTarjeta")?.toIntOrNull()
                DiarioAudioScreen(
                    navController = navController,
                    //idTarjeta = idTarjeta,
                    //modoEdicion = false
                )
            }

            // EDITAR AUDIO
            composable("editar_diario_audio/{idDiarioAudio}") { backStackEntry ->
                showBottomBar.value = false
                val idDiarioAudio = backStackEntry.arguments?.getString("idDiarioAudio")?.toIntOrNull()
                DiarioAudioScreen(
                    navController = navController,
                    //idDiarioAudio = idDiarioAudio,
                    //modoEdicion = true
                )
            }
        }
    }
}

@Composable
fun BottomNavBar(navController: NavHostController) {

    val items = listOf(
        NavItem("home", Icons.Default.Home, "Inicio"),
        NavItem("usuario", Icons.Default.Person, "Usuario")
    )

    NavigationBar(containerColor = Color(0xFF6D3B1A)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(0)
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = Color.White
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.White,
                    indicatorColor = Color(0xFF8D4E25)
                )
            )
        }
    }
}

data class NavItem(val route: String, val icon: ImageVector, val label: String)