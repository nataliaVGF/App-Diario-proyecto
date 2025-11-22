package mx.edu.utng.appdiario.ui.screens.auth.login_usuario

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import mx.edu.utng.appdiario.R
import mx.edu.utng.appdiario.navigation.navegacion_global.NavRoutes

@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(context)
    )

    // Observar los estados del ViewModel
    val email by viewModel.email.observeAsState(initial = "")
    val password by viewModel.password.observeAsState(initial = "")
    val loginEnable by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading by viewModel.isLoading.observeAsState(initial = false)
    val errorMessage by viewModel.errorMessage.observeAsState(initial = null)
    val emailError by viewModel.emailError.observeAsState(initial = null)
    val passwordError by viewModel.passwordError.observeAsState(initial = null)
    val navigateToAdmin by viewModel.navigateToAdmin.observeAsState(initial = false)
    val navigateToUser by viewModel.navigateToUser.observeAsState(initial = false)

    // Efectos para navegación automática después del login
    LaunchedEffect(navigateToAdmin) {
        if (navigateToAdmin) {
            navController.navigate("adminHome") {
                popUpTo("login") { inclusive = true }
            }
            viewModel.onNavigateToAdminCompleted()
        }
    }

    LaunchedEffect(navigateToUser) {
        if (navigateToUser) {
            navController.navigate(NavRoutes.BARRA_CLIENTE) {
                popUpTo(NavRoutes.LOGIN) { inclusive = true }
            }
            viewModel.onNavigateToUserCompleted()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF5E6D3))
    ) {
        Login(
            Modifier.align(Alignment.Center),
            navController = navController,
            email = email,
            password = password,
            loginEnable = loginEnable,
            isLoading = isLoading,
            errorMessage = errorMessage,
            emailError = emailError,
            passwordError = passwordError,
            onEmailChange = { newEmail ->
                viewModel.onLoginChanged(newEmail, password)
            },
            onPasswordChange = { newPassword ->
                viewModel.onLoginChanged(email, newPassword)
            },
            onLoginClick = {
                viewModel.loginUsuario()
            }
        )

        // Loading overlay
        if (isLoading) {
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
fun Login(
    modifier: Modifier,
    navController: NavController,
    email: String,
    password: String,
    loginEnable: Boolean,
    isLoading: Boolean,
    errorMessage: String?,
    emailError: String?,
    passwordError: String?,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 1.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Imagen(Modifier.padding(top = 10.dp))

        // Mostrar errores generales
        errorMessage?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        EmailField(
            email = email,
            onTextFieldChanged = onEmailChange,
            errorMessage = emailError
        )
        Spacer(modifier = Modifier.padding(10.dp))

        PasswordField(
            password = password,
            onTextFieldChanged = onPasswordChange,
            errorMessage = passwordError
        )
        Spacer(modifier = Modifier.padding(13.dp))

        LoginButton(
            loginEnable = loginEnable && !isLoading,
            isLoading = isLoading
        ) {
            onLoginClick()
        }

        Spacer(modifier = Modifier.padding(8.dp))

        // 🔹 BOTÓN DE RECUPERACIÓN DE CONTRASEÑA - NUEVO
        PasswordRecoveryButton {
            navController.navigate(NavRoutes.PASSWORD_RECOVERY)
        }

        Spacer(modifier = Modifier.padding(8.dp))

        RegistrarseBotton(loginEnable = true) {
            navController.navigate("registro")
        }
    }
}

@Composable
fun Imagen(modifier: Modifier) {
    Image(
        painter = painterResource(R.drawable.fondo),
        contentDescription = "imagen de fondo",
        modifier = modifier
    )
}

@Composable
fun PasswordField(
    password: String,
    onTextFieldChanged: (String) -> Unit,
    errorMessage: String?
) {
    Column {
        TextField(
            modifier = Modifier
                .width(320.dp)
                .height(56.dp)
                .fillMaxWidth()
                .background(
                    if (errorMessage != null) Color(0xFFFFE6E6) else Color.White,
                    shape = RoundedCornerShape(4.dp)
                ),
            placeholder = {
                Text(
                    text = "Password",
                    color = Color(0xFF4B3621),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            },
            value = password,
            onValueChange = { onTextFieldChanged(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFF4B3621),
                unfocusedContainerColor = if (errorMessage != null) Color(0xFFFFE6E6) else Color.White,
                focusedContainerColor = if (errorMessage != null) Color(0xFFFFE6E6) else Color.White,
                unfocusedIndicatorColor = if (errorMessage != null) Color.Red else Color.Transparent,
                focusedIndicatorColor = if (errorMessage != null) Color.Red else Color.Transparent
            ),
            isError = errorMessage != null
        )

        // Mostrar mensaje de error
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
fun LoginButton(
    loginEnable: Boolean,
    isLoading: Boolean = false,
    onLoginSelected: () -> Unit
) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier
            .width(330.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White,
            disabledContainerColor = Color(0xFFEB833D),
            disabledContentColor = Color.White
        ),
        enabled = loginEnable && !isLoading
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.size(20.dp)
            )
        } else {
            Text(
                "Iniciar Sesión",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun RegistrarseBotton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier
            .width(330.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White,
            disabledContainerColor = Color.Black,
            disabledContentColor = Color.White
        ),
        enabled = loginEnable
    ) {
        Text(
            "Registrarse",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// 🔹 NUEVO COMPONENTE: BOTÓN DE RECUPERACIÓN DE CONTRASEÑA
@Composable
fun PasswordRecoveryButton(onRecoverySelected: () -> Unit) {
    TextButton(
        onClick = { onRecoverySelected() },
        modifier = Modifier
            .width(330.dp)
            .height(40.dp)
    ) {
        Text(
            "¿Olvidaste tu contraseña?",
            color = Color(0xFF4B3621),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun EmailField(
    email: String,
    onTextFieldChanged: (String) -> Unit,
    errorMessage: String?
) {
    Column {
        TextField(
            modifier = Modifier
                .width(320.dp)
                .height(56.dp)
                .fillMaxWidth()
                .background(
                    if (errorMessage != null) Color(0xFFFFE6E6) else Color.White,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp)
                ),
            placeholder = {
                Text(
                    text = "Email",
                    color = Color(0xFF4B3621),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            },
            value = email,
            onValueChange = { onTextFieldChanged(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFF4B3621),
                unfocusedContainerColor = if (errorMessage != null) Color(0xFFFFE6E6) else Color.White,
                focusedContainerColor = if (errorMessage != null) Color(0xFFFFE6E6) else Color.White,
                unfocusedIndicatorColor = if (errorMessage != null) Color.Red else Color.Transparent,
                focusedIndicatorColor = if (errorMessage != null) Color.Red else Color.Transparent
            ),
            isError = errorMessage != null
        )

        // Mostrar mensaje de error
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