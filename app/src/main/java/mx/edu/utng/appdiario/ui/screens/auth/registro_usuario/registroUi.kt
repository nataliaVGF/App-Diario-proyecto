package mx.edu.utng.appdiario.ui.screens.auth.registro_usuario

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

// Colores personalizados
val BotonPrincipalColor = Color(0xFF6200EE)
val TextoBotonColor = Color.White
val FondoCampoColor = Color(0xFFFFCC89)
val PanelSuperiorColor = Color(0xFF5D2600)
val ErrorColor = Color(0xFFFF5252)
val FondoCampoErrorColor = Color(0xFFFFE6E6)

@Composable
fun Registro(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: RegistroViewModel = viewModel(
        factory = RegistroViewModelFactory(context)
    )
    val state by viewModel.state.collectAsState()

    // Navegar de regreso cuando el registro sea exitoso
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.popBackStack()
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF5E6D3))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Panel café superior
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PanelSuperiorColor)
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Registro",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Mostrar errores
            state.error?.let { error ->
                Text(
                    text = error,
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Contenido del formulario
            LoginForm(
                state = state,
                onNombreChange = viewModel::onNombreChange,
                onApellidoPaternoChange = viewModel::onApellidoPaternoChange,
                onApellidoMaternoChange = viewModel::onApellidoMaternoChange,
                onFechaNacimientoChange = viewModel::onFechaNacimientoChange,
                onEmailChange = viewModel::onEmailChange,
                onPasswordChange = viewModel::onPasswordChange,
                onRegistrar = {
                    viewModel.registrarUsuario()
                },
                onCancelar = {
                    navController.popBackStack()
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        // Loading overlay
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}

@Composable
fun LoginForm(
    state: RegistroState,
    onNombreChange: (String) -> Unit,
    onApellidoPaternoChange: (String) -> Unit,
    onApellidoMaternoChange: (String) -> Unit,
    onFechaNacimientoChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRegistrar: () -> Unit,
    onCancelar: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(bottom = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        // Campo Email
        CampoTexto(
            value = state.email,
            onValueChange = onEmailChange,
            placeholder = "Email",
            errorMessage = state.emailError
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Nombre
        CampoTexto(
            value = state.nombre,
            onValueChange = onNombreChange,
            placeholder = "Nombre",
            errorMessage = state.nombreError
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Apellido Paterno
        CampoTexto(
            value = state.apellidoPaterno,
            onValueChange = onApellidoPaternoChange,
            placeholder = "Apellido Paterno",
            errorMessage = state.apellidoPaternoError
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Apellido Materno
        CampoTexto(
            value = state.apellidoMaterno,
            onValueChange = onApellidoMaternoChange,
            placeholder = "Apellido Materno",
            errorMessage = state.apellidoMaternoError
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Fecha Nacimiento
        CampoTexto(
            value = state.fechaNacimiento,
            onValueChange = onFechaNacimientoChange,
            placeholder = "Fecha de Nacimiento (DD/MM/AAAA)",
            errorMessage = state.fechaNacimientoError
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Password (SIN INDICADOR DE FORTALEZA)
        CampoTexto(
            value = state.password,
            onValueChange = onPasswordChange,
            placeholder = "Contraseña",
            errorMessage = state.passwordError,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botones
        BotonesRegistro(
            onRegistrar = onRegistrar,
            onCancelar = onCancelar,
            isFormValid = state.isFormValid
        )

        Spacer(modifier = Modifier.height(24.dp))
        SocialLogin()

        // Espacio adicional para mejor scroll
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun CampoTexto(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    errorMessage: String?,
    isPassword: Boolean = false
) {
    Column {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (errorMessage != null) FondoCampoErrorColor else FondoCampoColor,
                    shape = RoundedCornerShape(8.dp)
                ),
            placeholder = { Text(placeholder) },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = if (errorMessage != null) FondoCampoErrorColor else FondoCampoColor,
                unfocusedContainerColor = if (errorMessage != null) FondoCampoErrorColor else FondoCampoColor,
                focusedIndicatorColor = if (errorMessage != null) ErrorColor else Color.Transparent,
                unfocusedIndicatorColor = if (errorMessage != null) ErrorColor else Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                cursorColor = Color.Black
            ),
            isError = errorMessage != null
        )

        // Mostrar mensaje de error si existe
        if (!errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage,
                color = ErrorColor,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
            )
        }
    }
}

@Composable
fun BotonesRegistro(
    onRegistrar: () -> Unit,
    onCancelar: () -> Unit,
    isFormValid: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onRegistrar,
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFormValid) BotonPrincipalColor else Color.Gray
            )
        ) {
            Text("Registrarse", color = TextoBotonColor)
        }
        OutlinedButton(
            onClick = onCancelar,
            colors = ButtonDefaults.outlinedButtonColors(contentColor = BotonPrincipalColor)
        ) {
            Text("Cancelar")
        }
    }
}

@Composable
fun SocialLogin() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("O regístrate con")
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Build, contentDescription = "Facebook")
            }
            IconButton(onClick = {}) {
                Icon(Icons.Default.Email, contentDescription = "Google")
            }
            IconButton(onClick = {}) {
                Icon(Icons.Default.Share, contentDescription = "Twitter")
            }
        }
    }
}