package mx.edu.utng.appdiario.ui.screens.cliente.diariotexto.listadiarioscreen

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
import androidx.compose.material.icons.filled.Edit
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
import mx.edu.utng.appdiario.repository.DiarioTextoRepository
import mx.edu.utng.appdiario.repository.TarjetaRepository
import mx.edu.utng.appdiario.local.dao.DiarioTextoDao
import mx.edu.utng.appdiario.local.dao.TarjetaDao
import mx.edu.utng.appdiario.ui.screens.auth.loginusuario.SessionManager
import mx.edu.utng.appdiario.viewmodel.ListaDiariosViewModel

@Composable
fun ListaDiariosScreen(
    navController: NavController,
    tarjetaDao: TarjetaDao,
    diarioTextoDao: DiarioTextoDao
) {
    val context = LocalContext.current
    val sessionManager = SessionManager(context)

    // Crear los repositories usando los DAOs que vienen como parámetros
    val tarjetaRepository = remember { TarjetaRepository(tarjetaDao, context) }
    val diarioTextoRepository = remember { DiarioTextoRepository(diarioTextoDao) }

    // Crear el ViewModel
    val viewModel: ListaDiariosViewModel = viewModel(
        factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return ListaDiariosViewModel(
                    tarjetaRepository = tarjetaRepository,
                    diarioTextoRepository = diarioTextoRepository,
                    sessionManager = sessionManager
                ) as T
            }
        }
    )

    val searchText = remember { mutableStateOf("") }
    // CAMBIO: Usar el tipo exacto de la BD como valor por defecto
    val selectedFilter = remember { mutableStateOf("RESETAS") }

    // Observar los estados del ViewModel
    val tarjetas by viewModel.tarjetas.collectAsState()
    val diariosPorTarjeta by viewModel.diariosPorTarjeta.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val userId by viewModel.userId.collectAsState()

    // Estado para controlar el diálogo de confirmación de eliminación
    var diarioAEliminar by remember { mutableStateOf<mx.edu.utng.appdiario.local.entity.Diario.DiarioTexto?>(null) }
    var mostrarDialogoEliminar by remember { mutableStateOf(false) }

    // Debug: Mostrar tipos disponibles
    LaunchedEffect(Unit) {
        viewModel.debugTiposDisponibles()
    }

    // Debug: Monitorear cambios en los estados
    LaunchedEffect(tarjetas) {
        println("🔄 [Screen] tarjetas StateFlow actualizado: ${tarjetas.size} elementos")
        tarjetas.forEachIndexed { index, tarjeta ->
            println("   📄 Tarjeta $index: ID=${tarjeta.idTarjeta}, Título='${tarjeta.titulo}', Tipo='${tarjeta.tipo}'")
            val diarios = diariosPorTarjeta[tarjeta.idTarjeta] ?: emptyList()
            println("   📝 Diarios asociados: ${diarios.size}")
            diarios.forEachIndexed { diarioIndex, diario ->
                println("      📄 Diario $diarioIndex: '${diario.titulo}'")
            }
        }
    }

    LaunchedEffect(isLoading) {
        println("🔄 [Screen] isLoading StateFlow actualizado: $isLoading")
    }

    LaunchedEffect(error) {
        println("🔄 [Screen] error StateFlow actualizado: $error")
    }

    // Cargar diarios cuando cambie el filtro O cuando esté disponible el userId
    LaunchedEffect(selectedFilter.value, userId) {
        if (userId != null) {
            println("DEBUG: Cargando diarios para userId: $userId, filtro: ${selectedFilter.value}")
            viewModel.cargarDiariosPorTipo(selectedFilter.value)
        } else {
            println("DEBUG: userId es null, esperando autenticación...")
        }
    }

    // Manejar errores
    LaunchedEffect(error) {
        if (error != null) {
            // Puedes mostrar un snackbar o dialog de error aquí
            println("Error: $error")
        }
    }

    // Filtrar diarios según el texto de búsqueda
    val todosLosDiariosFiltrados = remember(tarjetas, diariosPorTarjeta, searchText.value) {
        val todosLosDiarios = tarjetas.flatMap { tarjeta ->
            val diarios = diariosPorTarjeta[tarjeta.idTarjeta] ?: emptyList()
            diarios.map { diario ->
                DiarioConTarjeta(diario, tarjeta)
            }
        }

        if (searchText.value.isBlank()) {
            todosLosDiarios
        } else {
            todosLosDiarios.filter { diarioConTarjeta ->
                diarioConTarjeta.diario.titulo?.contains(searchText.value, ignoreCase = true) == true ||
                        diarioConTarjeta.diario.texto?.contains(searchText.value, ignoreCase = true) == true
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
                    text = "Inicia sesión para ver tus diarios",
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
                diarioAEliminar = null
            },
            title = {
                Text("Eliminar Nota", fontWeight = FontWeight.Bold)
            },
            text = {
                Text("¿Estás seguro de que quieres eliminar la nota '${diarioAEliminar?.titulo}'?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        diarioAEliminar?.let { diario ->
                            viewModel.eliminarDiario(diario)
                        }
                        mostrarDialogoEliminar = false
                        diarioAEliminar = null
                    }
                ) {
                    Text("Eliminar", color = Color(0xFFB71C1C))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        mostrarDialogoEliminar = false
                        diarioAEliminar = null
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
                text = "Mis Diarios",
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
                                    text = "Buscar notas...",
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
                        navController.navigate("diario_texto")
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFF8B5A2B), RoundedCornerShape(8.dp))
                ){
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
                    // CAMBIO: Usar los tipos exactos de la BD
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
            // Lista de diarios
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFD9A97C)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (todosLosDiariosFiltrados.isEmpty()) {
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
                                    "No se encontraron notas que coincidan con '${searchText.value}'"
                                } else {
                                    "No hay diarios de tipo ${selectedFilter.value}"
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
                        items(todosLosDiariosFiltrados) { diarioConTarjeta ->
                            DiarioItem(
                                diario = diarioConTarjeta.diario,
                                tarjeta = diarioConTarjeta.tarjeta,
                                onEdit = {
                                    // Navegar a edición pasando el ID del diario
                                    navController.navigate("editar_diario_texto/${diarioConTarjeta.diario.idDiarioTexto}")
                                },
                                onDelete = {
                                    // Mostrar diálogo de confirmación para eliminar
                                    diarioAEliminar = diarioConTarjeta.diario
                                    mostrarDialogoEliminar = true
                                },
                                onCardClick = {
                                    // Navegar a detalle pasando el ID del diario
                                    navController.navigate("detalle_diario/${diarioConTarjeta.diario.idDiarioTexto}")
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

// Data class para agrupar diario con su tarjeta
data class DiarioConTarjeta(
    val diario: mx.edu.utng.appdiario.local.entity.Diario.DiarioTexto,
    val tarjeta: mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta
)

// Componente para item de diario ACTUALIZADO - Ahora muestra el título del diario/nota
@Composable
fun DiarioItem(
    diario: mx.edu.utng.appdiario.local.entity.Diario.DiarioTexto,
    tarjeta: mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    println("🎯 [DiarioItem] Renderizando diario: '${diario.titulo}' de tarjeta: '${tarjeta.titulo}'")

    Card(
        modifier = modifier
            .clickable{ onCardClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header del item - AHORA MUESTRA EL TÍTULO DEL DIARIO/NOTA
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = diario.titulo ?: "Sin título", // CAMBIO: Usar título del diario
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

            // Contenido preview del diario
            diario.texto?.let { texto ->
                if (texto.isNotBlank()) {
                    Text(
                        text = texto,
                        color = Color(0xFF4E2A0E),
                        fontSize = 14.sp,
                        maxLines = 2
                    )
                } else {
                    Text(
                        text = "No hay contenido disponible",
                        color = Color(0xFF4E2A0E).copy(alpha = 0.5f),
                        fontSize = 14.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        maxLines = 2
                    )
                }
            } ?: run {
                Text(
                    text = "No hay contenido disponible",
                    color = Color(0xFF4E2A0E).copy(alpha = 0.5f),
                    fontSize = 14.sp,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    maxLines = 2
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Footer con fecha y botones
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Fecha del diario
                Text(
                    text = "Fecha: ${diario.fechaCreacion ?: "Sin fecha"}",
                    color = Color(0xFF4E2A0E).copy(alpha = 0.7f),
                    fontSize = 12.sp
                )

                Row {
                    // Botón Editar
                    IconButton(onClick = onEdit) {
                        Icon(
                            Icons.Default.Edit,
                            "Editar",
                            tint = Color(0xFF4E2A0E)
                        )
                    }

                    // Botón Eliminar
                    IconButton(onClick = onDelete) {
                        Icon(
                            Icons.Default.Delete,
                            "Eliminar",
                            tint = Color(0xFFB71C1C)
                        )
                    }
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
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}