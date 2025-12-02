package mx.edu.utng.appdiario.ui.screens.administrador.gestionusuario

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun GestionUsuarios(navController: NavController) {
    val context = LocalContext.current

    val viewModel: GestionUsuariosViewModel = viewModel(
        factory = GestionUsuariosViewModelFactory(context)
    )

    val state by viewModel.state.collectAsStateWithLifecycle()
    var showDeleteDialog by remember { mutableStateOf(false) }
    var usuarioToDelete by remember { mutableStateOf<Usuario?>(null) }
    var showEditDialog by remember { mutableStateOf(false) }
    var usuarioToEdit by remember { mutableStateOf<Usuario?>(null) }

    // Manejar diálogo de eliminación
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
                usuarioToDelete = null
            },
            title = { Text("Eliminar Usuario") },
            text = { Text("¿Estás seguro de que quieres eliminar a ${usuarioToDelete?.nombre}?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        usuarioToDelete?.let { viewModel.eliminarUsuario(it) }
                        showDeleteDialog = false
                        usuarioToDelete = null
                    }
                ) {
                    Text("Eliminar", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        usuarioToDelete = null
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }

    // Manejar diálogo de edición
    if (showEditDialog) {
        usuarioToEdit?.let { usuario ->
            EditarUsuarioDialog(
                usuario = usuario,
                onDismiss = {
                    showEditDialog = false
                    usuarioToEdit = null
                },
                onConfirm = { usuarioActualizado ->
                    viewModel.actualizarUsuario(usuarioActualizado)
                    showEditDialog = false
                    usuarioToEdit = null
                }
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3))
    ) {
        // 🔹 Encabezado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF8B4513))
                .padding(16.dp)
        ) {
            Text(
                text = "Gestión de Usuarios",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(13.dp))

        // 🔹 Campo de búsqueda
        OutlinedTextField(
            value = state.searchText,
            onValueChange = viewModel::onSearchTextChange,
            placeholder = { Text("Buscar por nombre, email o rol") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar",
                    tint = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color.White, RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Contador de usuarios y estado de carga
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(color = Color(0xFF8B4513))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Cargando usuarios...", color = Color.Gray)
                }
            }
        } else {
            Text(
                text = "${state.usuariosFiltrados.size} usuarios encontrados",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 🔹 Lista de usuarios en contenedores
            if (state.usuariosFiltrados.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (state.searchText.isNotEmpty()) "No se encontraron usuarios" else "No hay usuarios registrados",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // ✅ Corrección: quitar "LazyListScope."
                    items(state.usuariosFiltrados) { usuario ->
                        UsuarioCard(
                            usuario = usuario,
                            onEditClick = {
                                usuarioToEdit = usuario
                                showEditDialog = true
                            },
                            onDeleteClick = {
                                usuarioToDelete = usuario
                                showDeleteDialog = true
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UsuarioCard(
    usuario: Usuario,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Usuario",
                    tint = Color(0xFF8B4513),
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = usuario.nombre,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )
                    Text(
                        text = usuario.email,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                    Box(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .background(
                                color = when (usuario.rol) {
                                    "Administrador" -> Color(0xFFFF6B6B)
                                    else -> Color(0xFF45B7D1)
                                },
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = usuario.rol,
                            fontSize = 12.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Fecha de Nacimiento: ${usuario.fechaRegistro}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Row {
                    IconButton(onClick = onEditClick) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar",
                            tint = Color(0xFF8B4513)
                        )
                    }
                    IconButton(onClick = onDeleteClick) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar",
                            tint = Color(0xFFD32F2F)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarUsuarioDialog(
    usuario: Usuario,
    onDismiss: () -> Unit,
    onConfirm: (Usuario) -> Unit
) {
    var nombre by remember { mutableStateOf(usuario.nombre) }
    var email by remember { mutableStateOf(usuario.email) }
    var rol by remember { mutableStateOf(usuario.rol) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Usuario") },
        text = {
            Column {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre completo") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                var expanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = rol,
                        onValueChange = {},
                        label = { Text("Rol") },
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        listOf("Administrador", "Usuario").forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption) },
                                onClick = {
                                    rol = selectionOption
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val usuarioActualizado = usuario.copy(
                        nombre = nombre,
                        email = email,
                        rol = rol
                    )
                    onConfirm(usuarioActualizado)
                }
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

// Data class para la UI
data class Usuario(
    val id: String,
    val nombre: String,
    val email: String,
    val rol: String,
    val fechaRegistro: String = ""
)
