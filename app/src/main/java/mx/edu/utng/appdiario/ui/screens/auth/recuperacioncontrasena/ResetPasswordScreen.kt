package mx.edu.utng.appdiario.ui.screens.auth.recuperacioncontrasena

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.local.database.AppDatabase

@Composable
fun ResetPasswordScreen(
    navController: NavController,
    email: String

) {
    val context = LocalContext.current


    val usuarioRepository = remember {
        UsuarioRepository(AppDatabase.getDatabase(context).usuarioDao())
    }



    val viewModel: PasswordRecoveryViewModel = viewModel(
        factory = PasswordRecoveryViewModelFactory(
            context = context,
            usuarioRepository = usuarioRepository

        )
    )

    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }

    // 🔹 MOVER LaunchedEffect AQUÍ - fuera del onClick
    var navigateToLogin by remember { mutableStateOf(false) }

    if (navigateToLogin) {
        LaunchedEffect(Unit) {
            delay(2000)
            navController.navigate("login") {
                popUpTo("login") { inclusive = true }
            }
        }
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }

                Text(
                    text = "Nueva Contraseña",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 24.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        // Contenido principal
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
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Icono
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Nueva Contraseña",
                    tint = Color(0xFF4E2A0E),
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Crear Nueva Contraseña",
                    color = Color(0xFF4E2A0E),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Ingresa tu nueva contraseña para la cuenta:",
                    color = Color(0xFF4E2A0E),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = email,
                    color = Color(0xFF4E2A0E),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Campo de nueva contraseña
                OutlinedTextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    label = {
                        Text(
                            "Nueva Contraseña",
                            color = Color(0xFF4E2A0E)
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                                tint = Color(0xFF4E2A0E)
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color(0xFF4E2A0E),
                        unfocusedTextColor = Color(0xFF4E2A0E),
                        focusedLabelColor = Color(0xFF4E2A0E),
                        unfocusedLabelColor = Color(0xFF4E2A0E),
                        focusedIndicatorColor = Color(0xFF6D3B1A),
                        unfocusedIndicatorColor = Color(0xFF4E2A0E)
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Campo de confirmar contraseña
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    label = {
                        Text(
                            "Confirmar Contraseña",
                            color = Color(0xFF4E2A0E)
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Icon(
                                imageVector = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (confirmPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                                tint = Color(0xFF4E2A0E)
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color(0xFF4E2A0E),
                        unfocusedTextColor = Color(0xFF4E2A0E),
                        focusedLabelColor = Color(0xFF4E2A0E),
                        unfocusedLabelColor = Color(0xFF4E2A0E),
                        focusedIndicatorColor = Color(0xFF6D3B1A),
                        unfocusedIndicatorColor = Color(0xFF4E2A0E)
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Botón de restablecer
                Button(
                    onClick = {
                        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                            errorMessage = "Por favor completa ambos campos"
                            return@Button
                        }

                        if (newPassword != confirmPassword) {
                            errorMessage = "Las contraseñas no coinciden"
                            return@Button
                        }

                        if (newPassword.length < 6) {
                            errorMessage = "La contraseña debe tener al menos 6 caracteres"
                            return@Button
                        }

                        isLoading = true
                        errorMessage = ""

                        viewModel.resetPassword(email, newPassword) { success ->
                            isLoading = false
                            if (success) {
                                successMessage = "✅ Contraseña actualizada exitosamente"
                                navigateToLogin = true // 🔹 ACTIVAR LA NAVEGACIÓN
                            } else {
                                errorMessage = "❌ Error al actualizar la contraseña"
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6D3B1A)
                    ),
                    enabled = !isLoading && newPassword.isNotBlank() && confirmPassword.isNotBlank()
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    } else {
                        Text(
                            "Restablecer Contraseña",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Mensajes de error/éxito
                if (errorMessage.isNotBlank()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                if (successMessage.isNotBlank()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = successMessage,
                        color = Color.Green,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}