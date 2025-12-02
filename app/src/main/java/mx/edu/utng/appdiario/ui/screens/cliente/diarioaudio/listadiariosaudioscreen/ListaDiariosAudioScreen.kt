package mx.edu.utng.appdiario.ui.screens.cliente.diarioaudio.listadiariosaudioscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import mx.edu.utng.appdiario.local.database.AppDatabase
import mx.edu.utng.appdiario.ui.screens.auth.loginusuario.SessionManager

@Composable
fun ListaDiariosAudioScreen(navController: NavController) {
    val context = LocalContext.current

    // Obtener DAOs y crear ViewModel
    val diarioAudioDao = AppDatabase.getDatabase(context).diarioAudioDao()
    val tarjetaDao = AppDatabase.getDatabase(context).tarjetaDao()
    val sessionManager = SessionManager(context)

    val viewModel: ListaDiariosAudioViewModel = viewModel(
        factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return ListaDiariosAudioViewModel(
                    tarjetaRepository = mx.edu.utng.appdiario.repository.TarjetaRepository(tarjetaDao, context),
                    diarioAudioRepository = mx.edu.utng.appdiario.repository.DiarioAudioRepository(diarioAudioDao),
                    sessionManager = sessionManager
                ) as T
            }
        }
    )

    val searchText = remember { mutableStateOf("") }
    // ACTUALIZADO: Usar tipos exactos de la BD
    val selectedFilter = remember { mutableStateOf("RESETAS") }

    // Observar los estados del ViewModel
    val tarjetas by viewModel.tarjetas.collectAsState()
    val audiosPorTarjeta by viewModel.audiosPorTarjeta.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val userId by viewModel.userId.collectAsState()

    // Estado para controlar el diálogo de confirmación de eliminación
    var audioAEliminar by remember { mutableStateOf<mx.edu.utng.appdiario.local.entity.Diario.DiarioAudio?>(null) }
    var mostrarDialogoEliminar by remember { mutableStateOf(false) }

    // Debug: Mostrar tipos disponibles
    LaunchedEffect(Unit) {
        viewModel.debugTiposDisponibles()
    }

    // Debug: Monitorear cambios en los estados
    LaunchedEffect(tarjetas) {
        println("🎵 [AudioScreen] tarjetas StateFlow actualizado: ${tarjetas.size} elementos")
        tarjetas.forEachIndexed { index, tarjeta ->
            println("   📄 Tarjeta $index: ID=${tarjeta.idTarjeta}, Título='${tarjeta.titulo}', Tipo='${tarjeta.tipo}'")
            val audios = audiosPorTarjeta[tarjeta.idTarjeta] ?: emptyList()
            println("   🎵 Audios asociados: ${audios.size}")
            audios.forEachIndexed { audioIndex, audio ->
                println("      🎵 Audio $audioIndex: '${audio.titulo}'")
            }
        }
    }

    LaunchedEffect(isLoading) {
        println("🎵 [AudioScreen] isLoading StateFlow actualizado: $isLoading")
    }

    LaunchedEffect(error) {
        println("🎵 [AudioScreen] error StateFlow actualizado: $error")
    }

    // Cargar audios cuando cambie el filtro
    LaunchedEffect(selectedFilter.value, userId) {
        if (userId != null) {
            println("🎵 [AudioScreen] Cargando audios para userId: $userId, filtro: ${selectedFilter.value}")
            viewModel.cargarAudiosPorTipo(selectedFilter.value)
        } else {
            println("🎵 [AudioScreen] userId es null, esperando autenticación...")
        }
    }

    // Manejar errores
    LaunchedEffect(error) {
        if (error != null) {
            println("🎵 [AudioScreen] Error: $error")
        }
    }

    // Filtrar audios según el texto de búsqueda
    val todosLosAudiosFiltrados = remember(tarjetas, audiosPorTarjeta, searchText.value) {
        val todosLosAudios = tarjetas.flatMap { tarjeta ->
            val audios = audiosPorTarjeta[tarjeta.idTarjeta] ?: emptyList()
            audios.map { audio ->
                AudioConTarjeta(audio, tarjeta)
            }
        }

        if (searchText.value.isBlank()) {
            todosLosAudios
        } else {
            todosLosAudios.filter { audioConTarjeta ->
                audioConTarjeta.audio.titulo.contains(searchText.value, ignoreCase = true)
            }
        }
    }

    // Mostrar estado de no autenticado
    if (userId == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5E6D3))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Usuario no autenticado",
                    color = Color(0xFF4E2A0E),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Inicia sesión para ver tus audios",
                    color = Color(0xFF4E2A0E).copy(alpha = 0.7f),
                    fontSize = 14.sp
                )
            }
        }
        return
    }

    // Diálogo de confirmación para eliminar
    if (mostrarDialogoEliminar) {
        AlertDialog(
            onDismissRequest = {
                mostrarDialogoEliminar = false
                audioAEliminar = null
            },
            title = {
                Text("Eliminar Audio", fontWeight = FontWeight.Bold)
            },
            text = {
                Text("¿Estás seguro de que quieres eliminar el audio '${audioAEliminar?.titulo}'?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        audioAEliminar?.let { audio ->
                            viewModel.eliminarAudio(audio)
                        }
                        mostrarDialogoEliminar = false
                        audioAEliminar = null
                    }
                ) {
                    Text("Eliminar", color = Color(0xFFB71C1C))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        mostrarDialogoEliminar = false
                        audioAEliminar = null
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3))
            .padding(16.dp)
    ) {
        // Header
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
                text = "Mis Diarios de Audio",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        }

        // Barra de búsqueda compacta
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFD9A97C)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar",
                    tint = Color(0xFF4E2A0E),
                    modifier = Modifier.padding(end = 8.dp)
                )

                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    BasicTextField(
                        value = searchText.value,
                        onValueChange = { searchText.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        textStyle = TextStyle(
                            color = Color(0xFF4E2A0E),
                            fontSize = 16.sp
                        ),
                        singleLine = true,
                        decorationBox = { innerTextField ->
                            if (searchText.value.isEmpty()) {
                                Text(
                                    text = "Buscar audios...",
                                    color = Color(0xFF4E2A0E).copy(alpha = 0.5f),
                                    fontSize = 16.sp
                                )
                            }
                            innerTextField()
                        }
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Botón + compacto
                IconButton(
                    onClick = {
                        navController.navigate("diario_audio")
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFF8B5A2B), RoundedCornerShape(8.dp))
                ) {
                    Icon(
                        Icons.Default.Add,
                        "Agregar",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        // Filtros Radio - ACTUALIZADOS para coincidir con la BD
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFD9A97C)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = "Filtrar por:",
                    color = Color(0xFF4E2A0E),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FilterRadioOption(
                        text = "RESETA",
                        selected = selectedFilter.value == "RESETAS",
                        onSelected = { selectedFilter.value = "RESETAS" }
                    )
                    FilterRadioOption(
                        text = "PERSONAL",
                        selected = selectedFilter.value == "PERSONAL",
                        onSelected = { selectedFilter.value = "PERSONAL" }
                    )
                    FilterRadioOption(
                        text = "ACTIVIDAD",
                        selected = selectedFilter.value == "ACTIVIDADES",
                        onSelected = { selectedFilter.value = "ACTIVIDADES" }
                    )
                }

            }
        }

        // Loading state
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF6D3B1A))
            }
        } else {
            // Lista de diarios de audio
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFD9A97C)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (todosLosAudiosFiltrados.isEmpty()) {
                    // Estado vacío
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = if (searchText.value.isNotBlank()) {
                                    "No se encontraron audios que coincidan con '${searchText.value}'"
                                } else {
                                    "No hay audios de tipo ${selectedFilter.value}"
                                },
                                color = Color(0xFF4E2A0E),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Haz clic en el botón + para crear uno nuevo",
                                color = Color(0xFF4E2A0E).copy(alpha = 0.7f),
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        items(todosLosAudiosFiltrados) { audioConTarjeta ->
                            DiarioAudioItem(
                                audio = audioConTarjeta.audio,
                                tarjeta = audioConTarjeta.tarjeta,
                                onDelete = {
                                    // Mostrar diálogo de confirmación para eliminar
                                    audioAEliminar = audioConTarjeta.audio
                                    mostrarDialogoEliminar = true
                                },
                                onItemClick = {
                                    // Navegar a detalle pasando el ID del audio
                                    navController.navigate("detalle_diario_audio/${audioConTarjeta.audio.idDiarioAudio}")
                                },
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

// Data class para agrupar audio con su tarjeta
data class AudioConTarjeta(
    val audio: mx.edu.utng.appdiario.local.entity.Diario.DiarioAudio,
    val tarjeta: mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta
)

// Componente para item de diario de audio ACTUALIZADO
@Composable
fun DiarioAudioItem(
    audio: mx.edu.utng.appdiario.local.entity.Diario.DiarioAudio,
    tarjeta: mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta,
    onDelete: () -> Unit,
    onItemClick: () -> Unit, // CAMBIADO: de onPlayClick a onItemClick
    modifier: Modifier = Modifier
) {
    println("🎵 [DiarioAudioItem] Renderizando audio: '${audio.titulo}' de tarjeta: '${tarjeta.titulo}'")

    Card(
        modifier = modifier
            .clickable { onItemClick() }, // AGREGADO: Toda la tarjeta es clickeable
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header del item - AHORA MUESTRA EL TÍTULO DEL AUDIO
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = audio.titulo,
                    color = Color(0xFF4E2A0E),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )

                // Badge del tipo (de la tarjeta)
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF6D3B1A)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = tarjeta.tipo.name,
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Información del audio - DATOS REALES (ELIMINADO: controles de reproducción)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Duración: ${audio.audioDuracion} segundos",
                        color = Color(0xFF4E2A0E),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Archivo: ${audio.archivo}",
                        color = Color(0xFF4E2A0E).copy(alpha = 0.7f),
                        fontSize = 12.sp,
                        maxLines = 1
                    )
                }

                // AGREGADO: Indicador para tocar
                Text(
                    text = "Tocar para ver detalles →",
                    color = Color(0xFF8B5A2B),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Footer con fecha y botón eliminar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Fecha: ${audio.fechaCreacion}",
                    color = Color(0xFF4E2A0E).copy(alpha = 0.7f),
                    fontSize = 12.sp
                )

                // Botón Eliminar
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        Icons.Default.Delete,
                        "Eliminar",
                        tint = Color(0xFFB71C1C),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

// Componente para opciones de filtro radio
@Composable
fun FilterRadioOption(
    text: String,
    selected: Boolean,
    onSelected: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
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
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}
