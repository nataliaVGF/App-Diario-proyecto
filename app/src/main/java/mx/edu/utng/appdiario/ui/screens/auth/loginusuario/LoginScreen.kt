package mx.edu.utng.appdiario.ui.screens.auth.loginusuario

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import mx.edu.utng.appdiario.R
import mx.edu.utng.appdiario.navigation.navegacionglobal.NavRoutes

@Composable
fun LoginScreen(navController: NavHostController) {

    val context = LocalContext.current
    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(context)
    )

    val email by viewModel.email.observeAsState(initial = "")
    val password by viewModel.password.observeAsState(initial = "")
    val loginEnable by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading by viewModel.isLoading.observeAsState(initial = false)
    val errorMessage by viewModel.errorMessage.observeAsState(initial = null)
    val emailError by viewModel.emailError.observeAsState(initial = null)
    val passwordError by viewModel.passwordError.observeAsState(initial = null)
    val navigateToAdmin by viewModel.navigateToAdmin.observeAsState(initial = false)
    val navigateToUser by viewModel.navigateToUser.observeAsState(initial = false)

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
                viewModel.onEmailChanged(newEmail)
            },
            onPasswordChange = { newPassword ->
                viewModel.onPasswordChanged(newPassword)
            },
            onLoginClick = {
                viewModel.loginUsuario()
            }
        )

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
        Spacer(modifier = Modifier.padding(30.dp))

        errorMessage?.let {
            Text(
                text = it,
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

// ---------------------------------------------------
//          NUEVO EMAILFIELD @gmail.com CORREGIDO
// ---------------------------------------------------
@Composable
fun EmailField(
    email: String,
    onTextFieldChanged: (String) -> Unit,
    errorMessage: String?
) {
    val gmailRegex = Regex("^[a-zA-Z0-9._%+-]+@gmail\\.com$")

    Column {
        TextField(
            value = email,
            onValueChange = { newValue ->
                onTextFieldChanged(newValue)
            },
            placeholder = {
                Text(
                    text = "Gmail",
                    color = Color(0xFF4B3621),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            },
            modifier = Modifier
                .width(320.dp)
                .height(56.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            isError = !gmailRegex.matches(email) && email.isNotEmpty(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFF4B3621),
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Red
            )
        )

        if (!gmailRegex.matches(email) && email.isNotEmpty()) {
            Text(
                text = "Debe ser un correo @gmail.com",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
            )
        }
    }
}

@Composable
fun PasswordField(
    password: String,
    onTextFieldChanged: (String) -> Unit,
    errorMessage: String?
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column {
        TextField(
            value = password,
            onValueChange = { onTextFieldChanged(it) },
            placeholder = {
                Text(
                    text = "Password",
                    color = Color(0xFF4B3621),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            },
            modifier = Modifier
                .width(320.dp)
                .height(56.dp),
            singleLine = true,
            isError = errorMessage != null,

            // 👁 Mostrar / ocultar texto
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                        tint = Color(0xFF4B3621)
                    )
                }
            },

            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFF4B3621),
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Red
            )
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
            contentColor = Color.White
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
            contentColor = Color.White
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
