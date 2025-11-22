package mx.edu.utng.appdiario.ui.screens.cliente.perfilUsuario

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import mx.edu.utng.appdiario.Repository.UsuarioRepository
import mx.edu.utng.appdiario.local.database.AppDatabase
import mx.edu.utng.appdiario.ui.screens.auth.login_usuario.SessionManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilUsuario(
    navController: NavHostController,
    showBottomBar: MutableState<Boolean>,
    globalNavController: NavHostController
) {
    val context = LocalContext.current

    // Crear las dependencias necesarias
    val usuarioRepository = UsuarioRepository(AppDatabase.getDatabase(context).usuarioDao())
    val sessionManager = SessionManager(context)

    val viewModel: PerfilUsuarioViewModel = viewModel(
        factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return PerfilUsuarioViewModel(usuarioRepository, sessionManager) as T
            }
        }
    )

    // ✅ ELIMINADO: LaunchedEffect con viewModel.init() - Ya no es necesario

    // Estados del ViewModel
    val usuario by viewModel.usuario.collectAsState()
    val isEditing by viewModel.isEditing.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    // Estado local para edición - SOLO campos editables
    var nombreEditado by remember(usuario) { mutableStateOf(usuario?.nombre ?: "") }
    var apellidoPaEditado by remember(usuario) { mutableStateOf(usuario?.apellidoPa ?: "") }
    var apellidoMaEditado by remember(usuario) { mutableStateOf(usuario?.apellidoMa ?: "") }
    var emailEditado by remember(usuario) { mutableStateOf(usuario?.email ?: "") }

    // Mostrar loading
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5E6D3)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color(0xFF6D3B1A))
        }
        return
    }

    // Mostrar error
    if (errorMessage != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5E6D3))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Error", color = Color.Red, fontSize = 18.sp)
                Text(errorMessage ?: "Error desconocido", color = Color(0xFF4E2A0E))
                Button(
                    onClick = { viewModel.clearError() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D3B1A))
                ) {
                    Text("Reintentar")
                }
            }
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Mi Perfil",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                actions = {
                    // Botón Editar/Guardar
                    IconButton(
                        onClick = {
                            if (isEditing) {
                                // Crear usuario actualizado solo con campos editables
                                usuario?.let { usuarioActual ->
                                    val usuarioActualizado = usuarioActual.copy(
                                        nombre = nombreEditado,
                                        apellidoPa = apellidoPaEditado,
                                        apellidoMa = apellidoMaEditado,
                                        email = emailEditado
                                    )
                                    viewModel.actualizarUsuario(usuarioActualizado)
                                }
                            } else {
                                viewModel.setEditing(true)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (isEditing) Icons.Default.Save else Icons.Default.Edit,
                            contentDescription = if (isEditing) "Guardar" else "Editar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6D3B1A)
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5E6D3))
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()) // ← SCROLL AGREGADO AQUÍ
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Avatar del usuario
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF8D4E25)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Avatar",
                    tint = Color.White,
                    modifier = Modifier.size(60.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Información del usuario (editable o solo lectura)
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFD9A97C)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    if (isEditing) {
                        // MODO EDICIÓN - Solo campos editables
                        EditableInfoItem(
                            label = "Nombre",
                            value = nombreEditado,
                            onValueChange = { nuevoNombre ->
                                nombreEditado = nuevoNombre
                            }
                        )
                        EditableInfoItem(
                            label = "Apellido Paterno",
                            value = apellidoPaEditado,
                            onValueChange = { nuevoApellidoPa ->
                                apellidoPaEditado = nuevoApellidoPa
                            }
                        )
                        EditableInfoItem(
                            label = "Apellido Materno",
                            value = apellidoMaEditado,
                            onValueChange = { nuevoApellidoMa ->
                                apellidoMaEditado = nuevoApellidoMa
                            }
                        )
                        EditableInfoItem(
                            label = "Email",
                            value = emailEditado,
                            onValueChange = { nuevoEmail ->
                                emailEditado = nuevoEmail
                            }
                        )
                        // Fecha de nacimiento SOLO LECTURA
                        InfoItem("Fecha de Nacimiento", usuario?.fechNaci ?: "")
                        InfoItem("Tipo de Usuario", usuario?.tipo?.name ?: "")
                    } else {
                        // MODO SOLO LECTURA
                        InfoItem("Nombre", "${usuario?.nombre ?: ""} ${usuario?.apellidoPa ?: ""} ${usuario?.apellidoMa ?: ""}")
                        InfoItem("Email", usuario?.email ?: "")
                        InfoItem("Fecha de Nacimiento", usuario?.fechNaci ?: "")
                        InfoItem("Tipo de Usuario", usuario?.tipo?.name ?: "")
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botones según el modo
            if (isEditing) {
                // Botones en modo edición
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Botón Cancelar
                    Button(
                        onClick = {
                            viewModel.setEditing(false)
                            // Resetear los datos al usuario original
                            usuario?.let {
                                nombreEditado = it.nombre
                                apellidoPaEditado = it.apellidoPa
                                apellidoMaEditado = it.apellidoMa
                                emailEditado = it.email
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6D3B1A)
                        ),
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Cancelar",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }

                    // Botón Guardar
                    Button(
                        onClick = {
                            // Guardar cambios en la base de datos
                            usuario?.let { usuarioActual ->
                                val usuarioActualizado = usuarioActual.copy(
                                    nombre = nombreEditado,
                                    apellidoPa = apellidoPaEditado,
                                    apellidoMa = apellidoMaEditado,
                                    email = emailEditado
                                )
                                viewModel.actualizarUsuario(usuarioActualizado)
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .padding(start = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF8B5A2B)
                        ),
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Save,
                            contentDescription = "Guardar",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Guardar",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }
            } else {
                // Botón de cerrar sesión (solo en modo lectura)
                Button(
                    onClick = {
                        viewModel.cerrarSesion()
                        globalNavController.navigate("login") {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF8B5A2B)
                    ),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Cerrar sesión",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Cerrar Sesión",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }



        }
    }
}

// Componente para información en modo solo lectura
@Composable
fun InfoItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF4E2A0E),
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color(0xFF4E2A0E),
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

// Componente para información editable
@Composable
fun EditableInfoItem(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF4E2A0E),
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFF4E2A0E),
                unfocusedTextColor = Color(0xFF4E2A0E),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color(0xFF6D3B1A),
                unfocusedIndicatorColor = Color(0xFF8B5A2B),
                cursorColor = Color(0xFF6D3B1A)
            ),
            singleLine = true
        )
    }
}