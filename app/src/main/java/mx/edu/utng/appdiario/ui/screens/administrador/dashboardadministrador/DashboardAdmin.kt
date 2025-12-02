package mx.edu.utng.appdiario.ui.screens.administrador.dashboardadministrador

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import mx.edu.utng.appdiario.navigation.navegacionglobal.NavRoutes
import mx.edu.utng.appdiario.ui.screens.administrador.dashboardadministrador.viewmodel.DashboardViewModel
import mx.edu.utng.appdiario.ui.screens.administrador.dashboardadministrador.viewmodel.DashboardViewModelFactory
import mx.edu.utng.appdiario.ui.screens.administrador.gestionusuario.GestionUsuarios
import mx.edu.utng.appdiario.ui.screens.administrador.reportesparaadministrador.ReportesAdmin


@Composable

fun AdminHome(navController: NavController) {
    var selectedTab by remember { mutableStateOf("Inicio") }
    val context = LocalContext.current
    val viewModel: DashboardViewModel = viewModel(
        factory = DashboardViewModelFactory(context)
    )
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = { TopAdmin(navController = navController) },
        bottomBar = {
            BottomNavBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5E6D3))
                .padding(innerPadding)
        ) {

            Titulo()
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                when (selectedTab) {
                    "Inicio" -> {
                        LaunchedEffect(Unit) {
                            viewModel.actualizarDatosDashboard()
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            DashboardCard(
                                title = "Usuarios Registrados",
                                value = state.cantidadUsuarios.toString(),
                                subtitle = "Usuarios Activos en la App"
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            DashboardCard(
                                title = "Sección Popular",
                                value = state.tipoTarjetaMasUsado,
                                subtitle = "Más consultada"
                            )
                        }
                    }
                    "Usuarios" -> {GestionUsuarios(navController = navController) }
                    "Reportes" -> {ReportesAdmin(navController = navController) }
                }
            }
        }
    }
}


// ✅ Barra superior
@Composable
fun TopAdmin(navController: NavController) {
    // 🔹 Usamos Surface para adaptabilidad y sombra ligera
    Surface(
        color = Color(0xFF6D3B1A),
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp, max = 70.dp) // 🔹 Altura flexible (responsive)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 🔹 Texto o logo opcional (izquierda)
            Text(
                text = "Panel Admin",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            // 🔹 Botón de cierre de sesión (derecha)
            Button(
                onClick = {
                    navController.navigate(NavRoutes.LOGIN)

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD2A679)),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Cerrar Sesión",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}



// ✅ Título centrado
@Composable
fun Titulo() {
    Text(
        text = "¡Bienvenido Administrador!",
        color = Color(0xFF4E2A0E),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        textAlign = TextAlign.Center
    )
}


// ✅ Dashboard card
@Composable
fun DashboardCard(
    title: String,
    value: String,
    subtitle: String,
) {
    Box(modifier = Modifier.padding(16.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFD9A97C)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = title,
                    tint = Color(0xFF4E2A0E),
                    modifier = Modifier.size(40.dp)
                )
                Text(title, fontWeight = FontWeight.Bold, color = Color(0xFF4E2A0E))
                Text(
                    value,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4E2A0E)
                )
                Text(subtitle, color = Color(0xFF4E2A0E))
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B5A2B))
                ) {
                    Text("Ver Más", color = Color.White)
                }
            }
        }
    }
}


// ✅ BOTTOM NAVBAR YA FUERA DEL DashboardCard ✅
@Composable
fun BottomNavBar(
    selectedTab: String,
    onTabSelected: (String) -> Unit,
) {
    NavigationBar(
        containerColor = Color(0xFF6D3B1A),
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = selectedTab == "Inicio",
            onClick = { onTabSelected("Inicio") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio", tint = Color.White) },
            label = { Text("Inicio", color = Color.White) }
        )
        NavigationBarItem(
            selected = selectedTab == "Usuarios",
            onClick = { onTabSelected("Usuarios") },
            icon = { Icon(Icons.Default.Person, contentDescription = "Usuarios", tint = Color.White) },
            label = { Text("Usuarios", color = Color.White) }
        )
        NavigationBarItem(
            selected = selectedTab == "Reportes",
            onClick = { onTabSelected("Reportes") },
            icon = { Icon(Icons.Default.Check, contentDescription = "Reportes", tint = Color.White) },
            label = { Text("Reportes", color = Color.White) }
        )
    }
}
