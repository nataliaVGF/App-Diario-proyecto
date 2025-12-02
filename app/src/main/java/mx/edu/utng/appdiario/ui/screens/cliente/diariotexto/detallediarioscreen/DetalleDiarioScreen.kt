package mx.edu.utng.appdiario.ui.screens.cliente.diariotexto.detallediarioscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mx.edu.utng.appdiario.repository.DiarioTextoRepository
import mx.edu.utng.appdiario.repository.TarjetaRepository
import mx.edu.utng.appdiario.local.database.AppDatabase

@Composable
fun DetalleDiarioScreen(
    navController: NavController,
    diarioTextoId: Int
) {
    // Obtener el contexto
    val context = LocalContext.current

    // Crear repositorios manualmente
    val diarioTextoDao = AppDatabase.getDatabase(context).diarioTextoDao()
    val tarjetaDao = AppDatabase.getDatabase(context).tarjetaDao()

    val diarioTextoRepository = remember { DiarioTextoRepository(diarioTextoDao) }
    val tarjetaRepository = remember { TarjetaRepository(tarjetaDao, context) }

    // Crear ViewModel manualmente
    val viewModel: DetalleDiarioTextoViewModel = remember {
        DetalleDiarioTextoViewModel(diarioTextoRepository, tarjetaRepository)
    }

    // Estados del ViewModel
    val diarioTexto by viewModel.diarioTexto.collectAsState()
    val tarjeta by viewModel.tarjeta.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    // Cargar datos al iniciar
    LaunchedEffect(diarioTextoId) {
        if (!viewModel.datosCargados()) {
            viewModel.cargarDiarioTexto(diarioTextoId)
        }
    }

    // Estados locales para la UI
    var showDeleteDialog by remember { mutableStateOf(false) }

    // Pantallas de estado
    if (isLoading) {
        LoadingScreen()
        return
    }

    if (error != null) {
        ErrorScreen(
            error = error!!,
            onRetry = { viewModel.cargarDiarioTexto(diarioTextoId) },
            onBack = { navController.popBackStack() }
        )
        return
    }

    if (diarioTexto == null) {
        EmptyStateScreen(onBack = { navController.popBackStack() })
        return
    }

    // Datos reales del diario
    val titulo = viewModel.obtenerTitulo()
    val contenido = viewModel.obtenerContenidoConFormato()
    val tipoTarjeta = viewModel.obtenerTipoTarjeta()
    val fechaCreacion = viewModel.obtenerFechaCreacionFormateada()
    val estadisticas = viewModel.obtenerEstadisticasContenido()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3))
            .padding(16.dp)
    ) {
        // Header con botón volver y opciones
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botón volver
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFF6D3B1A), RoundedCornerShape(12.dp))
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    "Volver",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Menú de opciones
            Row {
                // Botón editar
                IconButton(
                    onClick = {
                        // Navegar a pantalla de edición
                        navController.navigate("editar_diario_texto/$diarioTextoId")
                    },
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0xFF8B5A2B), RoundedCornerShape(12.dp))
                ) {
                    Icon(
                        Icons.Default.Edit,
                        "Editar",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Botón eliminar
                IconButton(
                    onClick = { showDeleteDialog = true },
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0xFFD32F2F), RoundedCornerShape(12.dp))
                ) {
                    Icon(
                        Icons.Default.Delete,
                        "Eliminar",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        // Fecha
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF6D3B1A)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Creado: $fechaCreacion",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        }

        // Card principal con el contenido
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFD9A97C)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                // Header con título y tipo
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = titulo,
                        color = Color(0xFF4E2A0E),
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        modifier = Modifier.weight(1f)
                    )

                    // Badge del tipo
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF6D3B1A)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = tipoTarjeta,
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))




                Spacer(modifier = Modifier.height(20.dp))

                // Contenido del diario
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Contenido:",
                            color = Color(0xFF4E2A0E),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        // Área de texto con scroll
                        Text(
                            text = contenido,
                            color = Color(0xFF4E2A0E),
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        )
                    }
                }
            }
        }
    }

    // Diálogo de confirmación para eliminar
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Eliminar Diario") },
            text = { Text("¿Estás seguro de que quieres eliminar este diario? Esta acción no se puede deshacer.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        viewModel.eliminarDiarioTexto {
                            navController.popBackStack()
                        }
                    }
                ) {
                    Text("Eliminar", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDeleteDialog = false }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

// Las funciones LoadingScreen, ErrorScreen y EmptyStateScreen se mantienen igual que en el ejemplo anterior
@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = Color(0xFF6D3B1A))
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Cargando diario...",
                color = Color(0xFF4E2A0E),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ErrorScreen(error: String, onRetry: () -> Unit, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "❌ Error",
                color = Color(0xFFD32F2F),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                error,
                color = Color(0xFF4E2A0E),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D3B1A))
                ) {
                    Text("Volver")
                }
                Button(
                    onClick = onRetry,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B5A2B))
                ) {
                    Text("Reintentar")
                }
            }
        }
    }
}

@Composable
fun EmptyStateScreen(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "📝 Diario no encontrado",
                color = Color(0xFF4E2A0E),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "El diario que buscas no está disponible o ha sido eliminado.",
                color = Color(0xFF4E2A0E),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D3B1A))
            ) {
                Text("Volver al inicio")
            }
        }
    }
}