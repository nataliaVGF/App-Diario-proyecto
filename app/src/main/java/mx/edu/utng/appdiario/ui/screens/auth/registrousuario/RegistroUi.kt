package mx.edu.utng.appdiario.ui.screens.auth.registrousuario

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import java.util.Calendar

// Colores personalizados
val PanelSuperiorColor = Color(0xFF5D2600)  // Café oscuro línea visual
val BotonPrincipalColor = Color(0xFF5D2600) // Café oscuro en botones
val TextoBotonColor = Color.White
val FondoCampoColor = Color(0xFFFFCC89)
val FondoCampoErrorColor = Color(0xFFFFE6E6)
val ErrorColor = Color(0xFFFF5252)

@Composable
fun Registro(navController: NavHostController) {

    val context = LocalContext.current
    val viewModel: RegistroViewModel = viewModel(
        factory = RegistroViewModelFactory(context)
    )
    val state by viewModel.state.collectAsState()

    // Cuando el registro sea exitoso, regresar
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
            // Panel Café Superior
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

            // Errores generales
            state.error?.let { error ->
                Text(
                    text = error,
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Formulario
            LoginForm(
                state = state,
                onNombreChange = viewModel::onNombreChange,
                onApellidoPaternoChange = viewModel::onApellidoPaternoChange,
                onApellidoMaternoChange = viewModel::onApellidoMaternoChange,
                onFechaNacimientoChange = viewModel::onFechaNacimientoChange,
                onEmailChange = viewModel::onEmailChange,
                onPasswordChange = viewModel::onPasswordChange,
                onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
                onRegistrar = { viewModel.registrarUsuario() },
                onCancelar = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        // Loading overlay
        if (state.isLoading) {
            Box(
                Modifier
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
    onConfirmPasswordChange: (String) -> Unit,
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

        Spacer(Modifier.height(8.dp))

        CampoTexto(
            value = state.email,
            onValueChange = onEmailChange,
            placeholder = "Email",
            errorMessage = state.emailError
        )

        Spacer(Modifier.height(8.dp))

        CampoTexto(
            value = state.nombre,
            onValueChange = onNombreChange,
            placeholder = "Nombre",
            errorMessage = state.nombreError
        )

        Spacer(Modifier.height(8.dp))

        CampoTexto(
            value = state.apellidoPaterno,
            onValueChange = onApellidoPaternoChange,
            placeholder = "Apellido Paterno",
            errorMessage = state.apellidoPaternoError
        )

        Spacer(Modifier.height(8.dp))

        CampoTexto(
            value = state.apellidoMaterno,
            onValueChange = onApellidoMaternoChange,
            placeholder = "Apellido Materno",
            errorMessage = state.apellidoMaternoError
        )

        Spacer(Modifier.height(8.dp))

        CampoFecha(
            value = state.fechaNacimiento,
            onValueChange = onFechaNacimientoChange,
            errorMessage = state.fechaNacimientoError
        )

        Spacer(Modifier.height(8.dp))

        CampoPassword(
            value = state.password,
            onValueChange = onPasswordChange,
            placeholder = "Contraseña",
            errorMessage = state.passwordError
        )

        Spacer(Modifier.height(8.dp))

        CampoPassword(
            value = state.confirmPassword,
            onValueChange = onConfirmPasswordChange,
            placeholder = "Confirmar contraseña",
            errorMessage = state.confirmPasswordError
        )

        Spacer(Modifier.height(16.dp))

        BotonesRegistro(
            onRegistrar = onRegistrar,
            onCancelar = onCancelar,
            isFormValid = state.isFormValid
        )

        Spacer(Modifier.height(24.dp))
    }
}

@Composable
fun CampoTexto(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    errorMessage: String?
) {
    Column {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder) },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = if (errorMessage != null) FondoCampoErrorColor else FondoCampoColor,
                unfocusedContainerColor = if (errorMessage != null) FondoCampoErrorColor else FondoCampoColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            isError = errorMessage != null
        )

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
fun CampoFecha(
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String?
) {
    val context = LocalContext.current
    val calendario = Calendar.getInstance()

    Column {

        Box {
            TextField(
                value = value,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                placeholder = { Text("Fecha de nacimiento") },
                enabled = false,
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = if (errorMessage != null) FondoCampoErrorColor else FondoCampoColor,
                    disabledIndicatorColor = Color.Transparent,
                    disabledTextColor = Color.Black
                )
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable {
                        val year = calendario.get(Calendar.YEAR)
                        val month = calendario.get(Calendar.MONTH)
                        val day = calendario.get(Calendar.DAY_OF_MONTH)

                        DatePickerDialog(
                            context,
                            { _, y, m, d ->
                                val fecha = "%02d/%02d/%04d".format(d, m + 1, y)
                                onValueChange(fecha)
                            },
                            year, month, day
                        ).show()
                    }
            )
        }

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
fun CampoPassword(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    errorMessage: String?
) {
    var visible by remember { mutableStateOf(false) }

    Column {
        TextField(
            value = value,
            onValueChange = { nuevo ->
                val limpio = nuevo
                    .replace("[´`^¨]".toRegex(), "")
                    .replace("\\s".toRegex(), "")
                    .replace("[<>]".toRegex(), "")
                onValueChange(limpio)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder) },
            singleLine = true,
            visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { visible = !visible }) {
                    Icon(
                        imageVector = if (visible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = if (errorMessage != null) FondoCampoErrorColor else FondoCampoColor,
                unfocusedContainerColor = if (errorMessage != null) FondoCampoErrorColor else FondoCampoColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            isError = errorMessage != null
        )

        if (!errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
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
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        // BOTÓN REGISTRARSE (relleno café)
        Button(
            onClick = onRegistrar,
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFormValid)
                    BotonPrincipalColor
                else
                    BotonPrincipalColor.copy(alpha = 0.4f),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.width(140.dp)
        ) {
            Text("Registrarse")
        }

        // BOTÓN CANCELAR (relleno café también)
        Button(
            onClick = onCancelar,
            colors = ButtonDefaults.buttonColors(
                containerColor = BotonPrincipalColor,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.width(140.dp)
        ) {
            Text("Cancelar")
        }
    }
}
