package mx.edu.utng.appdiario.ui.screens.cliente.diarioAudio

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import mx.edu.utng.appdiario.audio.AudioManager
import mx.edu.utng.appdiario.local.database.AppDatabase
import mx.edu.utng.appdiario.local.entity.Tarjeta.TipoTarjeta
import mx.edu.utng.appdiario.ui.screens.auth.login_usuario.SessionManager
import mx.edu.utng.appdiario.ui.screens.cliente.diarioAudio.DiarioAudioScreen.DiarioAudioViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DiarioAudioScreen(navController: NavHostController) {
    val context = LocalContext.current

    // Obtener DAOs y crear ViewModel
    val diarioAudioDao = AppDatabase.getDatabase(context).diarioAudioDao()
    val tarjetaDao = AppDatabase.getDatabase(context).tarjetaDao()
    val sessionManager = SessionManager(context)

    val viewModel: DiarioAudioViewModel = viewModel(
        factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return DiarioAudioViewModel(
                    diarioAudioRepository = mx.edu.utng.appdiario.Repository.DiarioAudioRepository(diarioAudioDao),
                    tarjetaRepository = mx.edu.utng.appdiario.Repository.TarjetaRepository(tarjetaDao, context)
                ) as T
            }
        }
    )

    val uiState by viewModel.uiState.collectAsState()
    val userId by sessionManager.userIdFlow.collectAsState(initial = null)

    var selectedOption by remember { mutableStateOf("RESETAS") }
    var recordingTime by remember { mutableStateOf(0) }
    var audioRecorded by remember { mutableStateOf(false) }
    var isRecording by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }
    var showPermissionDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Audio Manager
    val audioManager = remember { AudioManager(context) }

    // Launcher para permisos
    val audioPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permiso concedido, iniciar grabación
            startRecording(audioManager) { success ->
                if (success) {
                    isRecording = true
                    recordingTime = 0
                    errorMessage = null
                } else {
                    errorMessage = "Error al iniciar la grabación"
                    isRecording = false
                }
            }
        } else {
            // Permiso denegado
            showPermissionDialog = true
            isRecording = false
        }
    }

    val fechaActual = remember {
        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
    }

    // Controlar el tiempo de grabación
    LaunchedEffect(isRecording) {
        if (isRecording) {
            while (isRecording) {
                delay(1000)
                recordingTime++
            }
        } else {
            // Cuando se detiene la grabación, verificar si hay audio grabado
            if (recordingTime > 0 && audioManager.hasRecording()) {
                audioRecorded = true
            }
        }
    }

    // Cleanup al salir de la pantalla
    DisposableEffect(Unit) {
        onDispose {
            audioManager.cleanup()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
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
                text = "Fecha: $fechaActual",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        }

        // Card principal
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFD9A97C)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // Título de la sección
                Text(
                    text = "Nuevo Diario Audio",
                    color = Color(0xFF4E2A0E),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textAlign = TextAlign.Center
                )

                // Opciones Radio
                Text(
                    text = "Tipo de entrada:",
                    color = Color(0xFF4E2A0E),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        RadioOption(
                            text = "RESETAS",
                            selected = selectedOption == "RESETAS",
                            onSelected = { selectedOption = "RESETAS" }
                        )
                        RadioOption(
                            text = "PERSONAL",
                            selected = selectedOption == "PERSONAL",
                            onSelected = { selectedOption = "PERSONAL" }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        RadioOption(
                            text = "ACTIVIDADES",
                            selected = selectedOption == "ACTIVIDADES",
                            onSelected = { selectedOption = "ACTIVIDADES" }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Campo para título
                TextFieldLabeled(
                    label = "Título:",
                    value = uiState.titulo,
                    onValueChange = { viewModel.actualizarTitulo(it) },
                    singleLine = true,
                    height = 60.dp
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Mostrar mensajes de error locales
                errorMessage?.let { message ->
                    Text(
                        text = message,
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        textAlign = TextAlign.Center
                    )
                }

                // Sección de audio
                AudioSection(
                    isRecording = isRecording,
                    recordingTime = recordingTime,
                    audioRecorded = audioRecorded,
                    isPlaying = isPlaying,
                    onToggleRecording = {
                        if (!audioRecorded) {
                            if (!isRecording) {
                                // Iniciar grabación
                                checkAudioPermission(
                                    context = context,
                                    onPermissionGranted = {
                                        startRecording(audioManager) { success ->
                                            if (success) {
                                                isRecording = true
                                                recordingTime = 0
                                                errorMessage = null
                                            } else {
                                                errorMessage = "Error al iniciar la grabación"
                                                isRecording = false
                                            }
                                        }
                                    },
                                    onPermissionDenied = {
                                        audioPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                                    }
                                )
                            } else {
                                // Detener grabación
                                audioManager.stopRecording()
                                isRecording = false
                            }
                        }
                    },
                    onTogglePlayback = {
                        if (!isPlaying) {
                            // Iniciar reproducción
                            audioManager.startPlaying {
                                isPlaying = false
                            }
                            isPlaying = true
                        } else {
                            // Detener reproducción
                            audioManager.stopPlaying()
                            isPlaying = false
                        }
                    },
                    onDeleteRecording = {
                        // Eliminar grabación
                        audioManager.deleteRecording()
                        isRecording = false
                        recordingTime = 0
                        audioRecorded = false
                        isPlaying = false
                        errorMessage = null
                    },
                    onSave = {
                        if (userId != null && uiState.titulo.isNotBlank() && recordingTime > 0 && audioManager.hasRecording()) {
                            val tipo = when (selectedOption) {
                                "RESETAS" -> TipoTarjeta.RESETAS
                                "PERSONAL" -> TipoTarjeta.PERSONAL
                                "ACTIVIDADES" -> TipoTarjeta.ACTIVIDADES
                                else -> TipoTarjeta.RESETAS
                            }

                            viewModel.actualizarArchivo(audioManager.getAudioFilePath() ?: "")
                            viewModel.actualizarDuracion(recordingTime)

                            viewModel.crearDiarioAudio(userId!!, tipo)

                            navController.navigate("lista_diarios_audio") {
                                popUpTo("lista_diarios_audio") { inclusive = true }
                            }
                        }
                    },
                    canSave = userId != null &&
                            uiState.titulo.isNotBlank() &&
                            recordingTime > 0 &&
                            audioManager.hasRecording()
                )
            }
        }

        // Mostrar error del ViewModel si existe
        val viewModelError by viewModel.error.collectAsState()
        viewModelError?.let { error ->
            Text(
                text = error,
                color = Color(0xFFB71C1C),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
    }

    // Dialog para cuando el permiso es denegado
    if (showPermissionDialog) {
        AlertDialog(
            onDismissRequest = { showPermissionDialog = false },
            title = { Text("Permiso de micrófono requerido") },
            text = { Text("Para grabar audio, necesitas conceder el permiso de micrófono. Ve a Configuración > Aplicaciones > ${context.applicationInfo.loadLabel(context.packageManager)} > Permisos y activa el micrófono.") },
            confirmButton = {
                Button(
                    onClick = { showPermissionDialog = false }
                ) {
                    Text("Entendido")
                }
            }
        )
    }
}

// Función para verificar permisos
private fun checkAudioPermission(
    context: android.content.Context,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val permission = Manifest.permission.RECORD_AUDIO
    val permissionGranted = ContextCompat.checkSelfPermission(context, permission) == android.content.pm.PackageManager.PERMISSION_GRANTED

    if (permissionGranted) {
        onPermissionGranted()
    } else {
        onPermissionDenied()
    }
}

// Función para iniciar grabación
private fun startRecording(
    audioManager: AudioManager,
    onResult: (Boolean) -> Unit
) {
    val filePath = audioManager.startRecording()
    onResult(filePath != null)
}

@Composable
fun AudioSection(
    isRecording: Boolean,
    recordingTime: Int,
    audioRecorded: Boolean,
    isPlaying: Boolean,
    onToggleRecording: () -> Unit,
    onTogglePlayback: () -> Unit,
    onDeleteRecording: () -> Unit,
    onSave: () -> Unit,
    canSave: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Botón de micrófono centrado
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = onToggleRecording,
                enabled = !audioRecorded,
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        when {
                            isRecording -> Color(0xFFB71C1C)
                            audioRecorded -> Color(0xFF9E9E9E)
                            else -> Color(0xFF8B5A2B)
                        },
                        RoundedCornerShape(40.dp)
                    )
            ) {
                Icon(
                    imageVector = when {
                        isRecording -> Icons.Default.MicOff
                        audioRecorded -> Icons.Default.Mic
                        else -> Icons.Default.Mic
                    },
                    contentDescription = when {
                        isRecording -> "Detener grabación"
                        audioRecorded -> "Audio ya grabado"
                        else -> "Iniciar grabación"
                    },
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        // Mensaje informativo debajo del micrófono
        Text(
            text = when {
                isRecording -> "Grabando... $recordingTime s - Presiona nuevamente para detener"
                audioRecorded -> "Audio grabado - Usa los botones de abajo para gestionar"
                else -> "Presiona el micrófono para comenzar a grabar"
            },
            color = Color(0xFF4E2A0E),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        )

        // Área de audio grabado (visible después de grabar)
        if (audioRecorded) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Audio Grabado",
                        color = Color(0xFF4E2A0E),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Duración: ${recordingTime} segundos",
                        color = Color(0xFF4E2A0E),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Controles de reproducción y eliminación
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Botón de reproducción
                        IconButton(
                            onClick = onTogglePlayback,
                            modifier = Modifier
                                .size(60.dp)
                                .background(
                                    if (isPlaying) Color(0xFF8B5A2B) else Color(0xFF4E2A0E),
                                    RoundedCornerShape(30.dp)
                                )
                        ) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Default.Stop else Icons.Default.PlayArrow,
                                contentDescription = if (isPlaying) "Detener" else "Reproducir",
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }

                        // Botón para eliminar grabación
                        IconButton(
                            onClick = onDeleteRecording,
                            modifier = Modifier
                                .size(60.dp)
                                .background(Color(0xFFB71C1C), RoundedCornerShape(30.dp))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Eliminar grabación",
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }

                    if (isPlaying) {
                        Text(
                            text = "Reproduciendo...",
                            color = Color(0xFF4E2A0E),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Botón guardar
            Button(
                onClick = onSave,
                enabled = canSave,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8B5A2B),
                    disabledContainerColor = Color(0xFFCCCCCC)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "GUARDAR DIARIO",
                    color = if (canSave) Color.White else Color(0xFF666666),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        } else if (!isRecording) {
            // Mensaje cuando no hay audio grabado
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Tu audio aparecerá aquí después de grabar",
                        color = Color(0xFF4E2A0E),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RadioOption(
    text: String,
    selected: Boolean,
    onSelected: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        RadioButton(
            selected = selected,
            onClick = onSelected,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0xFF4E2A0E)
            )
        )
        Text(
            text = text,
            color = Color(0xFF4E2A0E),
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun TextFieldLabeled(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean,
    height: Dp
) {
    Column {
        Text(
            text = label,
            color = Color(0xFF4E2A0E),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(height),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            BasicTextField(
                value = value,
                onValueChange = { onValueChange(it) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                textStyle = androidx.compose.ui.text.TextStyle(
                    color = Color(0xFF4E2A0E),
                    fontSize = 16.sp
                ),
                singleLine = singleLine
            )
        }
    }
}