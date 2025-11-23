package mx.edu.utng.appdiario.ui.screens.cliente.diarioAudio.detalleDiarioAudioScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
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
import kotlinx.coroutines.delay
import mx.edu.utng.appdiario.Repository.DiarioAudioRepository
import mx.edu.utng.appdiario.Repository.TarjetaRepository
import mx.edu.utng.appdiario.audio.AudioManager
import mx.edu.utng.appdiario.local.database.AppDatabase
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DetalleDiarioAudioScreen(
    navController: NavController,
    audioId: Int
) {
    // Obtener el contexto
    val context = LocalContext.current

    // Crear repositorios manualmente
    val diarioAudioDao = AppDatabase.getDatabase(context).diarioAudioDao()
    val tarjetaDao = AppDatabase.getDatabase(context).tarjetaDao()

    val diarioAudioRepository = remember { DiarioAudioRepository(diarioAudioDao) }
    val tarjetaRepository = remember { TarjetaRepository(tarjetaDao, context) }

    // Crear ViewModel manualmente
    val viewModel: DetalleDiarioAudioViewModel = remember {
        DetalleDiarioAudioViewModel(diarioAudioRepository, tarjetaRepository)
    }

    // Estados del ViewModel
    val diarioAudio by viewModel.diarioAudio.collectAsState()
    val tarjeta by viewModel.tarjeta.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val isPlaying by viewModel.isPlaying.collectAsState()
    val currentPosition by viewModel.currentPosition.collectAsState()

    // AudioManager para reproducción
    val audioManager = remember { AudioManager(context) }

    // Cargar datos al iniciar
    LaunchedEffect(audioId) {
        if (!viewModel.datosCargados()) {
            viewModel.cargarDiarioAudio(audioId)
        }
    }

    // Manejar reproducción - VERSIÓN CORREGIDA
    LaunchedEffect(isPlaying) {
        if (isPlaying && diarioAudio != null) {
            val audioFilePath = diarioAudio!!.archivo
            println("🎵 [DetalleScreen] Iniciando reproducción: $audioFilePath")

            // Verificar si el archivo es reproducible
            if (audioManager.isAudioFilePlayable(audioFilePath)) {
                audioManager.startPlaying(audioFilePath) {
                    // Callback cuando termina la reproducción
                    viewModel.setPlayingState(false)
                    viewModel.updateCurrentPosition(0)
                    println("✅ [DetalleScreen] Reproducción completada")
                }
            } else {
                println("❌ [DetalleScreen] Archivo no reproducible: $audioFilePath")
                viewModel.setPlayingState(false)
                // Mostrar error
                viewModel.cargarDiarioAudio(audioId) // Esto mostrará el error en el ViewModel
            }
        } else if (!isPlaying) {
            // Pausar reproducción
            audioManager.stopPlaying()
            println("⏸️ [DetalleScreen] Reproducción detenida")
        }
    }

    // Simular progreso de reproducción
    LaunchedEffect(isPlaying) {
        if (isPlaying && diarioAudio != null) {
            val duracionTotal = diarioAudio!!.audioDuracion
            while (isPlaying && viewModel.currentPosition.value < duracionTotal) {
                delay(1000) // Actualizar cada segundo
                val currentPos = viewModel.currentPosition.value + 1
                viewModel.updateCurrentPosition(currentPos)

                // Si llegamos al final, detener
                if (currentPos >= duracionTotal) {
                    viewModel.setPlayingState(false)
                    viewModel.updateCurrentPosition(0)
                    break
                }
            }
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
            onRetry = { viewModel.cargarDiarioAudio(audioId) },
            onBack = { navController.popBackStack() }
        )
        return
    }

    if (diarioAudio == null) {
        EmptyStateScreen(onBack = { navController.popBackStack() })
        return
    }

    // Datos reales del audio
    val audio = diarioAudio!!
    val fechaFormateada = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
    val duracionFormateada = viewModel.obtenerDuracionFormateada()
    val tamañoFormateado = viewModel.obtenerTamañoArchivoFormateado()
    val nombreArchivo = viewModel.obtenerNombreArchivo()
    val tipoTarjeta = tarjeta?.tipo?.name ?: "Audio"

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
            TextButton(
                onClick = { showDeleteDialog = true },
                colors = ButtonDefaults.textButtonColors(contentColor = Color(0xFFD32F2F))
            ) {
                Text("Eliminar")
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
                text = "Fecha: $fechaFormateada",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        }

        // Card principal con el contenido de audio REAL
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
                // Header con título REAL y tipo REAL
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = audio.titulo,
                        color = Color(0xFF4E2A0E),
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        modifier = Modifier.weight(1f)
                    )

                    // Badge del tipo REAL
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

                Spacer(modifier = Modifier.height(20.dp))

                // Información del audio REAL
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Información del Audio:",
                            color = Color(0xFF4E2A0E),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        // Detalles REALES del audio
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Duración:",
                                color = Color(0xFF4E2A0E),
                                fontSize = 14.sp
                            )
                            Text(
                                text = duracionFormateada,
                                color = Color(0xFF4E2A0E),
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Tamaño:",
                                color = Color(0xFF4E2A0E),
                                fontSize = 14.sp
                            )
                            Text(
                                text = tamañoFormateado,
                                color = Color(0xFF4E2A0E),
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Archivo:",
                                color = Color(0xFF4E2A0E),
                                fontSize = 14.sp
                            )
                            Text(
                                text = nombreArchivo,
                                color = Color(0xFF4E2A0E),
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Formato:",
                                color = Color(0xFF4E2A0E),
                                fontSize = 14.sp
                            )
                            Text(
                                text = "M4A",
                                color = Color(0xFF4E2A0E),
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Visualización de la onda de audio
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        // Simulación de onda de audio
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            repeat(20) { index ->
                                val height = (20 + (index % 5) * 8).dp
                                Box(
                                    modifier = Modifier
                                        .width(4.dp)
                                        .height(height)
                                        .background(
                                            if (isPlaying && index <= currentPosition / 2)
                                                Color(0xFF8B5A2B)
                                            else
                                                Color(0xFFD9A97C),
                                            RoundedCornerShape(2.dp)
                                        )
                                )
                            }
                        }

                        // Indicador de tiempo REAL
                        if (isPlaying) {
                            Text(
                                text = "${currentPosition}s / ${audio.audioDuracion}s",
                                color = Color(0xFF4E2A0E),
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                modifier = Modifier.align(Alignment.TopCenter)
                            )
                        } else {
                            Text(
                                text = "Duración total: ${audio.audioDuracion}s",
                                color = Color(0xFF4E2A0E),
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                modifier = Modifier.align(Alignment.TopCenter)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Botón de reproducción grande - VERSIÓN MEJORADA
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = {
                            println("🔄 [DEBUG] Click en botón reproducción")
                            println("🔄 [DEBUG] Estado actual - isPlaying: $isPlaying")
                            println("🔄 [DEBUG] DiarioAudio: $diarioAudio")

                            if (diarioAudio != null) {
                                println("🔄 [DEBUG] Ruta archivo: ${diarioAudio!!.archivo}")
                                val archivo = File(diarioAudio!!.archivo)
                                println("🔄 [DEBUG] Archivo existe: ${archivo.exists()}")
                                println("🔄 [DEBUG] Tamaño archivo: ${archivo.length()} bytes")
                                println("🔄 [DEBUG] Es reproducible: ${audioManager.isAudioFilePlayable(diarioAudio!!.archivo)}")
                            }

                            if (isPlaying) {
                                viewModel.setPlayingState(false)
                                println("⏸️ [DetalleScreen] Usuario pausó la reproducción")
                            } else {
                                if (diarioAudio != null) {
                                    if (audioManager.isAudioFilePlayable(diarioAudio!!.archivo)) {
                                        viewModel.setPlayingState(true)
                                        println("▶️ [DetalleScreen] Usuario inició reproducción")
                                    } else {
                                        println("❌ [DetalleScreen] Archivo no reproducible")
                                        viewModel.cargarDiarioAudio(audioId) // Forzar recarga para mostrar error
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .size(80.dp)
                            .background(
                                if (isPlaying) Color(0xFF8B5A2B) else Color(0xFF6D3B1A),
                                RoundedCornerShape(40.dp)
                            )
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) "Pausar" else "Reproducir",
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }

                // Estado de reproducción
                Text(
                    text = if (isPlaying) "Reproduciendo..." else "Presiona para reproducir",
                    color = Color(0xFF4E2A0E),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

            }
        }
    }

    // Diálogo de confirmación para eliminar
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Eliminar Audio") },
            text = { Text("¿Estás seguro de que quieres eliminar este audio? Esta acción no se puede deshacer.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        viewModel.eliminarDiarioAudio {
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

    // Cleanup cuando se desmonta el composable
    DisposableEffect(Unit) {
        onDispose {
            audioManager.cleanup()
        }
    }
}

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
                "Cargando audio...",
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
                "🎵 Audio no encontrado",
                color = Color(0xFF4E2A0E),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "El audio que buscas no está disponible o ha sido eliminado.",
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