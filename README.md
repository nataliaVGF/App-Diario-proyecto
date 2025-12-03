Descripcion del proyecto
DiarioApp es una aplicación de diario personal
diseñada para que los usuarios puedan registrar sus
pensamientos, actividades y emociones tanto mediante 
entradas de texto como grabaciones de audio con diferentes 
filtros de organizacion (receta,personal,actividades)

Caracteristicas Principales
1.-Crea tus propias notas y audios de forma ordenada y segura
2.-Las notas y audios estan catalogadas por tipo de diario y fecha
3.-Edita y elimina las existentes
4.-Privasidad integrada
5.-Recuperacion de cuenta

Tecnologias Implementadas 
Lenguaje:Kotlin
IDE:Android Studio
Base de Datos:ROOM DaraBase
UI:Composable+Material Design 3
Arquitectura:MVVM

Instrucciones de Instalacion
descarga del repositorio https://github.com/nataliaVGF/App-Diario-proyecto.git
Abre el proyecto en Android Studio
Haz click en file Open
Selecciona la carpeta Diario-App-Android
Espera a que eel Gradle construya la aplicacion 
Conecta un dispositivo ya sea en el emulador o fisico 
Click en RUN

Tambien ya esta disponible la aplicacion en PlayStore

Uso de la aplicacion
Abre la app
Registrate(en caso de olvidar tu contrasena hay una opcion para recuperarla y sigue las instrucciones que te indica )
Una vez en el menu puedes iniciar tu diario seleccionando la opcion de texto o audio
La interaccion en ambos tipos es similar
Da click en mas y anade y guarda tu diario en el caso de texto escribe un titulo y el texto que quieras y da en guardar 
En el caso del audio selecciona play empieza a grabar tu diarioAudio revisalo con play en caso de no agradarte puedes eliminarlo con el icono basura
Una vez aalmacenado el tipo de diario te enviara a la seccion de listado donde puedes previsualizarlos y editarlos en el caso de ya no requerirlos tambien puedes eliminarlos 

Estructura del Proyecto
(MVVM)
/app
    |
    |
    java/mx.edu.utng.appdiario/
    |
    |
    api/
    |    |
    |    {}client_secret
    |    GmailApiHelper
    |
    local/
    |    |
    |    dao/
    |    |    |
    |    |    DiarioAudioDao
    |    |    DiarioTextoDao
    |    |    TarjetaDao
    |    |    UsuarioDaio
    |    database/
    |     |       |
    |     |     AppDatabase  
    |    entity/
    |      |
    |      diario/
    |      |      | 
    |      |      DiarioAudio
    |      |      DiarioTexto
    |      tarjeta/
    |      |      |
    |      |      TipoTarjeta
    |      |      TipoTarjetaConver
    |      |
    |      usuario/ 
    |              |
    |              TipoUsuario
    |              TipoUsuarioConverter
    |              Usuario  
    |
    | 
    navigation/
    |        |
    |         barranavegacionadmin/
    |        |           |
    |        |            Navegacion
    |        |
    |        barranavegacioncliente/
    |        |           |
    |        |           NavegacionCliente
    |        |   
    |        navegacionglobal/
    |                     |
    |                     NavGraph
    repository/
    |        |
    |        AudioManager
    |        DiarioAudioRepository
    |        DiarioTextoRepository
    |        TarjetaRepository
    |        UsuarioRepository
    ui/
    |        |
    |        sceens/
    |                |
    |                administrador\
    |                |    |  
    |                |   dashboardadministrador\
    |                |    |    |          
    |                |    |    DashBoardAdmin
    |                |    |    | 
    |                |    |    repository\
    |                |    |           |
    |                |    |           DashboardRepository
    |                |    |  
    |                |    viewmodel\
    |                |    |       |
    |                |    |        DashBoardViewModel
    |                |    |        DashBoardViewModelFactory
    |                |    |        |
    |                |    |        |
    |                |    |  
    |                |    gestionusuario\
    |                |    |        |   
    |                |    |         GestionUsuario
    |                |    |         GestionUsuarioViewModel
    |                |    |         GestionUsuarioViewModelFactory   
    |                |    | 
    |                |    reportesadministrador\
    |                |    |          |
    |                |    |            ReportesAdministrador
    |                |    |            ReportesViewModel
    |                |    |            ReportesViewModelFactory
    |                |    | 
    |                |    AdminiInitializer
    |                |   
    |                 auth\   
    |                |     |
    |                |     loginusuario/
    |                |     |      |
    |                |     |      LoginScreen
    |                |     |      LoginUsuario
    |                |     |      LoginViewModel
    |                |     |      SessionMananger 
    |                |     |  
    |                |     recuperacioncontrasena\
    |                |     |         |
    |                |     |         CodeVerificationActivity
    |                |     |         PasswordRecoveryActivity
    |                |     |         PaswordRecoveryViewModel
    |                |     |         PasswordRecoveryViewModelFactory
    |                |     |         RecoveryCodeManager
    |                |     |         ResetPasswordScreen  
    |                |     |  
    |                |     |  
    |                |     registrousuario\   
    |                |                |     
    |                |                RegistroUi   
    |                |                RegistroViewModel   
    |                |                RegistroViewModelFactory   
    |                |
    |                |
    |                cliente\    
    |                       | 
    |                        dashboardcliente/
    |                       |             |
    |                       |             DashboardScreen   
    |                        diarioaudio/
    |                       |            |
    |                       |            detalleaudioscreen/   
    |                       |                  |
    |                       |                  DetalleDiarioAudioScreen
    |                       |                  DetalleDiarioAudioViewModel  
    |                       |
    |                       diarioaudioscreen/
    |                       |                  | 
    |                       |                  DiarioAudioScreen  
    |                       |                  DiarioAudioViewModel   
    |                       | 
    |                       listadiarioaudioscreen/
    |                       |                   |      
    |                       |                   ListaDiariosAudioScreen
    |                       |                   ListaDiariosAudioViewModel
    |                       |
    |                       diariotexto\
    |                                            |
    |                                            detallediariosceen\
    |                                            |            |
    |                                            |            DetalleDiarioScreen
    |                                            |            DetalleDiaritextoViewModel   
    |
    |
    |                                            diariotextoscreen\
    |                                            |            |
    |                                            |            |
    |                                            |             DiarioTextoScreen
    |                                            |             DiarioTextoUiState
    |                                            |             DiarioTextoViewModel
    |                                            |           
    |                                            listadiarioscreen\
    |                                            |              | 
    |                                            |              ListaDiariosScreen
    |                                            |              LisataDiariosViewModel  
    |                                            |                
    |                                            perfilusuario\
    |                                                            |
    |                                                            PerfilUsuario
    |                                                            PerfilUsuarioViewModel
    |
    MainActivity

Instrucciones 

MANUAL DE CREACION
Crea una nueva app dentro de Android Studio
Abre Android Studio y selecciona Emprty Activity entorno que trabaja con Composable para la creación de las pantallas de la app.
Name: appDiario
PackageName: com.mx.nombre.appDiario
Minimum SDK:API 24
Building configuration:Kotlin DSL(build.gradle.kts)


Gradle
Es quien consrtruye el sistema (build system) oficial de Android. Automatiza:
•	Compilación del código
•	Gestión de dependencias (librerías)
•	Empaquetado de la app (.apk/.aab)
•	Pruebas y despliegue

Tenemos que crear los 2 archivos principales 
build.gradle.kts: Carpeta raíz del proyecto
build.gradle.kts (Nivel de Módulo): Carpeta app/ en este caso solo requerimos de la implementación de librerías dentro de esta carpeta junto con su library para las versiones de lo que necesitaremos


Crea el build.Gradle.module

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)

}

android {

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/LICENSE.md"
            excludes += "/META-INF/NOTICE.md"
            excludes += "/META-INF/DEPENDENCIES"
            excludes += "/META-INF/*.kotlin_module"
            excludes += "META-INF/LICENSE"
            excludes += "META-INF/NOTICE"
            excludes += "META-INF/INDEX.LIST"
        }
    }

    namespace = "mx.edu.utng.appdiario"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "mx.edu.utng.appdiario"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs += listOf(
            "-Xjvm-default=all"
        )
    }

    buildFeatures {
        compose = true
    }

    // ✅ SOLO AGREGAR ESTAS 2 SECCIONES NUEVAS:
    aaptOptions {
        additionalParameters("--warn-manifest-validation")
    }

    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }
}


dependencies {
    implementation("com.sun.mail:android-mail:1.6.7")
    implementation("com.sun.mail:android-activation:1.6.7")

    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui.text)
    implementation(libs.androidx.ui.text)
    implementation(libs.androidx.benchmark.traceprocessor)
    implementation(libs.androidx.foundation)
    val roomVersion = libs.versions.roomKtx.get()
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    // AndroidX Core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")




    //splachscreen del main que no arranco
    implementation(libs.androidx.core.splashscreen)
    // Jetpack Compose BOM (Bill of Materials)
    // El BOM gestiona las versiones de todas las librerías de Compose
    val composeBom = platform("androidx.compose:compose-bom:2024.02.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Compose UI
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Material Design 3 para Compose
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")

    // Activity Compose
    implementation("androidx.activity:activity-compose:1.8.2")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // ViewModel Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    // Seguridad - EncryptedSharedPreferences
    implementation("androidx.security:security-crypto:1.1.0-alpha06")

    // Networking - Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp("androidx.room:room-compiler:$roomVersion")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Accompanist (utilidades para Compose)
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.32.0")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
}

Estamos haciendo uso de las librerías 
1.	Navigation Compose (androidx.navigation:navigation-compose): Para implementar navegación entre pantallas (destinos) en apps con Jetpack Compose.
2.	ViewModel Compose (lifecycle-viewmodel-compose y lifecycle-runtime-compose): integramos ViewModel con Compose para mantener datos durante cambios de configuración (como rotación) y manejar el ciclo de vida.
3.	Coroutines (kotlinx-coroutines-android y core): para programación asíncrona y concurrente en Kotlin. usada para operaciones en segundo plano (red, base de datos) sin bloquear el hilo principal.
4.	Room (androidx.room.runtime):
Capa de abstracción sobre SQLite para bases de datos locales. Simplifica el almacenamiento persistente con verificaciones en tiempo de compilación y soporte para corrutinas.


Ingresa el room y versiones de Android correcpondientes dentro de tu libs.versions.toml

[versions]
agp = "8.13.0"
kotlin = "2.1.10"
coreKtx = "1.17.0"
junit = "4.13.2"
junitVersion = "1.3.0"
espressoCore = "3.7.0"
lifecycleRuntimeKtx = "2.9.4"
activityCompose = "1.11.0"
composeBom = "2024.09.00"
uiGraphics = "1.9.4"
coreSplashscreen = "1.0.1"
ksp = "2.2.20-2.0.4"
roomRuntime = "2.8.2"
roomKtx = "2.8.2"
roomCompiler = "2.8.2"
hilt = "2.48.1"
hilt-navigation = "1.1.0"
navigation-compose = "2.7.7"
lifecycle-viewmodel-compose = "2.7.0"
runtimeLivedata = "1.9.4"
material3 = "1.4.0"
foundation = "1.9.4"
uiText = "1.9.4"
uiTextVersion = "1.9.5"
benchmarkTraceprocessor = "1.4.1"
foundationVersion = "1.9.5"

[libraries]
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
junit = { group = "junit", name = "junit", version.ref = "junit" }
androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
androidx-lifecycle-runtime-ktx = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "lifecycleRuntimeKtx" }
androidx-activity-compose = { group = "androidx.activity", name = "activity-compose", version.ref = "activityCompose" }
androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "composeBom" }
androidx-compose-ui = { group = "androidx.compose.ui", name = "ui" }
androidx-compose-ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics" }
androidx-compose-ui-tooling = { group = "androidx.compose.ui", name = "ui-tooling" }
androidx-compose-ui-tooling-preview = { group = "androidx.compose.ui", name = "ui-tooling-preview" }
androidx-compose-ui-test-manifest = { group = "androidx.compose.ui", name = "ui-test-manifest" }
androidx-compose-ui-test-junit4 = { group = "androidx.compose.ui", name = "ui-test-junit4" }
androidx-compose-material3 = { group = "androidx.compose.material3", name = "material3" }
androidx-ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics", version.ref = "uiGraphics" }
androidx-core-splashscreen = { group = "androidx.core", name = "core-splashscreen", version.ref = "coreSplashscreen" }
androidx-room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "roomRuntime" }
androidx-room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "roomKtx" }
androidx-room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "roomCompiler" }
androidx-compose-runtime-livedata = { group = "androidx.compose.runtime", name = "runtime-livedata", version.ref = "runtimeLivedata" }
androidx-material3 = { group = "androidx.compose.material3", name = "material3", version.ref = "material3" }
androidx-compose-foundation = { group = "androidx.compose.foundation", name = "foundation", version.ref = "foundation" }
androidx-compose-ui-text = { group = "androidx.compose.ui", name = "ui-text", version.ref = "uiText" }
androidx-ui-text = { group = "androidx.compose.ui", name = "ui-text", version.ref = "uiTextVersion" }
androidx-benchmark-traceprocessor = { group = "androidx.benchmark", name = "benchmark-traceprocessor", version.ref = "benchmarkTraceprocessor" }
androidx-foundation = { group = "androidx.compose.foundation", name = "foundation", version.ref = "foundationVersion" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
ksp = { id = "com.google.devtools.ksp", version.ref = "ksp" }
hilt-android = { id = "com.google.dagger.hilt.android", version.ref = "hilt" }


Creación de carpetas para la aplicación
Crea dentro de tu carpeta appdiario las siguientes carpetas:

appdiario/api 
Contiene clases para la implementación de la Api Gmail usada en la recuperación de la contraseña de nuestra aplicación,junto con su credencial de API Google Cloud.
appdiario/local
Contiene todo el almacenamiento local en la app en pocas palabras las carpetas que conforman la base de datos 
appdiario/navigation
Contiene la navegación de la aplicación 
appdiario/repository
Contiene la abstracicon de los datos quien maneja el cache y la sincronización 
appdiario/ui
Contiene toda la interfaz de la aplicación con sus respectivos ciclos de vida 











Empecemos a trabajar con el entorno UI

appdiario/ui
appdirio/ui/screens
appdirio/ui/screens/auth
	appdirio/ui/screens/auth/loginusuario
LoginScreen.kt

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

<img width="238" height="528" alt="image" src="https://github.com/user-attachments/assets/a472fc2e-9c31-4e12-9732-18f52c2ed2ff" />

LoginUsuarioViewModelFactory.kt

package mx.edu.utng.appdiario.ui.screens.auth.loginusuario

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.local.database.AppDatabase

class LoginViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            val database = AppDatabase.getDatabase(context)
            val usuarioDao = database.usuarioDao()
            val repository = UsuarioRepository(usuarioDao)
            val sessionManager = SessionManager(context)       // Añadido SessionManager
            return LoginViewModel(repository, sessionManager) as T   //  Pasar sessionManager
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

LoginViewModel.kt

package mx.edu.utng.appdiario.ui.screens.auth.loginusuario

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.local.entity.Ususario.TipoUsuario

class LoginViewModel(
    private val repository: UsuarioRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _navigateToRegister = MutableLiveData<Boolean>()
    val navigateToRegister: LiveData<Boolean> = _navigateToRegister

    private val _navigateToAdmin = MutableLiveData<Boolean>()
    val navigateToAdmin: LiveData<Boolean> = _navigateToAdmin

    private val _navigateToUser = MutableLiveData<Boolean>()
    val navigateToUser: LiveData<Boolean> = _navigateToUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    ////////////////////////////////////////////////////////////////////////////////
    // NUEVAS FUNCIONES PARA EVITAR "Unresolved reference"
    fun onEmailChanged(newEmail: String) {
        onLoginChanged(newEmail, _password.value ?: "")
    }

    fun onPasswordChanged(newPassword: String) {
        onLoginChanged(_email.value ?: "", newPassword)
    }
    ////////////////////////////////////////////////////////////////////////////////

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password

        // Validaciones en tiempo real
        val emailValid = isValidEmail(email)
        val passwordValid = isValidPassword(password)

        _emailError.value = if (email.isEmpty()) "El email es obligatorio"
        else if (!emailValid) "Formato de email inválido"
        else null

        _passwordError.value = if (password.isEmpty()) "La contraseña es obligatoria"
        else if (!passwordValid) "Mínimo 6 caracteres"
        else null

        _loginEnable.value = emailValid && passwordValid
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean =
        password.length >= 6

    /////////////////////////////////////////////////////////////////////////////
    // LOGIN CON SQLite
    fun loginUsuario() {
        val currentEmail = _email.value ?: ""
        val currentPassword = _password.value ?: ""

        // Validar antes de proceder
        if (!isValidEmail(currentEmail) || !isValidPassword(currentPassword)) {
            _errorMessage.value = "Por favor completa los campos correctamente"
            return
        }

        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            try {
                val usuario = repository.obtenerUsuarioPorEmail(currentEmail)

                if (usuario == null) {
                    _errorMessage.value = "Usuario no encontrado"
                    _isLoading.value = false
                    return@launch
                }

                if (usuario.password != currentPassword) {
                    _errorMessage.value = "Contraseña incorrecta"
                    _isLoading.value = false
                    return@launch
                }

                // Guardar sesión
                println("DEBUG: Login exitoso, guardando userId: ${usuario.idUsua}")
                sessionManager.saveUserId(usuario.idUsua)

                _isLoading.value = false

                when (usuario.tipo) {
                    TipoUsuario.ADMIN -> _navigateToAdmin.value = true
                    TipoUsuario.NORMAL -> _navigateToUser.value = true
                    else -> _errorMessage.value = "Tipo de usuario no reconocido"
                }

            } catch (e: Exception) {
                _errorMessage.value = "Error al iniciar sesión: ${e.message}"
                _isLoading.value = false
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    // LIMPIAR ESTADOS DE NAVEGACIÓN
    fun onNavigateToRegisterCompleted() {
        _navigateToRegister.value = false
    }

    fun onNavigateToAdminCompleted() {
        _navigateToAdmin.value = false
    }

    fun onNavigateToUserCompleted() {
        _navigateToUser.value = false
    }

    /////////////////////////////////////////////////////////////////////////////
    fun navigateToRegister() {
        _navigateToRegister.value = true
    }
}








SesionManager.kt

package mx.edu.utng.appdiario.ui.screens.auth.loginusuario

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SessionManager(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore(name = "user_prefs")
        private val USER_ID_KEY = intPreferencesKey("user_id")
    }

    val userIdFlow: Flow<Int?> = context.dataStore.data.map { prefs ->
        prefs[USER_ID_KEY].also { userId ->
            println("DEBUG: userIdFlow emitido: $userId")
        }
    }

    suspend fun saveUserId(id: Int) {
        println("DEBUG: Guardando userId: $id")
        context.dataStore.edit { prefs ->
            prefs[USER_ID_KEY] = id
        }
        println("DEBUG: userId guardado exitosamente")
    }

    suspend fun clearUserSession() {
        println("DEBUG: Limpiando sesión")
        context.dataStore.edit { prefs ->
            prefs.remove(USER_ID_KEY)
        }
        println("DEBUG: Sesión limpiada exitosamente")
    }
}




appdirio/ui/screens/auth/recuperacioncontrasena

CodeVerificationActivity.kt
package mx.edu.utng.appdiario.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.local.database.AppDatabase
import mx.edu.utng.appdiario.ui.screens.auth.recuperacioncontrasena.PasswordRecoveryViewModel
import mx.edu.utng.appdiario.ui.screens.auth.recuperacioncontrasena.PasswordRecoveryViewModelFactory

@Composable
fun CodeVerificationScreen(
    navController: NavController,
    email: String
) {
    val context = LocalContext.current

    // 🔹 CREAR EL REPOSITORY AQUÍ MISMO
    val usuarioRepository = remember {
        UsuarioRepository(AppDatabase.getDatabase(context).usuarioDao())
    }



    val viewModel: PasswordRecoveryViewModel = viewModel(
        factory = PasswordRecoveryViewModelFactory(
            context = context,
            usuarioRepository = usuarioRepository

        )
    )

    val uiState by viewModel.uiState.collectAsState()

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
                    text = "Verificar Código",
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
                    contentDescription = "Código",
                    tint = Color(0xFF4E2A0E),
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Verificación de Código",
                    color = Color(0xFF4E2A0E),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Ingresa el código de 6 dígitos que enviamos a:",
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

                // Campo de código
                OutlinedTextField(
                    value = uiState.verificationCode,
                    onValueChange = { viewModel.updateVerificationCode(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    label = {
                        Text(
                            "Código de 6 dígitos",
                            color = Color(0xFF4E2A0E)
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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

                Spacer(modifier = Modifier.height(24.dp))

                // Botón de verificar
                Button(
                    onClick = {
                        viewModel.verifyCode(
                            email = email,
                            onSuccess = {
                                navController.navigate("reset_password/${email}")
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6D3B1A)
                    ),
                    enabled = uiState.verificationCode.length == 6 && !uiState.isLoading
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    } else {
                        Text(
                            "Verificar Código",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Reenviar código
                TextButton(
                    onClick = {
                        viewModel.resendCode(email)
                    },
                    enabled = !uiState.isLoading
                ) {
                    Text(
                        "¿No recibiste el código? Reenviar",
                        color = Color(0xFF4E2A0E),
                        fontSize = 14.sp
                    )
                }

                // Mensajes de error/éxito
                if (uiState.errorMessage.isNotBlank()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = uiState.errorMessage,
                        color = Color.Red,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                if (uiState.successMessage.isNotBlank()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = uiState.successMessage,
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
<img width="268" height="595" alt="image" src="https://github.com/user-attachments/assets/d96e1c05-8815-4fcc-b605-1cb5f3638f11" />




PasswordRecoveryActivity.kt
package mx.edu.utng.appdiario.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.ui.screens.auth.recuperacioncontrasena.PasswordRecoveryViewModel
import mx.edu.utng.appdiario.ui.screens.auth.recuperacioncontrasena.PasswordRecoveryViewModelFactory

@Composable
fun PasswordRecoveryScreen(
    navController: NavController,
    usuarioRepository: UsuarioRepository

) {
    val context = LocalContext.current

    val viewModel: PasswordRecoveryViewModel = viewModel(
        factory = PasswordRecoveryViewModelFactory(
            context = context,
            usuarioRepository = usuarioRepository

        )
    )

    val uiState by viewModel.uiState.collectAsState()
    // ✅ ELIMINADO: gmailAuthState observer

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
                    text = "Recuperar Contraseña",
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
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email",
                    tint = Color(0xFF4E2A0E),
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "¿Olvidaste tu contraseña?",
                    color = Color(0xFF4E2A0E),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Ingresa tu email y te enviaremos un código de recuperación",
                    color = Color(0xFF4E2A0E),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )


                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "El código se enviará automáticamente a tu email",
                    color = Color(0xFF8B5A2B),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Campo de email
                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = { viewModel.updateEmail(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    label = {
                        Text(
                            "Email",
                            color = Color(0xFF4E2A0E)
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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

                Spacer(modifier = Modifier.height(24.dp))

                // Botón de enviar código
                Button(
                    onClick = {
                        viewModel.initiatePasswordRecovery(
                            onSuccess = {
                                navController.navigate("code_verification/${uiState.email}")
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6D3B1A)
                    ),
                    enabled = uiState.email.isNotBlank() && !uiState.isLoading
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    } else {
                        Text(
                            "Enviar Código de Recuperación",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }




                if (uiState.errorMessage.isNotBlank()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = uiState.errorMessage,
                        color = Color.Red,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                if (uiState.successMessage.isNotBlank()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = uiState.successMessage,
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


PasswordRecoveryViewModel.kt
package mx.edu.utng.appdiario.ui.screens.auth.recuperacioncontrasena

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.ui.screens.auth.AutoEmailSender

// ✅ ELIMINADO: Estados de Gmail (ya no los necesitamos)
data class PasswordRecoveryState(
    val email: String = "",
    val verificationCode: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val successMessage: String = ""
    // ✅ ELIMINADO: gmailAccount y requiresGmailAuth
)

class PasswordRecoveryViewModel(
    private val usuarioRepository: UsuarioRepository,
    private val recoveryCodeManager: RecoveryCodeManager,
    private val autoEmailSender: AutoEmailSender // ✅ CAMBIADO: Usar AutoEmailSender
) : ViewModel() {

    private val _uiState = MutableStateFlow(PasswordRecoveryState())
    val uiState: StateFlow<PasswordRecoveryState> = _uiState.asStateFlow()

    // ✅ ELIMINADO: Estados de autenticación Gmail

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email, errorMessage = "")
    }

    fun updateVerificationCode(code: String) {
        _uiState.value = _uiState.value.copy(verificationCode = code, errorMessage = "")
    }

    fun initiatePasswordRecovery(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = "")

            try {
                // Verificar si el email existe en tu base de datos
                val usuario = usuarioRepository.obtenerUsuarioPorEmail(_uiState.value.email)

                if (usuario != null) {
                    // Email existe, generar código
                    val recoveryCode = generateRecoveryCode()
                    Log.d("PasswordRecovery", "✅ Código generado: $recoveryCode para email: ${_uiState.value.email}")

                    // 🔹 GUARDAR EL CÓDIGO USANDO RECOVERYCODE MANAGER
                    val saved = recoveryCodeManager.saveRecoveryCode(_uiState.value.email, recoveryCode)

                    if (saved) {
                        Log.d("PasswordRecovery", "✅ Código guardado correctamente")

                        // 🔹 ENVIAR EMAIL AUTOMÁTICO CON AutoEmailSender
                        val emailSent = autoEmailSender.sendPasswordRecoveryEmail(_uiState.value.email, recoveryCode)

                        if (emailSent) {
                            _uiState.value = _uiState.value.copy(
                                successMessage = "✅ Código enviado a ${_uiState.value.email}",
                                isLoading = false
                            )
                            Log.d("PasswordRecovery", "✅ Email AUTOMÁTICO enviado exitosamente")
                            onSuccess()
                        } else {
                            _uiState.value = _uiState.value.copy(
                                errorMessage = "❌ Error al enviar el email. Verifica la configuración SMTP.",
                                isLoading = false
                            )
                            Log.e("PasswordRecovery", "❌ Error al enviar email automático")
                        }
                    } else {
                        _uiState.value = _uiState.value.copy(
                            errorMessage = "❌ Error al guardar el código de recuperación",
                            isLoading = false
                        )
                        Log.e("PasswordRecovery", "❌ Error al guardar código")
                    }
                } else {
                    _uiState.value = _uiState.value.copy(
                        errorMessage = "❌ No existe una cuenta con este email",
                        isLoading = false
                    )
                    Log.e("PasswordRecovery", "❌ Email no encontrado: ${_uiState.value.email}")
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "❌ Error: ${e.message}",
                    isLoading = false
                )
                Log.e("PasswordRecovery", "❌ Error general: ${e.message}", e)
            }
        }
    }

    // ✅ ELIMINADO: Método trySendRecoveryEmail (ya no es necesario)

    fun verifyCode(email: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            // 🔹 VERIFICAR EL CÓDIGO USANDO RECOVERYCODE MANAGER
            val isValid = recoveryCodeManager.verifyRecoveryCode(email, _uiState.value.verificationCode)

            if (isValid) {
                _uiState.value = _uiState.value.copy(isLoading = false)
                Log.d("PasswordRecovery", "✅ Código verificado correctamente")
                onSuccess()
            } else {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "❌ Código inválido o expirado",
                    isLoading = false
                )
                Log.e("PasswordRecovery", "❌ Código inválido: ${_uiState.value.verificationCode}")
            }
        }
    }

    fun resendCode(email: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val newCode = generateRecoveryCode()
                Log.d("PasswordRecovery", "🔄 Reenviando código: $newCode")

                val saved = recoveryCodeManager.saveRecoveryCode(email, newCode)

                if (saved) {
                    // 🔹 ENVIAR EMAIL AUTOMÁTICO CON AutoEmailSender
                    val emailSent = autoEmailSender.sendPasswordRecoveryEmail(email, newCode)

                    if (emailSent) {
                        _uiState.value = _uiState.value.copy(
                            successMessage = "✅ Nuevo código enviado",
                            isLoading = false
                        )
                        Log.d("PasswordRecovery", "✅ Nuevo código enviado exitosamente")
                    } else {
                        _uiState.value = _uiState.value.copy(
                            errorMessage = "❌ Error al reenviar el código.",
                            isLoading = false
                        )
                    }
                } else {
                    _uiState.value = _uiState.value.copy(
                        errorMessage = "❌ Error al generar nuevo código",
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "❌ Error: ${e.message}",
                    isLoading = false
                )
            }
        }
    }

    fun resetPassword(email: String, newPassword: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val usuario = usuarioRepository.obtenerUsuarioPorEmail(email)
                usuario?.let { user ->
                    // Actualizar la contraseña del usuario
                    val usuarioActualizado = user.copy(password = newPassword)
                    usuarioRepository.actualizarUsuario(usuarioActualizado)

                    // 🔹 LIMPIAR EL CÓDIGO DESPUÉS DE USARLO
                    recoveryCodeManager.clearRecoveryCode()

                    Log.d("PasswordRecovery", "✅ Contraseña actualizada para: $email")
                    onResult(true)
                } ?: run {
                    Log.e("PasswordRecovery", "❌ Usuario no encontrado para reset: $email")
                    onResult(false)
                }
            } catch (e: Exception) {
                Log.e("PasswordRecovery", "❌ Error resetting password: ${e.message}", e)
                onResult(false)
            }
        }
    }

    private fun generateRecoveryCode(): String {
        return (100000..999999).random().toString()
    }


    fun clearMessages() {
        _uiState.value = _uiState.value.copy(
            errorMessage = "",
            successMessage = ""
        )
    }
}
<img width="236" height="524" alt="image" src="https://github.com/user-attachments/assets/033f570f-b2fa-48a3-9e7d-4809b47a2f9a" />

PasswordRecoveryViewModelFactory.kt
package mx.edu.utng.appdiario.ui.screens.auth.recuperacioncontrasena

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.ui.screens.auth.AutoEmailSender

class PasswordRecoveryViewModelFactory(
    private val context: Context,
    private val usuarioRepository: UsuarioRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PasswordRecoveryViewModel::class.java)) {
            val recoveryCodeManager = RecoveryCodeManager(context)
            val autoEmailSender = AutoEmailSender(context) // ✅ Crear AutoEmailSender aquí

            return PasswordRecoveryViewModel(
                usuarioRepository = usuarioRepository,
                recoveryCodeManager = recoveryCodeManager,
                autoEmailSender = autoEmailSender // ✅ Pasar al ViewModel
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}



RecoveryCodeManager.kt
package mx.edu.utng.appdiario.ui.screens.auth.recuperacioncontrasena

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecoveryCodeManager(private val context: Context) {

    companion object {
        private const val PREFS_NAME = "recovery_codes"
        private const val KEY_CODE = "recovery_code"
        private const val KEY_EMAIL = "recovery_email"
        private const val KEY_TIMESTAMP = "recovery_timestamp"
        private const val CODE_EXPIRATION_TIME = 15 * 60 * 1000 // 15 minutos
    }

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    suspend fun saveRecoveryCode(email: String, code: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                prefs.edit().apply {
                    putString(KEY_CODE, code)
                    putString(KEY_EMAIL, email)
                    putLong(KEY_TIMESTAMP, System.currentTimeMillis())
                    apply()
                }
                true
            } catch (e: Exception) {
                false
            }
        }
    }

    suspend fun verifyRecoveryCode(email: String, code: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val savedCode = prefs.getString(KEY_CODE, null)
                val savedEmail = prefs.getString(KEY_EMAIL, null)
                val timestamp = prefs.getLong(KEY_TIMESTAMP, 0)

                // Verificar si el código ha expirado
                val isExpired = System.currentTimeMillis() - timestamp > CODE_EXPIRATION_TIME

                if (isExpired) {
                    return@withContext false
                }

                // Verificar que coincidan email y código
                savedCode == code && savedEmail == email
            } catch (e: Exception) {
                false
            }
        }
    }

    suspend fun clearRecoveryCode() {
        withContext(Dispatchers.IO) {
            prefs.edit().clear().apply()
        }
    }

    suspend fun isCodeExpired(): Boolean {
        return withContext(Dispatchers.IO) {
            val timestamp = prefs.getLong(KEY_TIMESTAMP, 0)
            System.currentTimeMillis() - timestamp > CODE_EXPIRATION_TIME
        }
    }
}

ResetPasswordScreen.kt
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

<img width="254" height="554" alt="image" src="https://github.com/user-attachments/assets/87899881-8e95-4464-883f-4fcd80355990" />

appdirio/ui/screens/auth/recuperacioncontrasena

RegistroUi.kt
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
<img width="327" height="726" alt="image" src="https://github.com/user-attachments/assets/5ce22ddb-f927-4c17-8c99-e03834bd8808" />
RegistroViewModel.kt
// --- EL MISMO PACKAGE Y LOS MISMOS IMPORTS ---
package mx.edu.utng.appdiario.ui.screens.auth.registrousuario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.local.entity.Ususario.TipoUsuario
import mx.edu.utng.appdiario.local.entity.Ususario.Usuario

enum class PasswordStrength {
    WEAK, MEDIUM, STRONG, EMPTY
}

data class RegistroState(
    val nombre: String = "",
    val apellidoPaterno: String = "",
    val apellidoMaterno: String = "",
    val fechaNacimiento: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,

    val nombreError: String? = null,
    val apellidoPaternoError: String? = null,
    val apellidoMaternoError: String? = null,
    val fechaNacimientoError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,

    val passwordStrength: PasswordStrength = PasswordStrength.EMPTY,
    val isFormValid: Boolean = false
)

class RegistroViewModel(
    private val repository: UsuarioRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RegistroState())
    val state: StateFlow<RegistroState> = _state.asStateFlow()

    // ------------------ CORREGIDOS LOS onChange ------------------

    fun onNombreChange(nombre: String) {
        val error = validateNombre(nombre)

        _state.value = _state.value.copy(
            nombre = nombre,
            nombreError = error
        )

        actualizarValidacionFormulario()
    }

    fun onApellidoPaternoChange(apellido: String) {
        val error = validateApellido(apellido, "apellido paterno")

        _state.value = _state.value.copy(
            apellidoPaterno = apellido,
            apellidoPaternoError = error
        )

        actualizarValidacionFormulario()
    }

    fun onApellidoMaternoChange(apellido: String) {
        val error = validateApellido(apellido, "apellido materno")

        _state.value = _state.value.copy(
            apellidoMaterno = apellido,
            apellidoMaternoError = error
        )

        actualizarValidacionFormulario()
    }

    fun onFechaNacimientoChange(fecha: String) {
        val formattedFecha = formatDateInput(fecha)
        val error = validateFechaNacimiento(formattedFecha)

        _state.value = _state.value.copy(
            fechaNacimiento = formattedFecha,
            fechaNacimientoError = error
        )

        actualizarValidacionFormulario()
    }

    fun onEmailChange(email: String) {
        val error = validateEmail(email)

        _state.value = _state.value.copy(
            email = email,
            emailError = error
        )

        actualizarValidacionFormulario()
    }

    fun onPasswordChange(password: String) {
        val error = validatePassword(password)
        val strength = calculatePasswordStrength(password)

        val confirmError =
            if (_state.value.confirmPassword.isNotEmpty() &&
                _state.value.confirmPassword != password
            ) "Las contraseñas no coinciden"
            else null

        _state.value = _state.value.copy(
            password = password,
            passwordError = error,
            confirmPasswordError = confirmError,
            passwordStrength = strength
        )

        actualizarValidacionFormulario()
    }

    fun onConfirmPasswordChange(confirm: String) {
        val error =
            if (confirm != _state.value.password) "Las contraseñas no coinciden"
            else null

        _state.value = _state.value.copy(
            confirmPassword = confirm,
            confirmPasswordError = error
        )

        actualizarValidacionFormulario()
    }

    // ------------------ FUNCIÓN CENTRAL DE VALIDACIÓN ------------------

    private fun actualizarValidacionFormulario() {
        _state.value = _state.value.copy(
            isFormValid = validateForm()
        )
    }

    private fun validateForm(): Boolean {
        val s = _state.value

        val confirmValid =
            s.confirmPassword.isNotEmpty() && s.confirmPasswordError == null

        return s.nombreError == null &&
                s.apellidoPaternoError == null &&
                s.apellidoMaternoError == null &&
                s.fechaNacimientoError == null &&
                s.emailError == null &&
                s.passwordError == null &&
                confirmValid &&
                s.nombre.isNotEmpty() &&
                s.apellidoPaterno.isNotEmpty() &&
                s.apellidoMaterno.isNotEmpty() &&
                s.fechaNacimiento.isNotEmpty() &&
                s.email.isNotEmpty() &&
                s.password.isNotEmpty() &&
                s.confirmPassword.isNotEmpty()
    }

    // ------------------ VALIDACIONES ------------------

    private fun validateNombre(nombre: String): String? =
        when {
            nombre.isEmpty() -> "El nombre es obligatorio"
            nombre.length < 2 -> "El nombre debe tener al menos 2 caracteres"
            !nombre.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+\$")) -> "Solo se permiten letras"
            else -> null
        }

    private fun validateApellido(apellido: String, tipo: String): String? =
        when {
            apellido.isEmpty() -> "El $tipo es obligatorio"
            apellido.length < 2 -> "El $tipo debe tener al menos 2 caracteres"
            !apellido.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+\$")) -> "Solo se permiten letras"
            else -> null
        }

    private fun validateFechaNacimiento(fecha: String): String? =
        when {
            fecha.isEmpty() -> "La fecha de nacimiento es obligatoria"
            !fecha.matches(Regex("^\\d{2}/\\d{2}/\\d{4}\$")) -> "Formato debe ser DD/MM/AAAA"
            else -> {
                try {
                    val (day, month, year) = fecha.split("/").map { it.toInt() }
                    when {
                        day !in 1..31 -> "Día inválido"
                        month !in 1..12 -> "Mes inválido"
                        year < 1900 -> "Año inválido"
                        year > 2024 -> "Año no puede ser futuro"
                        else -> null
                    }
                } catch (e: Exception) {
                    "Fecha inválida"
                }
            }
        }

    private fun validateEmail(email: String): String? =
        when {
            email.isEmpty() -> "El email es obligatorio"
            !email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")) -> "Formato de email inválido"
            else -> null
        }

    private fun validatePassword(password: String): String? =
        when {
            password.isEmpty() -> "La contraseña es obligatoria"
            password.length < 8 -> "Mínimo 8 caracteres"
            !password.matches(Regex(".*[A-Z].*")) -> "Debe contener una mayúscula"
            !password.matches(Regex(".*[a-z].*")) -> "Debe contener una minúscula"
            !password.matches(Regex(".*\\d.*")) -> "Debe contener un número"
            !password.matches(Regex(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) ->
                "Debe contener un carácter especial"
            else -> null
        }

    private fun calculatePasswordStrength(password: String): PasswordStrength {
        if (password.isEmpty()) return PasswordStrength.EMPTY
        var score = 0
        if (password.length >= 8) score++
        if (password.matches(Regex(".*[A-Z].*"))) score++
        if (password.matches(Regex(".*[a-z].*"))) score++
        if (password.matches(Regex(".*\\d.*"))) score++
        if (password.matches(Regex(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*"))) score++
        return when (score) {
            in 0..2 -> PasswordStrength.WEAK
            in 3..4 -> PasswordStrength.MEDIUM
            else -> PasswordStrength.STRONG
        }
    }

    private fun formatDateInput(input: String): String {
        val clean = input.filter { it.isDigit() }
        return when {
            clean.length <= 2 -> clean
            clean.length <= 4 -> "${clean.take(2)}/${clean.drop(2)}"
            else -> "${clean.take(2)}/${clean.drop(2).take(2)}/${clean.drop(4).take(4)}"
        }
    }

    // ------------------ REGISTRO ------------------

    fun registrarUsuario() {
        viewModelScope.launch {

            if (!validateForm()) {
                _state.value = _state.value.copy(
                    error = "Por favor corrige los errores del formulario"
                )
                return@launch
            }

            try {
                _state.value = _state.value.copy(isLoading = true, error = null)

                val usuario = Usuario(
                    nombre = state.value.nombre,
                    apellidoPa = state.value.apellidoPaterno,
                    apellidoMa = state.value.apellidoMaterno,
                    fechNaci = state.value.fechaNacimiento,
                    email = state.value.email,
                    password = state.value.password,
                    tipo = TipoUsuario.NORMAL
                )

                val existe = repository.validarUsuarioPorEmail(usuario.email)

                if (existe) {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = "El correo ya está registrado"
                    )
                    return@launch
                }

                repository.insertarUsuario(usuario)

                _state.value = _state.value.copy(
                    isLoading = false,
                    isSuccess = true
                )

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Error al registrar usuario: ${e.message}"
                )
            }
        }
    }
}


RegitroViewModelFactory

package mx.edu.utng.appdiario.ui.screens.auth.registrousuario

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.local.database.AppDatabase

class RegistroViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistroViewModel::class.java)) {
            val database = AppDatabase.getDatabase(context)
            val repository = UsuarioRepository(database.usuarioDao())
            return RegistroViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

appdirio/ui/screens/cliente/dashboardcliente
	DashboardScreen.kt
package mx.edu.utng.appdiario.ui.screens.cliente.dashboardcliente

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun DashboardScreen(
    localNavController: NavHostController,
    globalNavController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3)) // ✅ FONDO AGREGADO
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título de bienvenida
        Text(
            text = "¡Bienvenido a tu Diario!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6D3B1A),
            modifier = Modifier.padding(bottom = 40.dp)
        )

        // Botón Diario Texto - usa localNavController para navegación interna
        Button(
            onClick = { localNavController.navigate("lista_diarios") },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6D3B1A)
            )
        ) {
            Text(
                text = "Diario Texto",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        // Botón Diario Audio - usa localNavController para navegación interna
        Button(
            onClick = { localNavController.navigate("lista_diarios_audio") }, // ✅ CORREGIDO para ir a la lista de audios
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8D4E25)
            )
        ) {
            Text(
                text = "Diario Audio",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }


    }
}
<img width="293" height="651" alt="image" src="https://github.com/user-attachments/assets/fadb9c68-6b7b-4b08-9317-230cefb4aa6e" />
appdirio/ui/screens/cliente/diarioaudio
	appdirio/ui/screens/cliente/diarioaudio/detallediarioaudioscreen

DetalleDiarioAudioScreen.kt
package mx.edu.utng.appdiario.ui.screens.cliente.diarioaudio.detallediarioaudioscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import mx.edu.utng.appdiario.repository.DiarioAudioRepository
import mx.edu.utng.appdiario.repository.TarjetaRepository
import mx.edu.utng.appdiario.audio.AudioManager
import mx.edu.utng.appdiario.local.database.AppDatabase
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DetalleDiarioAudioScreen(
    navController: NavController,
    audioId: Int
) {
    // Obtener el contexto
    val context = LocalContext.current

    // Crear repositorios manualmente
    val diarioAudioDao = AppDatabase.getDatabase(context).diarioAudioDao()
    val tarjetaDao = AppDatabase.getDatabase(context).tarjetaDao()

    val diarioAudioRepository = remember { DiarioAudioRepository(diarioAudioDao) }
    val tarjetaRepository = remember { TarjetaRepository(tarjetaDao, context) }

    // Crear ViewModel manualmente
    val viewModel: DetalleDiarioAudioViewModel = remember {
        DetalleDiarioAudioViewModel(diarioAudioRepository, tarjetaRepository)
    }

    // Estados del ViewModel
    val diarioAudio by viewModel.diarioAudio.collectAsState()
    val tarjeta by viewModel.tarjeta.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val isPlaying by viewModel.isPlaying.collectAsState()
    val currentPosition by viewModel.currentPosition.collectAsState()

    // AudioManager para reproducción
    val audioManager = remember { AudioManager(context) }

    // Cargar datos al iniciar
    LaunchedEffect(audioId) {
        if (!viewModel.datosCargados()) {
            viewModel.cargarDiarioAudio(audioId)
        }
    }

    // Manejar reproducción - VERSIÓN CORREGIDA
    LaunchedEffect(isPlaying) {
        if (isPlaying && diarioAudio != null) {
            val audioFilePath = diarioAudio!!.archivo
            println("🎵 [DetalleScreen] Iniciando reproducción: $audioFilePath")

            // Verificar si el archivo es reproducible
            if (audioManager.isAudioFilePlayable(audioFilePath)) {
                audioManager.startPlaying(audioFilePath) {
                    // Callback cuando termina la reproducción
                    viewModel.setPlayingState(false)
                    viewModel.updateCurrentPosition(0)
                    println("✅ [DetalleScreen] Reproducción completada")
                }
            } else {
                println("❌ [DetalleScreen] Archivo no reproducible: $audioFilePath")
                viewModel.setPlayingState(false)
                // Mostrar error
                viewModel.cargarDiarioAudio(audioId) // Esto mostrará el error en el ViewModel
            }
        } else if (!isPlaying) {
            // Pausar reproducción
            audioManager.stopPlaying()
            println("⏸️ [DetalleScreen] Reproducción detenida")
        }
    }

    // Simular progreso de reproducción
    LaunchedEffect(isPlaying) {
        if (isPlaying && diarioAudio != null) {
            val duracionTotal = diarioAudio!!.audioDuracion
            while (isPlaying && viewModel.currentPosition.value < duracionTotal) {
                delay(1000) // Actualizar cada segundo
                val currentPos = viewModel.currentPosition.value + 1
                viewModel.updateCurrentPosition(currentPos)

                // Si llegamos al final, detener
                if (currentPos >= duracionTotal) {
                    viewModel.setPlayingState(false)
                    viewModel.updateCurrentPosition(0)
                    break
                }
            }
        }
    }

    // Estados locales para la UI
    var showDeleteDialog by remember { mutableStateOf(false) }

    // Pantallas de estado
    if (isLoading) {
        LoadingScreen()
        return
    }

    if (error != null) {
        ErrorScreen(
            error = error!!,
            onRetry = { viewModel.cargarDiarioAudio(audioId) },
            onBack = { navController.popBackStack() }
        )
        return
    }

    if (diarioAudio == null) {
        EmptyStateScreen(onBack = { navController.popBackStack() })
        return
    }

    // Datos reales del audio
    val audio = diarioAudio!!
    val fechaFormateada = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
    val duracionFormateada = viewModel.obtenerDuracionFormateada()
    val tamañoFormateado = viewModel.obtenerTamañoArchivoFormateado()
    val nombreArchivo = viewModel.obtenerNombreArchivo()
    val tipoTarjeta = tarjeta?.tipo?.name ?: "Audio"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3))
            .padding(16.dp)
    ) {
        // Header con botón volver y opciones
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botón volver
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFF6D3B1A), RoundedCornerShape(12.dp))
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    "Volver",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Menú de opciones
            TextButton(
                onClick = { showDeleteDialog = true },
                colors = ButtonDefaults.textButtonColors(contentColor = Color(0xFFD32F2F))
            ) {
                Text("Eliminar")
            }
        }

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
                text = "Fecha: $fechaFormateada",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        }

        // Card principal con el contenido de audio REAL
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
                    .padding(20.dp)
            ) {
                // Header con título REAL y tipo REAL
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = audio.titulo,
                        color = Color(0xFF4E2A0E),
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        modifier = Modifier.weight(1f)
                    )

                    // Badge del tipo REAL
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF6D3B1A)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = tipoTarjeta,
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Información del audio REAL
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Información del Audio:",
                            color = Color(0xFF4E2A0E),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        // Detalles REALES del audio
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Duración:",
                                color = Color(0xFF4E2A0E),
                                fontSize = 14.sp
                            )
                            Text(
                                text = duracionFormateada,
                                color = Color(0xFF4E2A0E),
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Tamaño:",
                                color = Color(0xFF4E2A0E),
                                fontSize = 14.sp
                            )
                            Text(
                                text = tamañoFormateado,
                                color = Color(0xFF4E2A0E),
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Archivo:",
                                color = Color(0xFF4E2A0E),
                                fontSize = 14.sp
                            )
                            Text(
                                text = nombreArchivo,
                                color = Color(0xFF4E2A0E),
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Formato:",
                                color = Color(0xFF4E2A0E),
                                fontSize = 14.sp
                            )
                            Text(
                                text = "M4A",
                                color = Color(0xFF4E2A0E),
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Visualización de la onda de audio
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        // Simulación de onda de audio
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            repeat(20) { index ->
                                val height = (20 + (index % 5) * 8).dp
                                Box(
                                    modifier = Modifier
                                        .width(4.dp)
                                        .height(height)
                                        .background(
                                            if (isPlaying && index <= currentPosition / 2)
                                                Color(0xFF8B5A2B)
                                            else
                                                Color(0xFFD9A97C),
                                            RoundedCornerShape(2.dp)
                                        )
                                )
                            }
                        }

                        // Indicador de tiempo REAL
                        if (isPlaying) {
                            Text(
                                text = "${currentPosition}s / ${audio.audioDuracion}s",
                                color = Color(0xFF4E2A0E),
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                modifier = Modifier.align(Alignment.TopCenter)
                            )
                        } else {
                            Text(
                                text = "Duración total: ${audio.audioDuracion}s",
                                color = Color(0xFF4E2A0E),
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                modifier = Modifier.align(Alignment.TopCenter)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Botón de reproducción grande - VERSIÓN MEJORADA
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = {
                            println("🔄 [DEBUG] Click en botón reproducción")
                            println("🔄 [DEBUG] Estado actual - isPlaying: $isPlaying")
                            println("🔄 [DEBUG] DiarioAudio: $diarioAudio")

                            if (diarioAudio != null) {
                                println("🔄 [DEBUG] Ruta archivo: ${diarioAudio!!.archivo}")
                                val archivo = File(diarioAudio!!.archivo)
                                println("🔄 [DEBUG] Archivo existe: ${archivo.exists()}")
                                println("🔄 [DEBUG] Tamaño archivo: ${archivo.length()} bytes")
                                println("🔄 [DEBUG] Es reproducible: ${audioManager.isAudioFilePlayable(diarioAudio!!.archivo)}")
                            }

                            if (isPlaying) {
                                viewModel.setPlayingState(false)
                                println("⏸️ [DetalleScreen] Usuario pausó la reproducción")
                            } else {
                                if (diarioAudio != null) {
                                    if (audioManager.isAudioFilePlayable(diarioAudio!!.archivo)) {
                                        viewModel.setPlayingState(true)
                                        println("▶️ [DetalleScreen] Usuario inició reproducción")
                                    } else {
                                        println("❌ [DetalleScreen] Archivo no reproducible")
                                        viewModel.cargarDiarioAudio(audioId) // Forzar recarga para mostrar error
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .size(80.dp)
                            .background(
                                if (isPlaying) Color(0xFF8B5A2B) else Color(0xFF6D3B1A),
                                RoundedCornerShape(40.dp)
                            )
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) "Pausar" else "Reproducir",
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }

                // Estado de reproducción
                Text(
                    text = if (isPlaying) "Reproduciendo..." else "Presiona para reproducir",
                    color = Color(0xFF4E2A0E),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

            }
        }
    }

    // Diálogo de confirmación para eliminar
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Eliminar Audio") },
            text = { Text("¿Estás seguro de que quieres eliminar este audio? Esta acción no se puede deshacer.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        viewModel.eliminarDiarioAudio {
                            navController.popBackStack()
                        }
                    }
                ) {
                    Text("Eliminar", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDeleteDialog = false }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }

    // Cleanup cuando se desmonta el composable
    DisposableEffect(Unit) {
        onDispose {
            audioManager.cleanup()
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = Color(0xFF6D3B1A))
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Cargando audio...",
                color = Color(0xFF4E2A0E),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ErrorScreen(error: String, onRetry: () -> Unit, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "❌ Error",
                color = Color(0xFFD32F2F),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                error,
                color = Color(0xFF4E2A0E),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D3B1A))
                ) {
                    Text("Volver")
                }
                Button(
                    onClick = onRetry,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B5A2B))
                ) {
                    Text("Reintentar")
                }
            }
        }
    }
}

@Composable
fun EmptyStateScreen(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "🎵 Audio no encontrado",
                color = Color(0xFF4E2A0E),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "El audio que buscas no está disponible o ha sido eliminado.",
                color = Color(0xFF4E2A0E),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D3B1A))
            ) {
                Text("Volver al inicio")
            }
        }
    }
}
<img width="373" height="852" alt="image" src="https://github.com/user-attachments/assets/e85a41fe-543b-4616-8cbc-3e695b549447" />
DetalleDiarioAudioViewModel.kt
package mx.edu.utng.appdiario.ui.screens.cliente.diarioaudio.detallediarioaudioscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.repository.DiarioAudioRepository
import mx.edu.utng.appdiario.repository.TarjetaRepository
import mx.edu.utng.appdiario.local.entity.Diario.DiarioAudio
import mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta
import java.io.File

class DetalleDiarioAudioViewModel(
    private val diarioAudioRepository: DiarioAudioRepository,
    private val tarjetaRepository: TarjetaRepository
) : ViewModel() {

    // Estados para la UI
    private val _diarioAudio = MutableStateFlow<DiarioAudio?>(null)
    val diarioAudio: StateFlow<DiarioAudio?> = _diarioAudio.asStateFlow()

    private val _tarjeta = MutableStateFlow<Tarjeta?>(null)
    val tarjeta: StateFlow<Tarjeta?> = _tarjeta.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _currentPosition = MutableStateFlow(0)
    val currentPosition: StateFlow<Int> = _currentPosition.asStateFlow()

    // Cargar datos del diario de audio
    fun cargarDiarioAudio(idDiarioAudio: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                println("🎵 [DetalleAudioVM] Cargando diario audio ID: $idDiarioAudio")

                // 1. Obtener el diario de audio
                val audio = diarioAudioRepository.obtenerDiarioAudioPorId(idDiarioAudio)

                if (audio == null) {
                    _error.value = "No se encontró el audio solicitado"
                    println("❌ [DetalleAudioVM] Audio no encontrado para ID: $idDiarioAudio")
                    return@launch
                }

                _diarioAudio.value = audio
                println("🎵 [DetalleAudioVM] Audio cargado: ${audio.titulo}")

                // 2. Obtener la tarjeta asociada
                val tarjetaData = tarjetaRepository.obtenerTarjetaPorId(audio.idTarjeta)
                _tarjeta.value = tarjetaData
                println("🎵 [DetalleAudioVM] Tarjeta cargada: ${tarjetaData?.titulo}")

                // 3. Verificar que el archivo existe
                val archivo = File(audio.archivo)
                if (!archivo.exists()) {
                    println("⚠️ [DetalleAudioVM] Archivo no encontrado: ${audio.archivo}")
                    _error.value = "El archivo de audio no se encuentra disponible"
                } else {
                    println("✅ [DetalleAudioVM] Archivo verificado: ${archivo.length()} bytes")
                }

            } catch (e: Exception) {
                println("❌ [DetalleAudioVM] Error cargando datos: ${e.message}")
                _error.value = "Error al cargar los datos: ${e.message}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Eliminar diario de audio
    fun eliminarDiarioAudio(onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            try {
                val audio = _diarioAudio.value
                if (audio != null) {
                    diarioAudioRepository.eliminarDiarioAudio(audio)

                    // Eliminar archivo físico
                    val archivo = File(audio.archivo)
                    if (archivo.exists()) {
                        archivo.delete()
                        println("🗑️ [DetalleAudioVM] Archivo eliminado: ${audio.archivo}")
                    }

                    println("✅ [DetalleAudioVM] Diario de audio eliminado: ${audio.titulo}")
                    onSuccess()
                }
            } catch (e: Exception) {
                println("❌ [DetalleAudioVM] Error eliminando audio: ${e.message}")
                _error.value = "Error al eliminar el audio: ${e.message}"
            }
        }
    }

    // Actualizar diario de audio
    fun actualizarDiarioAudio(nuevoTitulo: String? = null) {
        viewModelScope.launch {
            try {
                val audioActual = _diarioAudio.value
                if (audioActual != null) {
                    val audioActualizado = audioActual.copy(
                        titulo = nuevoTitulo ?: audioActual.titulo
                    )

                    diarioAudioRepository.actualizarDiarioAudio(audioActualizado)
                    _diarioAudio.value = audioActualizado

                    println("✅ [DetalleAudioVM] Diario de audio actualizado: ${audioActualizado.titulo}")
                }
            } catch (e: Exception) {
                println("❌ [DetalleAudioVM] Error actualizando audio: ${e.message}")
                _error.value = "Error al actualizar el audio: ${e.message}"
            }
        }
    }

    // Control de reproducción
    fun setPlayingState(playing: Boolean) {
        _isPlaying.value = playing
    }

    fun updateCurrentPosition(position: Int) {
        _currentPosition.value = position
    }

    fun limpiarError() {
        _error.value = null
    }

    // Reiniciar estado
    fun reiniciarEstado() {
        _diarioAudio.value = null
        _tarjeta.value = null
        _isPlaying.value = false
        _currentPosition.value = 0
        _error.value = null
    }

    // Verificar si los datos están cargados
    fun datosCargados(): Boolean {
        return _diarioAudio.value != null
    }

    // Obtener información formateada para la UI
    fun obtenerDuracionFormateada(): String {
        val duracion = _diarioAudio.value?.audioDuracion ?: 0
        return "$duracion segundos"
    }

    fun obtenerTamañoArchivoFormateado(): String {
        val archivoPath = _diarioAudio.value?.archivo
        return if (archivoPath != null) {
            val archivo = File(archivoPath)
            if (archivo.exists()) {
                val tamañoKB = archivo.length() / 1024
                "$tamañoKB KB"
            } else {
                "No disponible"
            }
        } else {
            "No disponible"
        }
    }

    fun obtenerNombreArchivo(): String {
        return _diarioAudio.value?.archivo?.substringAfterLast("/") ?: "audio.m4a"
    }
}






	appdirio/ui/screens/cliente/diarioaudio/diarioaudioscreen
DiarioAudioScreen.kt
package mx.edu.utng.appdiario.ui.screens.cliente.diarioaudio

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
import mx.edu.utng.appdiario.ui.screens.auth.loginusuario.SessionManager
import mx.edu.utng.appdiario.ui.screens.cliente.diarioaudio.diarioaudioscreen.DiarioAudioViewModel
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
                    diarioAudioRepository = mx.edu.utng.appdiario.repository.DiarioAudioRepository(diarioAudioDao),
                    tarjetaRepository = mx.edu.utng.appdiario.repository.TarjetaRepository(tarjetaDao, context)
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

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioOption(
                        text = "RESETA",
                        selected = selectedOption == "RESETAS",
                        onSelected = { selectedOption = "RESETAS" }
                    )

                    RadioOption(
                        text = "PERSONAL",
                        selected = selectedOption == "PERSONAL",
                        onSelected = { selectedOption = "PERSONAL" }
                    )

                    RadioOption(
                        text = "ACTIVIDAD",
                        selected = selectedOption == "ACTIVIDADES",
                        onSelected = { selectedOption = "ACTIVIDADES" }
                    )
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
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
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
<img width="188" height="417" alt="image" src="https://github.com/user-attachments/assets/2ab69f2b-7ca0-4f21-9b35-82b7ce074055" />
DiarioAudioViewModel.kt

package mx.edu.utng.appdiario.ui.screens.cliente.diarioaudio.diarioaudioscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.repository.DiarioAudioRepository
import mx.edu.utng.appdiario.repository.TarjetaRepository
import mx.edu.utng.appdiario.local.entity.Diario.DiarioAudio
import mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta
import mx.edu.utng.appdiario.local.entity.Tarjeta.TipoTarjeta

class DiarioAudioViewModel(
    private val diarioAudioRepository: DiarioAudioRepository,
    private val tarjetaRepository: TarjetaRepository
) : ViewModel() {

    // Estado para la UI
    private val _uiState = MutableStateFlow(DiarioAudioUiState())
    val uiState: StateFlow<DiarioAudioUiState> = _uiState.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    data class DiarioAudioUiState(
        val titulo: String = "",
        val archivo: String = "",
        val audioDuracion: Int = 0,
        val idTarjeta: Int? = null
    )

    fun actualizarTitulo(titulo: String) {
        _uiState.value = _uiState.value.copy(titulo = titulo)
    }

    fun actualizarArchivo(archivo: String) {
        _uiState.value = _uiState.value.copy(archivo = archivo)
    }

    fun actualizarDuracion(duracion: Int) {
        _uiState.value = _uiState.value.copy(audioDuracion = duracion)
    }

    // Método principal para crear diario de audio
    fun crearDiarioAudio(usuarioId: Int, tipo: TipoTarjeta) {
        viewModelScope.launch {
            try {
                println("🎵 [AudioViewModel] Creando diario de audio para usuario: $usuarioId, tipo: $tipo")

                // 1. Buscar o crear tarjeta
                val tarjeta = obtenerOCrearTarjeta(usuarioId, tipo)
                println("🎵 [AudioViewModel] Tarjeta obtenida/creada: ${tarjeta.idTarjeta} - ${tarjeta.titulo}")

                // 2. Crear el diario de audio
                val diarioAudio = DiarioAudio(
                    titulo = _uiState.value.titulo,
                    archivo = _uiState.value.archivo,
                    audioDuracion = _uiState.value.audioDuracion,
                    idTarjeta = tarjeta.idTarjeta
                )

                // 3. Guardar en la base de datos
                diarioAudioRepository.insertarDiarioAudio(diarioAudio)
                println("🎵 [AudioViewModel] Diario de audio creado exitosamente: ${diarioAudio.titulo}")

                // 4. Limpiar el estado
                _uiState.value = DiarioAudioUiState()

            } catch (e: Exception) {
                println("❌ [AudioViewModel] Error al crear diario de audio: ${e.message}")
                _error.value = "Error al crear el diario de audio: ${e.message}"
            }
        }
    }

    // Método para editar diario existente
    fun actualizarDiarioAudio() {
        viewModelScope.launch {
            try {
                val currentState = _uiState.value
                if (currentState.idTarjeta != null) {
                    // Aquí implementarías la actualización si es necesario
                    println("🎵 [AudioViewModel] Actualizando diario de audio")
                }
            } catch (e: Exception) {
                println("❌ [AudioViewModel] Error al actualizar diario de audio: ${e.message}")
                _error.value = "Error al actualizar el diario de audio: ${e.message}"
            }
        }
    }

    // Método para cargar un diario existente (para edición)
    fun cargarDiarioAudio(idDiarioAudio: Int) {
        viewModelScope.launch {
            try {
                val diario = diarioAudioRepository.obtenerDiarioAudioPorId(idDiarioAudio)
                diario?.let {
                    _uiState.value = _uiState.value.copy(
                        titulo = it.titulo,
                        archivo = it.archivo,
                        audioDuracion = it.audioDuracion,
                        idTarjeta = it.idTarjeta
                    )
                    println("🎵 [AudioViewModel] Diario de audio cargado: ${it.titulo}")
                }
            } catch (e: Exception) {
                println("❌ [AudioViewModel] Error al cargar diario de audio: ${e.message}")
                _error.value = "Error al cargar el diario de audio: ${e.message}"
            }
        }
    }

    // Función para obtener o crear tarjeta (igual que en texto)
    private suspend fun obtenerOCrearTarjeta(usuarioId: Int, tipo: TipoTarjeta): Tarjeta {
        // Buscar tarjetas existentes del usuario
        val tarjetasUsuario = tarjetaRepository.obtenerTarjetasPorUsuario(usuarioId)

        // Buscar si ya existe una tarjeta del tipo solicitado
        val tarjetaExistente = tarjetasUsuario.find { it.tipo == tipo }

        return if (tarjetaExistente != null) {
            println("🎵 [AudioViewModel] Tarjeta existente encontrada: ${tarjetaExistente.titulo}")
            tarjetaExistente
        } else {
            // Crear nueva tarjeta
            val nuevaTarjeta = Tarjeta(
                titulo = when (tipo) {
                    TipoTarjeta.PERSONAL -> "Notas Personales"
                    TipoTarjeta.RESETAS -> "Mis Recetas"
                    TipoTarjeta.ACTIVIDADES -> "Mis Actividades"
                },
                tipo = tipo,
                idUsua = usuarioId,
            )

            val tarjetaId = tarjetaRepository.insertarTarjeta(nuevaTarjeta)
            nuevaTarjeta.copy(idTarjeta = tarjetaId).also {
                println("🎵 [AudioViewModel] Nueva tarjeta creada: ${it.titulo}")
            }
        }
    }

    fun limpiarError() {
        _error.value = null
    }
}


	appdirio/ui/screens/cliente/diarioaudio/listadiarioaudioscreen

ListaDiarioAudioScreen.kt
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

ListaDiariosAudioViewModel.kt
package mx.edu.utng.appdiario.ui.screens.cliente.diarioaudio.listadiariosaudioscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.repository.DiarioAudioRepository
import mx.edu.utng.appdiario.repository.TarjetaRepository
import mx.edu.utng.appdiario.local.entity.Diario.DiarioAudio
import mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta
import mx.edu.utng.appdiario.ui.screens.auth.loginusuario.SessionManager

class ListaDiariosAudioViewModel(
    private val tarjetaRepository: TarjetaRepository,
    private val diarioAudioRepository: DiarioAudioRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _tarjetas = MutableStateFlow<List<Tarjeta>>(emptyList())
    val tarjetas: StateFlow<List<Tarjeta>> = _tarjetas.asStateFlow()

    private val _audiosPorTarjeta = MutableStateFlow<Map<Int, List<DiarioAudio>>>(emptyMap())
    val audiosPorTarjeta: StateFlow<Map<Int, List<DiarioAudio>>> = _audiosPorTarjeta.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _userId = MutableStateFlow<Int?>(null)
    val userId: StateFlow<Int?> = _userId.asStateFlow()

    private var selectedFilter = "RESETAS"

    init {
        println("🎵 [AudioListViewModel] INIT - Inicializando ListaDiariosAudioViewModel")

        viewModelScope.launch {
            sessionManager.userIdFlow.collect { userId ->
                _userId.value = userId
                println("🎵 [AudioListViewModel] userId actualizado: $userId")

                if (userId != null) {
                    println("🎵 [AudioListViewModel] Cargando datos iniciales de audio...")
                    cargarAudiosPorTipo("RESETAS")
                }
            }
        }
    }

    fun cargarAudiosPorTipo(tipo: String) {
        selectedFilter = tipo

        val currentUserId = _userId.value
        if (currentUserId == null) {
            _error.value = "Usuario no autenticado"
            return
        }

        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                println("🎵 [AudioListViewModel] Cargando audios para usuario: $currentUserId, tipo: $tipo")

                // 1. Obtener tarjetas por tipo y usuario
                val tarjetasObtenidas = tarjetaRepository.obtenerTarjetasPorTipoYUsuario(tipo, currentUserId)
                _tarjetas.value = tarjetasObtenidas

                println("🎵 [AudioListViewModel] Tarjetas obtenidas: ${tarjetasObtenidas.size}")

                // 2. Obtener audios para cada tarjeta
                val audiosMap = mutableMapOf<Int, List<DiarioAudio>>()
                tarjetasObtenidas.forEach { tarjeta ->
                    val audios = diarioAudioRepository.obtenerDiariosAudioPorTarjeta(tarjeta.idTarjeta)
                    audiosMap[tarjeta.idTarjeta] = audios
                    println("🎵 [AudioListViewModel] Tarjeta ${tarjeta.idTarjeta} - Audios: ${audios.size}")
                }
                _audiosPorTarjeta.value = audiosMap

            } catch (e: Exception) {
                _error.value = "Error al cargar los audios: ${e.message}"
                println("❌ [AudioListViewModel] Error al cargar audios: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Método para eliminar audio
    fun eliminarAudio(audio: DiarioAudio) {
        println("🗑️ [AudioListViewModel] Eliminando audio: ${audio.idDiarioAudio} - '${audio.titulo}'")

        viewModelScope.launch {
            try {
                diarioAudioRepository.eliminarDiarioAudio(audio)
                println("✅ [AudioListViewModel] Audio eliminado exitosamente")

                // Actualizar StateFlow inmediatamente
                val audiosMapActualizado = _audiosPorTarjeta.value.toMutableMap()
                audiosMapActualizado.forEach { (tarjetaId, audios) ->
                    val audiosFiltrados = audios.filter { it.idDiarioAudio != audio.idDiarioAudio }
                    audiosMapActualizado[tarjetaId] = audiosFiltrados
                }
                _audiosPorTarjeta.value = audiosMapActualizado

            } catch (e: Exception) {
                println("❌ [AudioListViewModel] Error al eliminar audio: ${e.message}")
                _error.value = "Error al eliminar el audio: ${e.message}"
            }
        }
    }

    fun limpiarError() {
        _error.value = null
    }

    // Método para obtener el primer audio de una tarjeta
    fun obtenerPrimerAudio(tarjetaId: Int): DiarioAudio? {
        return _audiosPorTarjeta.value[tarjetaId]?.firstOrNull()
    }

    // Debug method
    fun debugTiposDisponibles() {
        val currentUserId = _userId.value ?: return

        viewModelScope.launch {
            println("\n🔍 [AudioListViewModel] Tipos de tarjetas disponibles para usuario $currentUserId:")
            val todasTarjetas = tarjetaRepository.obtenerTarjetasPorUsuario(currentUserId)
            val tiposUnicos = todasTarjetas.map { it.tipo.name }.distinct()
            tiposUnicos.forEach { tipo ->
                println("   🏷️  Tipo: '$tipo'")
            }
        }
    }
}
<img width="158" height="352" alt="image" src="https://github.com/user-attachments/assets/2d8ed0e5-39c6-43dd-9cac-67b3e4a0876f" />
appdirio/ui/screens/cliente/diariotexto
	appdirio/ui/screens/cliente/diariotexto/detallediarioscreen
DetalleDiarioScreen.kt
package mx.edu.utng.appdiario.ui.screens.cliente.diariotexto.detallediarioscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mx.edu.utng.appdiario.repository.DiarioTextoRepository
import mx.edu.utng.appdiario.repository.TarjetaRepository
import mx.edu.utng.appdiario.local.database.AppDatabase

@Composable
fun DetalleDiarioScreen(
    navController: NavController,
    diarioTextoId: Int
) {
    // Obtener el contexto
    val context = LocalContext.current

    // Crear repositorios manualmente
    val diarioTextoDao = AppDatabase.getDatabase(context).diarioTextoDao()
    val tarjetaDao = AppDatabase.getDatabase(context).tarjetaDao()

    val diarioTextoRepository = remember { DiarioTextoRepository(diarioTextoDao) }
    val tarjetaRepository = remember { TarjetaRepository(tarjetaDao, context) }

    // Crear ViewModel manualmente
    val viewModel: DetalleDiarioTextoViewModel = remember {
        DetalleDiarioTextoViewModel(diarioTextoRepository, tarjetaRepository)
    }

    // Estados del ViewModel
    val diarioTexto by viewModel.diarioTexto.collectAsState()
    val tarjeta by viewModel.tarjeta.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    // Cargar datos al iniciar
    LaunchedEffect(diarioTextoId) {
        if (!viewModel.datosCargados()) {
            viewModel.cargarDiarioTexto(diarioTextoId)
        }
    }

    // Estados locales para la UI
    var showDeleteDialog by remember { mutableStateOf(false) }

    // Pantallas de estado
    if (isLoading) {
        LoadingScreen()
        return
    }

    if (error != null) {
        ErrorScreen(
            error = error!!,
            onRetry = { viewModel.cargarDiarioTexto(diarioTextoId) },
            onBack = { navController.popBackStack() }
        )
        return
    }

    if (diarioTexto == null) {
        EmptyStateScreen(onBack = { navController.popBackStack() })
        return
    }

    // Datos reales del diario
    val titulo = viewModel.obtenerTitulo()
    val contenido = viewModel.obtenerContenidoConFormato()
    val tipoTarjeta = viewModel.obtenerTipoTarjeta()
    val fechaCreacion = viewModel.obtenerFechaCreacionFormateada()
    val estadisticas = viewModel.obtenerEstadisticasContenido()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3))
            .padding(16.dp)
    ) {
        // Header con botón volver y opciones
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botón volver
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFF6D3B1A), RoundedCornerShape(12.dp))
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    "Volver",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Menú de opciones
            Row {
                // Botón editar
                IconButton(
                    onClick = {
                        // Navegar a pantalla de edición
                        navController.navigate("editar_diario_texto/$diarioTextoId")
                    },
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0xFF8B5A2B), RoundedCornerShape(12.dp))
                ) {
                    Icon(
                        Icons.Default.Edit,
                        "Editar",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Botón eliminar
                IconButton(
                    onClick = { showDeleteDialog = true },
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0xFFD32F2F), RoundedCornerShape(12.dp))
                ) {
                    Icon(
                        Icons.Default.Delete,
                        "Eliminar",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

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
                text = "Creado: $fechaCreacion",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        }

        // Card principal con el contenido
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
                    .padding(20.dp)
            ) {
                // Header con título y tipo
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = titulo,
                        color = Color(0xFF4E2A0E),
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        modifier = Modifier.weight(1f)
                    )

                    // Badge del tipo
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF6D3B1A)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = tipoTarjeta,
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))




                Spacer(modifier = Modifier.height(20.dp))

                // Contenido del diario
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Contenido:",
                            color = Color(0xFF4E2A0E),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        // Área de texto con scroll
                        Text(
                            text = contenido,
                            color = Color(0xFF4E2A0E),
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        )
                    }
                }
            }
        }
    }

    // Diálogo de confirmación para eliminar
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Eliminar Diario") },
            text = { Text("¿Estás seguro de que quieres eliminar este diario? Esta acción no se puede deshacer.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        viewModel.eliminarDiarioTexto {
                            navController.popBackStack()
                        }
                    }
                ) {
                    Text("Eliminar", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDeleteDialog = false }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

// Las funciones LoadingScreen, ErrorScreen y EmptyStateScreen se mantienen igual que en el ejemplo anterior
@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = Color(0xFF6D3B1A))
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Cargando diario...",
                color = Color(0xFF4E2A0E),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ErrorScreen(error: String, onRetry: () -> Unit, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "❌ Error",
                color = Color(0xFFD32F2F),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                error,
                color = Color(0xFF4E2A0E),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D3B1A))
                ) {
                    Text("Volver")
                }
                Button(
                    onClick = onRetry,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B5A2B))
                ) {
                    Text("Reintentar")
                }
            }
        }
    }
}

@Composable
fun EmptyStateScreen(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "📝 Diario no encontrado",
                color = Color(0xFF4E2A0E),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "El diario que buscas no está disponible o ha sido eliminado.",
                color = Color(0xFF4E2A0E),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D3B1A))
            ) {
                Text("Volver al inicio")
            }
        }
    }
}

<img width="147" height="326" alt="image" src="https://github.com/user-attachments/assets/d40344f3-29a2-4ca5-a374-ce5aa55a5dd6" />
DetalleDiarioTextoViewModel.kt
package mx.edu.utng.appdiario.ui.screens.cliente.diariotexto.detallediarioscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.repository.DiarioTextoRepository
import mx.edu.utng.appdiario.repository.TarjetaRepository
import mx.edu.utng.appdiario.local.entity.Diario.DiarioTexto
import mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta
import java.text.SimpleDateFormat
import java.util.*

class DetalleDiarioTextoViewModel(
    private val diarioTextoRepository: DiarioTextoRepository,
    private val tarjetaRepository: TarjetaRepository
) : ViewModel() {

    // Estados para la UI
    private val _diarioTexto = MutableStateFlow<DiarioTexto?>(null)
    val diarioTexto: StateFlow<DiarioTexto?> = _diarioTexto.asStateFlow()

    private val _tarjeta = MutableStateFlow<Tarjeta?>(null)
    val tarjeta: StateFlow<Tarjeta?> = _tarjeta.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // Cargar datos del diario de texto
    fun cargarDiarioTexto(idDiarioTexto: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                println("📝 [DetalleTextoVM] Cargando diario texto ID: $idDiarioTexto")

                // 1. Obtener el diario de texto
                val texto = diarioTextoRepository.obtenerDiarioTextoPorId(idDiarioTexto)

                if (texto == null) {
                    _error.value = "No se encontró el diario solicitado"
                    println("❌ [DetalleTextoVM] Diario no encontrado para ID: $idDiarioTexto")
                    return@launch
                }

                _diarioTexto.value = texto
                println("📝 [DetalleTextoVM] Diario cargado: ${texto.titulo}")
                println("📝 [DetalleTextoVM] Contenido: ${texto.texto.take(50)}...")

                // 2. Obtener la tarjeta asociada
                val tarjetaData = tarjetaRepository.obtenerTarjetaPorId(texto.idTarjeta)
                _tarjeta.value = tarjetaData
                println("📝 [DetalleTextoVM] Tarjeta cargada: ${tarjetaData?.titulo}")

            } catch (e: Exception) {
                println("❌ [DetalleTextoVM] Error cargando datos: ${e.message}")
                _error.value = "Error al cargar los datos: ${e.message}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Eliminar diario de texto
    fun eliminarDiarioTexto(onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            try {
                val texto = _diarioTexto.value
                if (texto != null) {
                    diarioTextoRepository.eliminarDiarioTexto(texto)
                    println("✅ [DetalleTextoVM] Diario de texto eliminado: ${texto.titulo}")
                    onSuccess()
                }
            } catch (e: Exception) {
                println("❌ [DetalleTextoVM] Error eliminando diario: ${e.message}")
                _error.value = "Error al eliminar el diario: ${e.message}"
            }
        }
    }

    // Actualizar diario de texto
    fun actualizarDiarioTexto(nuevoTitulo: String? = null, nuevoContenido: String? = null) {
        viewModelScope.launch {
            try {
                val textoActual = _diarioTexto.value
                if (textoActual != null) {
                    val textoActualizado = textoActual.copy(
                        titulo = nuevoTitulo ?: textoActual.titulo,
                        texto = nuevoContenido ?: textoActual.texto
                        // Nota: Tu entidad no tiene fechaActualizacion, solo fechaCreacion
                    )

                    diarioTextoRepository.actualizarDiarioTexto(textoActualizado)
                    _diarioTexto.value = textoActualizado

                    println("✅ [DetalleTextoVM] Diario de texto actualizado: ${textoActualizado.titulo}")
                }
            } catch (e: Exception) {
                println("❌ [DetalleTextoVM] Error actualizando diario: ${e.message}")
                _error.value = "Error al actualizar el diario: ${e.message}"
            }
        }
    }

    fun limpiarError() {
        _error.value = null
    }

    // Reiniciar estado
    fun reiniciarEstado() {
        _diarioTexto.value = null
        _tarjeta.value = null
        _error.value = null
    }

    // Verificar si los datos están cargados
    fun datosCargados(): Boolean {
        return _diarioTexto.value != null
    }

    // Obtener información formateada para la UI
    fun obtenerFechaCreacionFormateada(): String {
        val fecha = _diarioTexto.value?.fechaCreacion
        return if (fecha != null) {
            try {
                // Tu fecha tiene formato "yyyy-MM-dd HH:mm:ss"
                val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val outputFormat = SimpleDateFormat("dd/MM/yyyy 'a las' HH:mm", Locale.getDefault())
                val date = inputFormat.parse(fecha)
                outputFormat.format(date ?: Date())
            } catch (e: Exception) {
                // Si falla el parsing, intentar con formato simple
                try {
                    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val date = inputFormat.parse(fecha)
                    outputFormat.format(date ?: Date())
                } catch (e2: Exception) {
                    "Fecha no disponible"
                }
            }
        } else {
            "Fecha no disponible"
        }
    }

    fun obtenerContenidoConFormato(): String {
        return _diarioTexto.value?.texto ?: "No hay contenido disponible"
    }

    fun obtenerTitulo(): String {
        return _diarioTexto.value?.titulo ?: "Sin título"
    }

    fun obtenerTipoTarjeta(): String {
        return _tarjeta.value?.tipo?.name ?: "Diario"
    }

    // Obtener estadísticas del contenido
    fun obtenerEstadisticasContenido(): Map<String, Int> {
        val contenido = _diarioTexto.value?.texto ?: ""
        return mapOf(
            "caracteres" to contenido.length,
            "palabras" to contenido.split("\\s+".toRegex()).count { it.isNotBlank() },
            "lineas" to contenido.split("\n").size
        )
    }

    // Obtener fecha de creación simple (solo fecha)
    fun obtenerFechaCreacionSimple(): String {
        val fecha = _diarioTexto.value?.fechaCreacion
        return if (fecha != null) {
            try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val date = inputFormat.parse(fecha)
                outputFormat.format(date ?: Date())
            } catch (e: Exception) {
                "Fecha no disponible"
            }
        } else {
            "Fecha no disponible"
        }
    }
}
<img width="220" height="426" alt="image" src="https://github.com/user-attachments/assets/18b5b819-1c34-43d3-bf3c-ff98cdeaa7fa" />
	appdirio/ui/screens/cliente/diariotexto/diariotextoscreen
DiarioTextoScreen.kt
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import mx.edu.utng.appdiario.local.entity.Tarjeta.TipoTarjeta
import mx.edu.utng.appdiario.ui.screens.auth.loginusuario.SessionManager
import mx.edu.utng.appdiario.ui.screens.cliente.diarioaudio.RadioOption
import mx.edu.utng.appdiario.ui.screens.cliente.diarioaudio.TextFieldLabeled
import mx.edu.utng.appdiario.ui.screens.cliente.diariotexto.diariotextoscreen.DiarioTextoViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiarioTextoScreen(
    navController: NavHostController,
    viewModel: DiarioTextoViewModel,
    sessionManager: SessionManager,
    idDiarioTexto: Int? = null,
    modoEdicion: Boolean = false
) {
    val uiState by viewModel.uiState.collectAsState()

    // 🔥 Obtener ID del usuario desde DataStore
    val userId by sessionManager.userIdFlow.collectAsState(initial = null)

    // 🔥 Tipo seleccionado
    val tipoSeleccionado = remember { mutableStateOf<TipoTarjeta?>(null) }

    LaunchedEffect(modoEdicion, idDiarioTexto) {
        if (modoEdicion && idDiarioTexto != null) {
            viewModel.cargarDiario(idDiarioTexto)
        }
    }

    val fechaActual = remember {
        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3))
            .padding(16.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF6D3B1A)),
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

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFD9A97C)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Text(
                    text = if (modoEdicion) "Editar Diario" else "Nuevo Diario",
                    color = Color(0xFF4E2A0E),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textAlign = TextAlign.Center
                )

                // ------------------------------
                // 🔥 SELECTOR HORIZONTAL BONITO
                // ------------------------------
                if (!modoEdicion) {
                    Text(
                        text = "Tipo de entrada:",
                        color = Color(0xFF4E2A0E),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        listOf(
                            TipoTarjeta.RESETAS to "RESETA",
                            TipoTarjeta.PERSONAL to "PERSONAL",
                            TipoTarjeta.ACTIVIDADES to "ACTIVIDAD"
                        ).forEach { (tipo, texto) ->
                            Row(
                                modifier = Modifier.weight(1f),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                RadioOption(
                                    text = texto,
                                    selected = tipoSeleccionado.value == tipo,
                                    onSelected = { tipoSeleccionado.value = tipo }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
                // -------------------------------------

                TextFieldLabeled(
                    label = "Título:",
                    value = uiState.titulo,
                    onValueChange = { viewModel.actualizarTitulo(it) },
                    singleLine = true,
                    height = 60.dp
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextFieldLabeled(
                    label = "Contenido:",
                    value = uiState.contenido,
                    onValueChange = { viewModel.actualizarContenido(it) },
                    singleLine = false,
                    height = 200.dp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        if (modoEdicion) {
                            viewModel.guardar(uiState.idTarjeta ?: 0)
                        } else if (userId != null && tipoSeleccionado.value != null) {
                            viewModel.crearDiario(userId!!, tipoSeleccionado.value!!)
                        }

                        navController.navigate("lista_diarios") {
                            popUpTo("lista_diarios") { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B5A2B)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = if (modoEdicion) "GUARDAR CAMBIOS" else "CREAR DIARIO",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

DiarioTextoUiState.kt
package mx.edu.utng.appdiario.ui.screens.cliente.diariotexto.diariotextoscreen


data class DiarioTextoUiState(
    val titulo: String = "",
    val contenido: String = "",
    val idTarjeta: Int? = null,
    val idDiarioTexto: Int? = null,
    val modoEdicion: Boolean = false,
    val cargando: Boolean = false,
    val guardado: Boolean = false,
    val error: String? = null
)

DiarioTextoViewModel.kt
package mx.edu.utng.appdiario.ui.screens.cliente.diariotexto.diariotextoscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.repository.DiarioTextoRepository
import mx.edu.utng.appdiario.repository.TarjetaRepository
import mx.edu.utng.appdiario.local.entity.Diario.DiarioTexto
import mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta
import mx.edu.utng.appdiario.local.entity.Tarjeta.TipoTarjeta

class DiarioTextoViewModel(
    private val repo: DiarioTextoRepository,
    private val tarjetaRepo: TarjetaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DiarioTextoUiState())
    val uiState = _uiState.asStateFlow()

    fun limpiarCampos() {
        _uiState.update {
            it.copy(
                titulo = "",
                contenido = "",
                modoEdicion = false,
                idDiarioTexto = null,
                idTarjeta = null,
                guardado = false
            )
        }
    }

    fun cargarDiario(id: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true, error = null) }

            try {
                val diario = repo.obtenerDiarioTextoPorId(id)

                if (diario != null) {
                    _uiState.update {
                        it.copy(
                            titulo = diario.titulo,
                            contenido = diario.texto,
                            modoEdicion = true,
                            idDiarioTexto = diario.idDiarioTexto,
                            idTarjeta = diario.idTarjeta,
                            cargando = false
                        )
                    }
                } else {
                    _uiState.update { it.copy(cargando = false, error = "Diario no encontrado") }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(cargando = false, error = "Error al cargar: ${e.message}") }
            }
        }
    }

    fun actualizarTitulo(nuevo: String) {
        _uiState.update { it.copy(titulo = nuevo) }
    }

    fun actualizarContenido(nuevo: String) {
        _uiState.update { it.copy(contenido = nuevo) }
    }

    // =====================================================
    // 🔥 MÉTODO crearDiario() FINAL
    // =====================================================
    fun crearDiario(idUsuario: Int, tipo: TipoTarjeta) {
        viewModelScope.launch {

            _uiState.update { it.copy(cargando = true, error = null) }

            try {
                val estado = _uiState.value

                // 1️⃣ Buscar si el usuario ya tiene una tarjeta de ese tipo
                val tarjetasUsuario = tarjetaRepo.obtenerTarjetasPorUsuario(idUsuario)
                var tarjeta = tarjetasUsuario.find { it.tipo == tipo }

                // 2️⃣ Si NO existe → crearla usando insertar() directamente
                if (tarjeta == null) {

                    val nuevaTarjeta = Tarjeta(
                        titulo = when (tipo) {
                            TipoTarjeta.PERSONAL -> "Notas Personales"
                            TipoTarjeta.RESETAS -> "Mis Recetas"
                            TipoTarjeta.ACTIVIDADES -> "Mis Actividades"
                        },
                        tipo = tipo,
                        idUsua = idUsuario
                    )

                    // Insertar tarjeta y obtener ID generado
                    val idGenerado = tarjetaRepo.insertarTarjeta(nuevaTarjeta).toInt()

                    // Volver a obtener la tarjeta creada
                    tarjeta = tarjetaRepo.obtenerTarjetaPorId(idGenerado)
                }

                // 3️⃣ Crear el diario ligado a dicha tarjeta
                repo.insertarDiarioTexto(
                    DiarioTexto(
                        titulo = estado.titulo,
                        texto = estado.contenido,
                        idTarjeta = tarjeta!!.idTarjeta
                    )
                )

                limpiarCampos()

                _uiState.update { it.copy(cargando = false, guardado = true) }

            } catch (e: Exception) {
                _uiState.update { it.copy(cargando = false, error = "Error al guardar diario: ${e.message}") }
            }
        }
    }


    // =====================================================
    // MÉTODO GUARDAR ORIGINAL (SOLO PARA EDICIÓN)
    // =====================================================
    fun guardar(idTarjetaNav: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true, error = null) }

            val estado = _uiState.value

            try {
                if (estado.modoEdicion && estado.idDiarioTexto != null) {
                    repo.actualizarDiarioTexto(
                        DiarioTexto(
                            idDiarioTexto = estado.idDiarioTexto,
                            titulo = estado.titulo,
                            texto = estado.contenido,
                            idTarjeta = estado.idTarjeta ?: idTarjetaNav
                        )
                    )
                } else {
                    repo.insertarDiarioTexto(
                        DiarioTexto(
                            titulo = estado.titulo,
                            texto = estado.contenido,
                            idTarjeta = idTarjetaNav
                        )
                    )
                    limpiarCampos()
                }

                _uiState.update { it.copy(cargando = false, guardado = true) }

            } catch (e: Exception) {
                _uiState.update { it.copy(cargando = false, error = "Error al guardar: ${e.message}") }
            }
        }
    }
}

	appdirio/ui/screens/cliente/diariotexto/listadiarioscreen
package mx.edu.utng.appdiario.ui.screens.cliente.diariotexto.diariotextoscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.repository.DiarioTextoRepository
import mx.edu.utng.appdiario.repository.TarjetaRepository
import mx.edu.utng.appdiario.local.entity.Diario.DiarioTexto
import mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta
import mx.edu.utng.appdiario.local.entity.Tarjeta.TipoTarjeta

class DiarioTextoViewModel(
    private val repo: DiarioTextoRepository,
    private val tarjetaRepo: TarjetaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DiarioTextoUiState())
    val uiState = _uiState.asStateFlow()

    fun limpiarCampos() {
        _uiState.update {
            it.copy(
                titulo = "",
                contenido = "",
                modoEdicion = false,
                idDiarioTexto = null,
                idTarjeta = null,
                guardado = false
            )
        }
    }

    fun cargarDiario(id: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true, error = null) }

            try {
                val diario = repo.obtenerDiarioTextoPorId(id)

                if (diario != null) {
                    _uiState.update {
                        it.copy(
                            titulo = diario.titulo,
                            contenido = diario.texto,
                            modoEdicion = true,
                            idDiarioTexto = diario.idDiarioTexto,
                            idTarjeta = diario.idTarjeta,
                            cargando = false
                        )
                    }
                } else {
                    _uiState.update { it.copy(cargando = false, error = "Diario no encontrado") }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(cargando = false, error = "Error al cargar: ${e.message}") }
            }
        }
    }

    fun actualizarTitulo(nuevo: String) {
        _uiState.update { it.copy(titulo = nuevo) }
    }

    fun actualizarContenido(nuevo: String) {
        _uiState.update { it.copy(contenido = nuevo) }
    }

    // =====================================================
    // 🔥 MÉTODO crearDiario() FINAL
    // =====================================================
    fun crearDiario(idUsuario: Int, tipo: TipoTarjeta) {
        viewModelScope.launch {

            _uiState.update { it.copy(cargando = true, error = null) }

            try {
                val estado = _uiState.value

                // 1️⃣ Buscar si el usuario ya tiene una tarjeta de ese tipo
                val tarjetasUsuario = tarjetaRepo.obtenerTarjetasPorUsuario(idUsuario)
                var tarjeta = tarjetasUsuario.find { it.tipo == tipo }

                // 2️⃣ Si NO existe → crearla usando insertar() directamente
                if (tarjeta == null) {

                    val nuevaTarjeta = Tarjeta(
                        titulo = when (tipo) {
                            TipoTarjeta.PERSONAL -> "Notas Personales"
                            TipoTarjeta.RESETAS -> "Mis Recetas"
                            TipoTarjeta.ACTIVIDADES -> "Mis Actividades"
                        },
                        tipo = tipo,
                        idUsua = idUsuario
                    )

                    // Insertar tarjeta y obtener ID generado
                    val idGenerado = tarjetaRepo.insertarTarjeta(nuevaTarjeta).toInt()

                    // Volver a obtener la tarjeta creada
                    tarjeta = tarjetaRepo.obtenerTarjetaPorId(idGenerado)
                }

                // 3️⃣ Crear el diario ligado a dicha tarjeta
                repo.insertarDiarioTexto(
                    DiarioTexto(
                        titulo = estado.titulo,
                        texto = estado.contenido,
                        idTarjeta = tarjeta!!.idTarjeta
                    )
                )

                limpiarCampos()

                _uiState.update { it.copy(cargando = false, guardado = true) }

            } catch (e: Exception) {
                _uiState.update { it.copy(cargando = false, error = "Error al guardar diario: ${e.message}") }
            }
        }
    }


    // =====================================================
    // MÉTODO GUARDAR ORIGINAL (SOLO PARA EDICIÓN)
    // =====================================================
    fun guardar(idTarjetaNav: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true, error = null) }

            val estado = _uiState.value

            try {
                if (estado.modoEdicion && estado.idDiarioTexto != null) {
                    repo.actualizarDiarioTexto(
                        DiarioTexto(
                            idDiarioTexto = estado.idDiarioTexto,
                            titulo = estado.titulo,
                            texto = estado.contenido,
                            idTarjeta = estado.idTarjeta ?: idTarjetaNav
                        )
                    )
                } else {
                    repo.insertarDiarioTexto(
                        DiarioTexto(
                            titulo = estado.titulo,
                            texto = estado.contenido,
                            idTarjeta = idTarjetaNav
                        )
                    )
                    limpiarCampos()
                }

                _uiState.update { it.copy(cargando = false, guardado = true) }

            } catch (e: Exception) {
                _uiState.update { it.copy(cargando = false, error = "Error al guardar: ${e.message}") }
            }
        }
    }
}

appdirio/ui/screens/cliente/diariotexto/diariotextoscreen
ListaDiariosScreen.kt
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
<img width="271" height="603" alt="image" src="https://github.com/user-attachments/assets/433ce932-8476-4e5f-a38e-fdda24629049" />

ListaDiariosViewModel.kt
package mx.edu.utng.appdiario.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.repository.DiarioTextoRepository
import mx.edu.utng.appdiario.repository.TarjetaRepository
import mx.edu.utng.appdiario.local.entity.Diario.DiarioTexto
import mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta
import mx.edu.utng.appdiario.ui.screens.auth.loginusuario.SessionManager

class ListaDiariosViewModel(
    private val tarjetaRepository: TarjetaRepository,
    private val diarioTextoRepository: DiarioTextoRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _tarjetas = MutableStateFlow<List<Tarjeta>>(emptyList())
    val tarjetas: StateFlow<List<Tarjeta>> = _tarjetas.asStateFlow()

    private val _diariosPorTarjeta = MutableStateFlow<Map<Int, List<DiarioTexto>>>(emptyMap())
    val diariosPorTarjeta: StateFlow<Map<Int, List<DiarioTexto>>> = _diariosPorTarjeta.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _userId = MutableStateFlow<Int?>(null)
    val userId: StateFlow<Int?> = _userId.asStateFlow()

    init {
        println("🔄 [ViewModel] INIT - Inicializando ListaDiariosViewModel")
        println("🔄 [ViewModel] Repositories inyectados:")
        println("   - TarjetaRepository: ${tarjetaRepository::class.simpleName}")
        println("   - DiarioTextoRepository: ${diarioTextoRepository::class.simpleName}")
        println("   - SessionManager: ${sessionManager::class.simpleName}")

        // Observar el ID del usuario cuando se inicializa el ViewModel
        viewModelScope.launch {
            println("👀 [ViewModel] Iniciando observación del userIdFlow...")
            sessionManager.userIdFlow.collect { userId ->
                println("📥 [ViewModel] userIdFlow emitido: $userId")
                _userId.value = userId
                println("✅ [ViewModel] _userId actualizado a: $userId")

                // Si hay usuario, cargar datos automáticamente
                if (userId != null) {
                    println("🚀 [ViewModel] Usuario autenticado detectado, cargando datos iniciales...")
                    // Probar con diferentes tipos para debug
                    cargarDiariosPorTipo("RESETAS") // Usar el tipo exacto de la BD
                } else {
                    println("⚠️ [ViewModel] userId es null - Usuario no autenticado")
                }
            }
        }
    }

    fun cargarDiariosPorTipo(tipo: String) {
        println("\n📋 [ViewModel] cargarDiariosPorTipo() llamado")
        println("   📝 Parámetros: tipo='$tipo'") // CORREGIDO: usar 'tipo' no 'type'

        val currentUserId = _userId.value
        println("   👤 Estado actual - userId: $currentUserId")

        if (currentUserId == null) {
            println("❌ [ViewModel] ERROR: Usuario no autenticado - No se pueden cargar diarios")
            _error.value = "Usuario no autenticado"
            _isLoading.value = false
            return
        }

        println("✅ [ViewModel] Usuario autenticado, procediendo a cargar datos...")
        _isLoading.value = true
        _error.value = null
        println("   🎯 Estado - isLoading: ${_isLoading.value}, error: ${_error.value}")

        viewModelScope.launch {
            try {
                println("\n🔍 [ViewModel] Iniciando carga de datos...")
                println("   👤 Usuario ID: $currentUserId")
                println("   🏷️  Tipo solicitado: '$tipo'")

                // DEBUG: Primero obtener TODAS las tarjetas para ver qué hay
                println("   🔎 [DEBUG] Obteniendo TODAS las tarjetas del usuario...")
                val todasLasTarjetas = tarjetaRepository.obtenerTarjetasPorUsuario(currentUserId)
                println("   🔎 [DEBUG] Total tarjetas del usuario: ${todasLasTarjetas.size}")
                todasLasTarjetas.forEachIndexed { index, tarjeta ->
                    println("      📄 Tarjeta $index:")
                    println("         ID: ${tarjeta.idTarjeta}")
                    println("         Título: '${tarjeta.titulo}'")
                    println("         Tipo: '${tarjeta.tipo}'")
                    println("         Usuario ID: ${tarjeta.idUsua}") // CORREGIDO: usar usuarioId
                }

                // 1. Obtener tarjetas por tipo y usuario
                println("   📦 [Paso 1] Obteniendo tarjetas por tipo y usuario...")
                val tarjetasObtenidas = tarjetaRepository.obtenerTarjetasPorTipoYUsuario(tipo, currentUserId)
                println("   ✅ [Paso 1] Tarjetas obtenidas con filtro: ${tarjetasObtenidas.size}")

                // Debug detallado de cada tarjeta obtenida
                tarjetasObtenidas.forEachIndexed { index, tarjeta ->
                    println("      📄 Tarjeta filtrada $index:")
                    println("         ID: ${tarjeta.idTarjeta}")
                    println("         Título: '${tarjeta.titulo}'")
                    println("         Tipo: '${tarjeta.tipo}'")
                    println("         Usuario ID: ${tarjeta.idUsua}")
                }

                // Actualizar estado de tarjetas
                println("   📤 [Paso 2] Actualizando _tarjetas StateFlow...")
                _tarjetas.value = tarjetasObtenidas
                println("   ✅ [Paso 2] _tarjetas actualizado con ${_tarjetas.value.size} elementos")

                // 2. Obtener diarios de texto para cada tarjeta
                println("   📦 [Paso 3] Obteniendo diarios para cada tarjeta...")
                val diariosMap = mutableMapOf<Int, List<DiarioTexto>>()

                if (tarjetasObtenidas.isEmpty()) {
                    println("   ℹ️  [Paso 3] No hay tarjetas, saltando obtención de diarios")
                } else {
                    tarjetasObtenidas.forEach { tarjeta ->
                        println("      🔍 Obteniendo diarios para tarjeta ID: ${tarjeta.idTarjeta}")
                        val diarios = diarioTextoRepository.obtenerDiariosTextoPorTarjeta(tarjeta.idTarjeta)
                        println("      ✅ Diarios obtenidos para tarjeta ${tarjeta.idTarjeta}: ${diarios.size}")

                        // Debug detallado de cada diario
                        diarios.forEachIndexed { diarioIndex, diario ->
                            println("         📝 Diario $diarioIndex:")
                            println("            ID: ${diario.idDiarioTexto}")
                            println("            Tarjeta ID: ${diario.idTarjeta}") // CORREGIDO: usar idTarjeta
                            println("            Título: '${diario.titulo}'")
                            println("            Texto: '${diario.texto?.take(50)}...'")
                            println("            Fecha: ${diario.fechaCreacion}")
                        }

                        diariosMap[tarjeta.idTarjeta] = diarios
                    }
                }

                // Actualizar estado de diarios
                println("   📤 [Paso 4] Actualizando _diariosPorTarjeta StateFlow...")
                _diariosPorTarjeta.value = diariosMap
                println("   ✅ [Paso 4] _diariosPorTarjeta actualizado con ${_diariosPorTarjeta.value.size} entradas")

                // Resumen final
                println("\n🎉 [ViewModel] CARGA COMPLETADA EXITOSAMENTE")
                println("   📊 Resumen:")
                println("      • Tarjetas cargadas: ${_tarjetas.value.size}")
                println("      • Mapeo diarios/tarjeta: ${_diariosPorTarjeta.value.size}")
                println("      • Total diarios: ${_diariosPorTarjeta.value.values.flatten().size}")

            } catch (e: Exception) {
                println("\n💥 [ViewModel] ERROR durante la carga de datos")
                println("   🚨 Excepción: ${e.javaClass.simpleName}")
                println("   📄 Mensaje: ${e.message}")
                println("   📍 StackTrace:")
                e.printStackTrace()

                _error.value = "Error al cargar los diarios: ${e.message}"
                println("   ❗ Error guardado en StateFlow: ${_error.value}")

            } finally {
                _isLoading.value = false
                println("   🏁 Estado final - isLoading: ${_isLoading.value}")
            }
        }
    }

    // En tu ListaDiariosViewModel, agrega esta función:
    fun eliminarDiario(diario: DiarioTexto) {
        println("🗑️ [ViewModel] Eliminando diario: ${diario.idDiarioTexto} - '${diario.titulo}'")

        viewModelScope.launch {
            try {
                diarioTextoRepository.eliminarDiarioTexto(diario)
                println("✅ [ViewModel] Diario eliminado exitosamente")

                // ACTUALIZACIÓN INMEDIATA: Remover el diario eliminado de los StateFlows
                val diariosMapActualizado = _diariosPorTarjeta.value.toMutableMap()

                // Buscar en todas las tarjetas y eliminar el diario
                diariosMapActualizado.forEach { (tarjetaId, diarios) ->
                    val diariosFiltrados = diarios.filter { it.idDiarioTexto != diario.idDiarioTexto }
                    diariosMapActualizado[tarjetaId] = diariosFiltrados
                }

                // Actualizar el StateFlow - esto hará que la UI se actualice automáticamente
                _diariosPorTarjeta.value = diariosMapActualizado

                println("🔄 [ViewModel] StateFlows actualizados después de eliminar")

            } catch (e: Exception) {
                println("❌ [ViewModel] Error al eliminar diario: ${e.message}")
                _error.value = "Error al eliminar la nota: ${e.message}"
            }
        }
    }

    // Método para cargar todos los tipos disponibles (para debug)
    fun debugTiposDisponibles() {
        val currentUserId = _userId.value ?: return

        viewModelScope.launch {
            println("\n🔍 [DEBUG] Tipos de tarjetas disponibles para usuario $currentUserId:")
            val todasTarjetas = tarjetaRepository.obtenerTarjetasPorUsuario(currentUserId)
            val tiposUnicos = todasTarjetas.map { it.tipo.name }.distinct()
            tiposUnicos.forEach { tipo ->
                println("   🏷️  Tipo: '$tipo'")
            }
        }
    }

    // ... (los demás métodos se mantienen igual)
    fun limpiarError() {
        println("\n🧹 [ViewModel] limpiarError() llamado")
        println("   📝 Estado anterior - error: ${_error.value}")
        _error.value = null
        println("   ✅ Estado actual - error: ${_error.value}")
    }

    fun obtenerPrimerDiario(tarjetaId: Int): DiarioTexto? {
        println("\n🔍 [ViewModel] obtenerPrimerDiario() llamado")
        println("   📝 Parámetros: tarjetaId=$tarjetaId")

        val diarios = _diariosPorTarjeta.value[tarjetaId]
        println("   📦 Diarios encontrados para tarjeta $tarjetaId: ${diarios?.size ?: 0}")

        val primerDiario = diarios?.firstOrNull()
        println("   ✅ Primer diario: ${primerDiario?.let { "ID: ${it.idDiarioTexto}, Título: ${it.titulo}" } ?: "NULO"}")

        return primerDiario
    }

    fun obtenerTodosDiarios(tarjetaId: Int): List<DiarioTexto> {
        println("\n🔍 [ViewModel] obtenerTodosDiarios() llamado")
        println("   📝 Parámetros: tarjetaId=$tarjetaId")

        val diarios = _diariosPorTarjeta.value[tarjetaId] ?: emptyList()
        println("   ✅ Diarios obtenidos: ${diarios.size}")

        diarios.forEachIndexed { index, diario ->
            println("      📄 Diario $index: ID=${diario.idDiarioTexto}, Título=${diario.titulo}")
        }

        return diarios
    }

    fun isUserAuthenticated(): Boolean {
        val isAuthenticated = _userId.value != null
        println("\n🔐 [ViewModel] isUserAuthenticated() = $isAuthenticated (userId: ${_userId.value})")
        return isAuthenticated
    }

    fun debugEstado() {
        println("\n📊 [ViewModel] DEBUG ESTADO ACTUAL")
        println("   👤 userId: ${_userId.value}")
        println("   📋 tarjetas: ${_tarjetas.value.size} elementos")
        println("   📝 diariosPorTarjeta: ${_diariosPorTarjeta.value.size} mapeos")
        println("   ⏳ isLoading: ${_isLoading.value}")
        println("   ❗ error: ${_error.value}")

        _tarjetas.value.forEachIndexed { index, tarjeta ->
            println("      📄 Tarjeta $index: ID=${tarjeta.idTarjeta}, Título='${tarjeta.titulo}'")
            val diarios = _diariosPorTarjeta.value[tarjeta.idTarjeta] ?: emptyList()
            println("         📝 Diarios asociados: ${diarios.size}")
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("\n🗑️ [ViewModel] onCleared() - ListaDiariosViewModel destruido")
    }
}


appdirio/ui/screens/cliente/perfilusuario
PerfilUsuario.kt
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
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.local.database.AppDatabase
import mx.edu.utng.appdiario.ui.screens.auth.loginusuario.SessionManager

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
<img width="316" height="702" alt="image" src="https://github.com/user-attachments/assets/7fa104e6-8960-4e5e-9023-dfdca541d558" />

PerfilUsuarioViewModel.kt
package mx.edu.utng.appdiario.ui.screens.cliente.perfilUsuario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.local.entity.Ususario.Usuario
import mx.edu.utng.appdiario.ui.screens.auth.loginusuario.SessionManager

class PerfilUsuarioViewModel(
    private val usuarioRepository: UsuarioRepository,
    private val sessionManager: SessionManager // ✅ Recibe por constructor
) : ViewModel() {

    private val _usuario = MutableStateFlow<Usuario?>(null)
    val usuario = _usuario.asStateFlow()

    private val _isEditing = MutableStateFlow(false)
    val isEditing = _isEditing.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        cargarUsuarioActual() // ✅ Carga automáticamente al crearse
    }

    fun cargarUsuarioActual() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                // Obtener el ID del usuario desde SessionManager
                val userId = sessionManager.userIdFlow.first()
                println("DEBUG: UserId obtenido en perfil: $userId")

                if (userId != null) {
                    // Obtener usuario real del repositorio
                    val usuarioEncontrado = usuarioRepository.obtenerUsuarioPorId(userId)
                    println("DEBUG: Usuario encontrado: $usuarioEncontrado")

                    if (usuarioEncontrado != null) {
                        _usuario.value = usuarioEncontrado
                    } else {
                        _errorMessage.value = "Usuario no encontrado en la base de datos"
                    }
                } else {
                    _errorMessage.value = "No hay usuario logueado. Por favor inicie sesión."
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar usuario: ${e.message}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun actualizarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                usuarioRepository.actualizarUsuario(usuario)
                _usuario.value = usuario
                _isEditing.value = false
            } catch (e: Exception) {
                _errorMessage.value = "Error al actualizar usuario: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun setEditing(editing: Boolean) {
        _isEditing.value = editing
    }

    fun clearError() {
        _errorMessage.value = null
    }

    fun cerrarSesion() {
        viewModelScope.launch {
            println("DEBUG: Cerrando sesión")
            sessionManager.clearUserSession()
        }
    }
}

appdiario/api
                GmailApiHelper.kt
package mx.edu.utng.appdiario.ui.screens.auth

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Properties
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class AutoEmailSender(private val context: Context) {

    companion object {
        private const val TAG = "AutoEmailSender"
        // 🔹 CONFIGURA ESTOS DATOS CON TU EMAIL GMAIL
        private const val SMTP_HOST = "smtp.gmail.com"
        private const val SMTP_PORT = "587"
        private const val FROM_EMAIL = "androoz706@gmail.com" // 🔹 REEMPLAZA CON TU EMAIL
        private const val FROM_PASSWORD = "nafi qwvi uuen hygo" // 🔹 CONTRASEÑA DE APLICACIÓN
    }

    //  Obtener la cuenta actualmente autenticada (mantenido por compatibilidad)
    fun getLastSignedInAccount(): Nothing? {
        return null // Ya no usamos Google Sign-In
    }

    // Obtener intent para iniciar sesión (mantenido por compatibilidad)
    fun getSignInIntent(): Nothing? {
        return null // Ya no usamos Google Sign-In
    }

    // Cerrar sesión (mantenido por compatibilidad)
    suspend fun signOut() {
        // No necesita implementación para SMTP
    }


    suspend fun sendPasswordRecoveryEmail(
        toEmail: String,
        recoveryCode: String
    ): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                Log.d(TAG, "🔹 Iniciando envío automático a: $toEmail")

                val props = Properties().apply {
                    put("mail.smtp.host", SMTP_HOST)
                    put("mail.smtp.port", SMTP_PORT)
                    put("mail.smtp.auth", "true")
                    put("mail.smtp.starttls.enable", "true")
                    put("mail.smtp.ssl.trust", SMTP_HOST)
                }

                val session = Session.getInstance(props, object : Authenticator() {
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD)
                    }
                })

                val message = MimeMessage(session).apply {
                    setFrom(InternetAddress(FROM_EMAIL, "App Diario"))
                    setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail))
                    subject = "Código de Recuperación - App Diario"
                    setText(createEmailBody(recoveryCode), "utf-8", "html")
                }

                Transport.send(message)
                Log.d(TAG, "Email AUTOMÁTICO enviado exitosamente a: $toEmail")
                true

            } catch (e: AuthenticationFailedException) {
                Log.e(TAG, "Error de autenticación SMTP: ${e.message}")
                Log.e(TAG, "Verifica FROM_EMAIL y FROM_PASSWORD en AutoEmailSender")
                false
            } catch (e: MessagingException) {
                Log.e(TAG, "Error de mensajería: ${e.message}")
                false
            } catch (e: Exception) {
                Log.e(TAG, "Error general enviando email: ${e.message}")
                e.printStackTrace()
                false
            }
        }
    }

    private fun createEmailBody(recoveryCode: String): String {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <style>
                    body { 
                        font-family: 'Arial', sans-serif; 
                        background-color: #f5f5f5; 
                        margin: 0;
                        padding: 20px;
                        color: #333;
                    }
                    .container { 
                        max-width: 600px;
                        margin: 0 auto;
                        background: white; 
                        padding: 30px;
                        border-radius: 15px;
                        box-shadow: 0 4px 6px rgba(0,0,0,0.1);
                        border: 2px solid #6d3b1a;
                    }
                    .header {
                        text-align: center;
                        background: linear-gradient(135deg, #6d3b1a, #8B5A2B);
                        color: white;
                        padding: 20px;
                        border-radius: 10px 10px 0 0;
                        margin: -30px -30px 20px -30px;
                    }
                    .code-container {
                        background: #f5e6d3;
                        border: 2px dashed #6d3b1a;
                        border-radius: 10px;
                        padding: 20px;
                        margin: 20px 0;
                        text-align: center;
                    }
                    .code { 
                        font-size: 32px; 
                        font-weight: bold; 
                        color: #6d3b1a; 
                        letter-spacing: 5px;
                        font-family: 'Courier New', monospace;
                    }
                    .footer {
                        margin-top: 30px;
                        padding-top: 20px;
                        border-top: 1px solid #ddd;
                        text-align: center;
                        color: #666;
                        font-size: 12px;
                    }
                    .warning {
                        background: #fff3cd;
                        border: 1px solid #ffeaa7;
                        border-radius: 5px;
                        padding: 10px;
                        margin: 15px 0;
                        color: #856404;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>App Diario</h1>
                        <h2>Recuperación de Contraseña</h2>
                    </div>
                    
                    <p>Hola,</p>
                    
                    <p>Has solicitado recuperar tu contraseña en <strong>App Diario</strong>.</p>
                    
                    <p>Tu código de verificación es:</p>
                    
                    <div class="code-container">
                        <div class="code">$recoveryCode</div>
                    </div>
                    
                    <p>Ingresa este código de 6 dígitos en la aplicación para continuar con el proceso de recuperación.</p>
                    
                    <div class="warning">
                           <strong>Importante:</strong> 
                        <ul>
                            <li>El código expirará en 15 minutos</li>
                            <li>No compartas este código con nadie</li>
                            <li>Si no reconoces esta solicitud, ignora este email</li>
                        </ul>
                    </div>
                    
                    <p>Si tienes problemas con el código, puedes solicitar uno nuevo desde la aplicación.</p>
                    
                    <div class="footer">
                        <p>Saludos,<br>
                        <strong>El equipo de App Diario</strong></p>
                        <p>Este email fue enviado automáticamente, por favor no respondas</p>
                    </div>
                </div>
            </body>
            </html>
        """.trimIndent()
    }

    // Métodos de compatibilidad (pueden llamarse pero no hacen nada)
    fun getGmailService(account: Nothing?): Nothing? = null

    private fun String.toBase64Url(): String {
        return android.util.Base64.encodeToString(
            this.toByteArray(Charsets.UTF_8),
            android.util.Base64.URL_SAFE or android.util.Base64.NO_WRAP
        )
    }
}
<img width="243" height="362" alt="image" src="https://github.com/user-attachments/assets/05af94ab-705b-4f15-a859-b807e8ef86cd" />
	client_secret_1095786928176-6kk0curmk3jr41du89oekd1b265oq8oe.apps.googleusercontent.com.json
//jason para agregar el servicio de Gmail 	
{"installed":{"client_id":"1095786928176-6kk0curmk3jr41du89oekd1b265oq8oe.apps.googleusercontent.com","project_id":"diarioapp-478712","auth_uri":"https://accounts.google.com/o/oauth2/auth","token_uri":"https://oauth2.googleapis.com/token","auth_provider_x509_cert_url":"https://www.googleapis.com/oauth2/v1/certs"}}

appdiario/local
	appdiario/local/dao
DiarioAudioDao.kt
package mx.edu.utng.appdiario.local.dao

import androidx.room.*
import mx.edu.utng.appdiario.local.entity.Diario.DiarioAudio

@Dao
interface DiarioAudioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(diarioAudio: DiarioAudio)

    @Update
    suspend fun actualizar(diarioAudio: DiarioAudio)

    @Delete
    suspend fun eliminar(diarioAudio: DiarioAudio)

    @Query("SELECT * FROM diario_audio")
    suspend fun obtenerTodos(): List<DiarioAudio>

    @Query("SELECT * FROM diario_audio WHERE idTarjeta = :tarjetaId")
    suspend fun obtenerPorTarjeta(tarjetaId: Int): List<DiarioAudio>
}

DiarioTexto.kt
package mx.edu.utng.appdiario.local.dao

import androidx.room.*
import mx.edu.utng.appdiario.local.entity.Diario.DiarioTexto

@Dao
interface DiarioTextoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(diarioTexto: DiarioTexto)

    @Update
    suspend fun actualizar(diarioTexto: DiarioTexto)

    @Delete
    suspend fun eliminar(diarioTexto: DiarioTexto)

    @Query("SELECT * FROM diario_texto")
    suspend fun obtenerTodos(): List<DiarioTexto>

    @Query("SELECT * FROM diario_texto WHERE idDiarioTexto = :id LIMIT 1")
    suspend fun obtenerPorId(id: Int): DiarioTexto? //////

    @Query("SELECT * FROM diario_texto WHERE idTarjeta = :tarjetaId")
    suspend fun obtenerPorTarjeta(tarjetaId: Int): List<DiarioTexto>/////

}

TarjetaDao.kt
package mx.edu.utng.appdiario.local.dao

import androidx.room.*
import mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta

@Dao
interface TarjetaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(tarjeta: Tarjeta): Long

    @Update
    suspend fun actualizar(tarjeta: Tarjeta)

    @Delete
    suspend fun eliminar(tarjeta: Tarjeta)

    @Query("SELECT * FROM tarjeta")
    suspend fun obtenerTodas(): List<Tarjeta>

    @Query("SELECT * FROM tarjeta WHERE idUsua = :usuarioId")
    suspend fun obtenerPorUsuario(usuarioId: Int): List<Tarjeta>

    @Query("SELECT * FROM tarjeta WHERE tipo = :tipo AND idUsua = :usuarioId")
    suspend fun obtenerPorTipoYUsuario(tipo: String, usuarioId: Int): List<Tarjeta>

}

UsuarioDao.kt
package mx.edu.utng.appdiario.local.dao

import androidx.room.*
import mx.edu.utng.appdiario.local.entity.Ususario.Usuario

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(usuario: Usuario)

    @Update
    suspend fun actualizar(usuario: Usuario)

    @Delete
    suspend fun eliminar(usuario: Usuario)

    @Query("SELECT * FROM usuario")
    suspend fun obtenerTodos(): List<Usuario>

    @Query("SELECT * FROM usuario WHERE email = :email AND password = :password LIMIT 1")
    suspend fun login(email: String, password: String): Usuario?
}




appdiario/local/database
	AppDatabase.kt
package mx.edu.utng.appdiario.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.local.dao.*
import mx.edu.utng.appdiario.local.entity.Diario.DiarioAudio
import mx.edu.utng.appdiario.local.entity.Diario.DiarioTexto
import mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta
import mx.edu.utng.appdiario.local.entity.Tarjeta.TipoTarjetaConverter
import mx.edu.utng.appdiario.local.entity.Ususario.TipoUsuarioConverter
import mx.edu.utng.appdiario.local.entity.Ususario.Usuario
import mx.edu.utng.appdiario.ui.screens.administrador.AdminInitializer

@Database(
    entities = [
        Usuario::class,
        Tarjeta::class,
        DiarioTexto::class,
        DiarioAudio::class
    ],
    version = 7, // 🔹 Incrementé la versión por los cambios
    exportSchema = false
)
@TypeConverters(
    TipoUsuarioConverter::class,
    TipoTarjetaConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun tarjetaDao(): TarjetaDao
    abstract fun diarioTextoDao(): DiarioTextoDao
    abstract fun diarioAudioDao(): DiarioAudioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "DiarioDB"
                )
                    .fallbackToDestructiveMigration() // 🔹 Borra y recrea la DB si cambia la versión
                    .build()

                INSTANCE = instance

                // 🔹 INICIALIZAR ADMIN DESPUÉS DE CREAR LA BASE DE DATOS
                initializeAdmin(context, instance)

                instance
            }
        }

        private fun initializeAdmin(context: Context, database: AppDatabase) {
            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                try {
                    val usuarioRepository = UsuarioRepository(database.usuarioDao())
                    val adminInitializer = AdminInitializer(context, usuarioRepository)
                    adminInitializer.initializeAdminIfNeeded()
                } catch (e: Exception) {
                    println("❌ Error al inicializar admin en AppDatabase: ${e.message}")
                }
            }
        }
    }
}









appdiario/local/entity
	appdiario/local/entity/diario
DiarioAudio
package mx.edu.utng.appdiario.local.entity.Diario

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "diario_audio")
data class DiarioAudio(
    @PrimaryKey(autoGenerate = true)
    val idDiarioAudio: Int = 0,
    val titulo: String,
    val archivo: String, // ruta o nombre del archivo
    val audioDuracion: Int, // duración en segundos
    val fechaCreacion: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        .format(Date()),
    val idTarjeta: Int // llave foránea a Tarjeta
)

DiarioTexto
package mx.edu.utng.appdiario.local.entity.Diario

import androidx.room.Entity
import androidx.room.PrimaryKey
import mx.edu.utng.appdiario.local.entity.Tarjeta.TipoTarjeta
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "diario_texto")
data class DiarioTexto(
    @PrimaryKey(autoGenerate = true)
    val idDiarioTexto: Int = 0,
    val titulo: String,
    val texto: String,
    val fechaCreacion: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        .format(Date()),
    val idTarjeta: Int,
)

	appdiario/local/entity/tarjeta
TipoTarjeta.kt
package mx.edu.utng.appdiario.local.entity.Tarjeta

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class TipoTarjeta {
    PERSONAL,
    RESETAS,
    ACTIVIDADES
}

@Entity(tableName = "tarjeta")
data class Tarjeta(
    @PrimaryKey(autoGenerate = true)
    val idTarjeta: Int = 0,
    val titulo: String,
    val tipo: TipoTarjeta,
    val idUsua: Int // llave foránea a Usuario
)

TipoTarjetaConverter.kt
package mx.edu.utng.appdiario.local.entity.Tarjeta

import androidx.room.TypeConverter

class TipoTarjetaConverter {

    @TypeConverter
    fun fromTipoTarjeta(tipo: TipoTarjeta): String {
        return tipo.name
    }

    @TypeConverter
    fun toTipoTarjeta(value: String): TipoTarjeta {
        return TipoTarjeta.valueOf(value)
    }
}




	appdiario/local/usuario
TipoUsuario.kt
	package mx.edu.utng.appdiario.local.entity.Ususario

enum class TipoUsuario (){
    ADMIN,
    NORMAL
}

TipoUsuarioConverter.kt
package mx.edu.utng.appdiario.local.entity.Ususario

import androidx.room.TypeConverter

class TipoUsuarioConverter {

    @TypeConverter
    fun fromTipoUsuario(tipo: TipoUsuario): String {
        return tipo.name  // Guarda el enum como texto ("ADMIN" o "NORMAL")
    }

    @TypeConverter
    fun toTipoUsuario(value: String): TipoUsuario {
        return TipoUsuario.valueOf(value)  // Convierte el texto de nuevo al enum
    }
}

Usuario.kt
package mx.edu.utng.appdiario.local.entity.Ususario

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(
    tableName = "usuario",
    indices = [Index(value = ["email"], unique = true)] // 🔹 Hace único el email
)
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val idUsua: Int = 0,
    val nombre: String,
    val apellidoMa: String,
    val apellidoPa: String,
    val fechNaci: String,
    val email: String,
    val password: String,
    val fechaRegistro: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        .format(Date()),
    val tipo: TipoUsuario
)

appdiario/navigation
	appdiario/navigation/barranavegacionadmin
		Navegacion.kt
package mx.edu.utng.appdiario.navigation.barranavegacionadmin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mx.edu.utng.appdiario.ui.screens.administrador.gestionusuario.GestionUsuarios
import mx.edu.utng.appdiario.ui.screens.administrador.reportesparaadministrador.ReportesAdmin

// 🔹 Pantalla principal con barra inferior
@Composable
fun NavegacionAdmin(navController: NavHostController) {
    // NavController para la barra inferior
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(bottomNavController) }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = "home",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable("home") { HomeScreen(bottomNavController) }
            composable("usuarios") { UsuariosScreen(bottomNavController) }
            composable("reportes") { ReportesScreen(bottomNavController) }
        }
    }
}


// 🔹 Barra inferior
@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        NavItem("home", "🏠", "Home"),
        NavItem("usuarios", "👥", "Usuarios"),
        NavItem("reportes", "📊", "Reportes")
    )

    NavigationBar(containerColor = Color(0xFF6D3B1A)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Text(item.icon, fontSize = 22.sp) },
                label = { Text(item.label, color = Color.White) }
            )
        }
    }
}

// 🔹 Modelos y pantallas internas
data class NavItem(val route: String, val icon: String, val label: String)

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            navController.navigate("login") {
                popUpTo(0) { inclusive = true }
            }
        }) {
            Text("Cerrar sesión")
        }
    }
}

@Composable
fun UsuariosScreen(navController: NavController) {
    GestionUsuarios(navController = navController)
}

@Composable
fun ReportesScreen(navController: NavController) {
    ReportesAdmin(navController = navController)
}

	appdiario/navigation/barranavegacioncliente
NavegacionCliente.kt
package mx.edu.utng.appdiario.navigation.barranavegacioncliente

import DiarioTextoScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.local.database.AppDatabase
import mx.edu.utng.appdiario.ui.screens.auth.loginusuario.SessionManager
import mx.edu.utng.appdiario.ui.screens.cliente.diarioaudio.DiarioAudioScreen
import mx.edu.utng.appdiario.ui.screens.cliente.diariotexto.diariotextoscreen.DiarioTextoViewModel
import mx.edu.utng.appdiario.ui.screens.cliente.dashboardcliente.DashboardScreen
import mx.edu.utng.appdiario.ui.screens.cliente.diarioaudio.detallediarioaudioscreen.DetalleDiarioAudioScreen
import mx.edu.utng.appdiario.ui.screens.cliente.diarioaudio.listadiariosaudioscreen.ListaDiariosAudioScreen
import mx.edu.utng.appdiario.ui.screens.cliente.diariotexto.detallediarioscreen.DetalleDiarioScreen
import mx.edu.utng.appdiario.ui.screens.cliente.diariotexto.listadiarioscreen.ListaDiariosScreen
import mx.edu.utng.appdiario.ui.screens.cliente.perfilUsuario.PerfilUsuario

@Composable
fun NavegacionCliente(globalNavController: NavHostController) {

    val navController = rememberNavController()
    val showBottomBar = rememberSaveable { mutableStateOf(true) }
    val context = LocalContext.current

    // Obtener los DAOs
    val diarioTextoDao = AppDatabase.getDatabase(context).diarioTextoDao()
    val tarjetaDao = AppDatabase.getDatabase(context).tarjetaDao()
    val sessionManager = SessionManager(context)

    // Inicializar el ViewModel del diario texto
    val diarioTextoRepo = mx.edu.utng.appdiario.repository.DiarioTextoRepository(diarioTextoDao)
    val tarjetaRepo = mx.edu.utng.appdiario.repository.TarjetaRepository(tarjetaDao, context)
    val diarioTextoViewModel = remember { DiarioTextoViewModel(diarioTextoRepo, tarjetaRepo) }

    Scaffold(
        bottomBar = { if (showBottomBar.value) BottomNavBar(navController) }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {

            // ---------- HOME ----------
            composable("home") {
                showBottomBar.value = true
                DashboardScreen(
                    localNavController = navController,
                    globalNavController = globalNavController
                )
            }

            // ---------- PERFIL ----------
            composable("usuario") {
                showBottomBar.value = true
                val usuarioDao = AppDatabase.getDatabase(context).usuarioDao()
                val usuarioRepository = UsuarioRepository(usuarioDao)

                PerfilUsuario(
                    navController = navController,
                    showBottomBar = showBottomBar,
                    globalNavController = globalNavController
                )
            }

            // ---------- DIARIO TEXTO ----------

            // LISTA DIARIOS TEXTO - Pasar los DAOs como parámetros
            composable("lista_diarios") {
                showBottomBar.value = true
                ListaDiariosScreen(
                    navController = navController,
                    tarjetaDao = tarjetaDao,
                    diarioTextoDao = diarioTextoDao
                )
            }

            // DETALLE DIARIO TEXTO - Recibe idTarjeta como parámetro
            composable("detalle_diario/{diarioTextoId}") { backStackEntry ->
                showBottomBar.value = true
                val diarioTextoId = backStackEntry.arguments?.getString("diarioTextoId")?.toIntOrNull() ?: 0
                DetalleDiarioScreen(
                    navController = navController,
                    diarioTextoId = diarioTextoId
                )
            }

            // CREAR DIARIO TEXTO - Recibe idTarjeta
            composable("crear_diario_texto/{idTarjeta}") { backStackEntry ->
                showBottomBar.value = false
                val idTarjeta = backStackEntry.arguments?.getString("idTarjeta")?.toIntOrNull()
                DiarioTextoScreen(
                    navController = navController,
                    viewModel = diarioTextoViewModel,
                    sessionManager = sessionManager,
                    modoEdicion = false
                )
            }

            // EDITAR DIARIO TEXTO - Recibe idDiarioTexto
            composable("editar_diario_texto/{idDiarioTexto}") { backStackEntry ->
                showBottomBar.value = false
                val idDiarioTexto = backStackEntry.arguments?.getString("idDiarioTexto")?.toIntOrNull()
                DiarioTextoScreen(
                    navController = navController,
                    viewModel = diarioTextoViewModel,
                    sessionManager = sessionManager,
                    idDiarioTexto = idDiarioTexto,
                    modoEdicion = true
                )
            }

            // DIARIO TEXTO (pantalla principal sin parámetros)
            composable("diario_texto") {
                showBottomBar.value = true
                DiarioTextoScreen(
                    navController = navController,
                    viewModel = diarioTextoViewModel,
                    sessionManager = sessionManager
                )
            }

            // ---------- DIARIO AUDIO ----------
            composable("diario_audio") {
                showBottomBar.value = true
                DiarioAudioScreen(navController = navController)
            }

            // LISTA AUDIO
            composable("lista_diarios_audio") {
                showBottomBar.value = true
                ListaDiariosAudioScreen(navController = navController)
            }

            // DETALLE AUDIO
// En tu archivo de navegación (barra_navegacion_cliente)
            composable("detalle_diario_audio/{audioId}") { backStackEntry ->
                showBottomBar.value = true
                val audioId = backStackEntry.arguments?.getString("audioId")?.toIntOrNull() ?: 0
                DetalleDiarioAudioScreen(
                    navController = navController,
                    audioId = audioId
                )
            }

            // CREAR AUDIO
            composable("crear_diario_audio/{idTarjeta}") { backStackEntry ->
                showBottomBar.value = false
                val idTarjeta = backStackEntry.arguments?.getString("idTarjeta")?.toIntOrNull()
                DiarioAudioScreen(
                    navController = navController,
                    //idTarjeta = idTarjeta,
                    //modoEdicion = false
                )
            }

            // EDITAR AUDIO
            composable("editar_diario_audio/{idDiarioAudio}") { backStackEntry ->
                showBottomBar.value = false
                val idDiarioAudio = backStackEntry.arguments?.getString("idDiarioAudio")?.toIntOrNull()
                DiarioAudioScreen(
                    navController = navController,
                    //idDiarioAudio = idDiarioAudio,
                    //modoEdicion = true
                )
            }
        }
    }
}

@Composable
fun BottomNavBar(navController: NavHostController) {

    val items = listOf(
        NavItem("home", Icons.Default.Home, "Inicio"),
        NavItem("usuario", Icons.Default.Person, "Usuario")
    )

    NavigationBar(containerColor = Color(0xFF6D3B1A)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(0)
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = Color.White
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.White,
                    indicatorColor = Color(0xFF8D4E25)
                )
            )
        }
    }
}

data class NavItem(val route: String, val icon: ImageVector, val label: String)

	appdiario/navigation/navegacionglobal
NavGraph.kt
package mx.edu.utng.appdiario.navigation.navegacionglobal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.local.database.AppDatabase
import mx.edu.utng.appdiario.navigation.barranavegacionadmin.NavegacionAdmin
import mx.edu.utng.appdiario.navigation.barranavegacioncliente.NavegacionCliente
import mx.edu.utng.appdiario.ui.screens.auth.loginusuario.LoginScreen
import mx.edu.utng.appdiario.ui.screens.administrador.dashboardadministrador.AdminHome
import mx.edu.utng.appdiario.ui.screens.auth.registrousuario.Registro
import mx.edu.utng.appdiario.ui.screens.administrador.gestionusuario.GestionUsuarios
import mx.edu.utng.appdiario.ui.screens.administrador.reportesparaadministrador.ReportesAdmin
import mx.edu.utng.appdiario.ui.screens.auth.PasswordRecoveryScreen
import mx.edu.utng.appdiario.ui.screens.auth.CodeVerificationScreen
import mx.edu.utng.appdiario.ui.screens.auth.recuperacioncontrasena.ResetPasswordScreen

object NavRoutes {
    // AUTH ##################################
    const val LOGIN = "login"
    const val REGISTRO = "registro"
    const val PASSWORD_RECOVERY = "password_recovery"
    const val CODE_VERIFICATION = "code_verification"
    const val RESET_PASSWORD = "reset_password"

    // ADMIN ################################
    const val ADMIN_HOME = "adminHome"
    const val USUARIOS = "usuarios"
    const val REPORTES = "reportes"
    const val DASHBOARD_ADMIN = "dashboard_admin"

    // CLIENTE ##########################
    const val HOME_NORMAL = "homeNormal"
    const val BARRA_CLIENTE = "barraCliente"

    // NUEVAS PANTALLAS PARA DIARIOS
    const val DIARIO_TEXTO = "diario_texto"
    const val DIARIO_AUDIO = "diario_audio"
}

@Composable
fun NavegacionApp(navController: NavHostController) {

    // 🔹 SOLO PARA LAS PANTALLAS DE RECUPERACIÓN
    val context = LocalContext.current
    val usuarioRepository = remember {
        UsuarioRepository(AppDatabase.getDatabase(context).usuarioDao())
    }

    // ✅ ELIMINADO: GmailHelper - Ya no se necesita

    NavHost(
        navController = navController,
        startDestination = NavRoutes.LOGIN
    ) {
        // ==================== AUTH SCREENS ====================
        // Pantalla de login
        composable(NavRoutes.LOGIN) {
            LoginScreen(navController = navController)
        }

        // Registro de usuario
        composable(NavRoutes.REGISTRO) {
            Registro(navController = navController)
        }

        // ✅ CORREGIDO: PasswordRecoveryScreen ya no necesita gmailHelper
        composable(NavRoutes.PASSWORD_RECOVERY) {
            PasswordRecoveryScreen(
                navController = navController,
                usuarioRepository = usuarioRepository
                // ✅ ELIMINADO: gmailHelper parameter
            )
        }

        // ✅ CodeVerificationScreen NO necesita usuarioRepository - lo crea internamente
        composable(
            route = "${NavRoutes.CODE_VERIFICATION}/{email}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            CodeVerificationScreen(
                navController = navController,
                email = email
            )
        }

        // ✅ CORREGIDO: ResetPasswordScreen ya no necesita usuarioRepository
        composable(
            route = "${NavRoutes.RESET_PASSWORD}/{email}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            ResetPasswordScreen(
                navController = navController,
                email = email
                // ✅ ELIMINADO: usuarioRepository parameter
            )
        }

        // ==================== ADMIN SCREENS ====================
        // Dashboard del admin con barra inferior
        composable(NavRoutes.DASHBOARD_ADMIN) {
            NavegacionAdmin(navController = navController)
        }

        // Pantalla de administración específica
        composable(NavRoutes.ADMIN_HOME) {
            AdminHome(navController = navController)
        }

        // Pantalla Usuarios
        composable(NavRoutes.USUARIOS) {
            GestionUsuarios(navController = navController)
        }

        // Pantalla Reportes
        composable(NavRoutes.REPORTES) {
            ReportesAdmin(navController = navController)
        }

        // ==================== CLIENTE SCREENS ====================
        // Pantalla casa Cliente (redirige a la barra de navegación)
        composable(NavRoutes.HOME_NORMAL) {
            navController.navigate(NavRoutes.BARRA_CLIENTE) {
                popUpTo(NavRoutes.BARRA_CLIENTE) { inclusive = true }
            }
        }

        // Navegación principal del cliente con barra inferior
        composable(NavRoutes.BARRA_CLIENTE) {
            NavegacionCliente(globalNavController = navController)
        }

        // ==================== DIARIOS SCREENS ====================
        composable(NavRoutes.DIARIO_TEXTO) {
            // Redirige a la barra del cliente donde está el flujo de diarios
            navController.navigate(NavRoutes.BARRA_CLIENTE)
        }

        composable(NavRoutes.DIARIO_AUDIO) {
            // Redirige a la barra del cliente donde está el flujo de diarios
            navController.navigate(NavRoutes.BARRA_CLIENTE)
        }
    }
}

appdiario/repository
AudioManager.kt
package mx.edu.utng.appdiario.audio

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.util.Log
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class AudioManager(private val context: Context) {
    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private var audioFile: File? = null
    private var isRecording = false
    private var isPlaying = false

    fun startRecording(): String? {
        return try {
            // Crear directorio interno seguro
            val storageDir = File(context.cacheDir, "audio_records")
            storageDir.mkdirs()

            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            audioFile = File.createTempFile("AUDIO_${timeStamp}_", ".m4a", storageDir)

            mediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                MediaRecorder(context)
            } else {
                @Suppress("DEPRECATION")
                MediaRecorder()
            }

            mediaRecorder?.apply {
                try {
                    setAudioSource(MediaRecorder.AudioSource.MIC)
                    setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                    setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                    setOutputFile(audioFile?.absolutePath)
                    setAudioSamplingRate(44100)
                    setAudioEncodingBitRate(128000)
                    setAudioChannels(1)

                    prepare()
                    start()
                    isRecording = true
                    Log.d("AudioManager", "✅ Grabación iniciada. Archivo: ${audioFile?.absolutePath}")
                    return audioFile?.absolutePath
                } catch (e: IOException) {
                    Log.e("AudioManager", "❌ Error al preparar o iniciar grabación: ${e.message}")
                    e.printStackTrace()
                    release()
                    mediaRecorder = null
                    return null
                }
            }
            null
        } catch (e: Exception) {
            Log.e("AudioManager", "❌ Error general en startRecording: ${e.message}")
            e.printStackTrace()
            null
        }
    }

    fun stopRecording() {
        try {
            if (isRecording && mediaRecorder != null) {
                mediaRecorder?.apply {
                    try {
                        stop()
                    } catch (e: RuntimeException) {
                        Log.e("AudioManager", "❌ Error al detener (grabación muy corta): ${e.message}")
                        audioFile?.delete()
                        audioFile = null
                    }
                    release()
                }
                mediaRecorder = null
                isRecording = false

                val fileExists = audioFile?.exists() ?: false
                val fileSize = audioFile?.length() ?: 0
                Log.d("AudioManager", "⏹️ Grabación detenida. Existe: $fileExists, tamaño: $fileSize bytes")

                if (fileSize < 1000) {
                    Log.w("AudioManager", "⚠️ Archivo muy pequeño, posible grabación corrupta")
                }
            }
        } catch (e: Exception) {
            Log.e("AudioManager", "❌ Error al detener grabación: ${e.message}")
            e.printStackTrace()
        }
    }

    // MÉTODO ORIGINAL - reproduce solo el archivo grabado
    fun startPlaying(onCompletion: () -> Unit = {}) {
        audioFile?.let { file ->
            try {
                stopPlaying()

                Log.d("AudioManager", "🔍 Verificando archivo: ${file.absolutePath}")
                Log.d("AudioManager", "   - Existe: ${file.exists()}")
                Log.d("AudioManager", "   - Tamaño: ${file.length()} bytes")
                Log.d("AudioManager", "   - Lectura: ${file.canRead()}")

                if (file.exists() && file.length() > 1000) {
                    mediaPlayer = MediaPlayer().apply {
                        setDataSource(file.absolutePath)
                        setOnPreparedListener {
                            start()
                            this@AudioManager.isPlaying = true
                            Log.d("AudioManager", "▶️ Reproducción iniciada")
                        }
                        setOnCompletionListener {
                            stopPlaying()
                            onCompletion()
                            Log.d("AudioManager", "✅ Reproducción finalizada")
                        }
                        setOnErrorListener { _, what, extra ->
                            Log.e("AudioManager", "❌ Error en reproducción: what=$what extra=$extra")
                            stopPlaying()
                            onCompletion()
                            true
                        }
                        prepareAsync()
                    }
                } else {
                    Log.e("AudioManager", "❌ Archivo inexistente o corrupto")
                    onCompletion()
                }
            } catch (e: Exception) {
                Log.e("AudioManager", "❌ Error al iniciar reproducción: ${e.message}")
                e.printStackTrace()
                stopPlaying()
                onCompletion()
            }
        } ?: run {
            Log.e("AudioManager", "❌ No hay archivo para reproducir")
            onCompletion()
        }
    }

    // NUEVO MÉTODO - reproduce cualquier archivo por su ruta
    fun startPlaying(audioFilePath: String, onCompletion: () -> Unit = {}) {
        try {
            stopPlaying()

            val file = File(audioFilePath)
            Log.d("AudioManager", "🔍 Verificando archivo para reproducción: $audioFilePath")
            Log.d("AudioManager", "   - Existe: ${file.exists()}")
            Log.d("AudioManager", "   - Tamaño: ${file.length()} bytes")
            Log.d("AudioManager", "   - Lectura: ${file.canRead()}")

            if (file.exists() && file.length() > 1000) {
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(audioFilePath)
                    setOnPreparedListener {
                        start()
                        this@AudioManager.isPlaying = true
                        Log.d("AudioManager", "▶️ Reproducción iniciada: $audioFilePath")
                    }
                    setOnCompletionListener {
                        stopPlaying()
                        onCompletion()
                        Log.d("AudioManager", "✅ Reproducción finalizada: $audioFilePath")
                    }
                    setOnErrorListener { _, what, extra ->
                        Log.e("AudioManager", "❌ Error en reproducción: what=$what extra=$extra")
                        stopPlaying()
                        onCompletion()
                        true
                    }
                    prepareAsync()
                }
            } else {
                Log.e("AudioManager", "❌ Archivo inexistente o corrupto: $audioFilePath")
                onCompletion()
            }
        } catch (e: Exception) {
            Log.e("AudioManager", "❌ Error al iniciar reproducción: ${e.message}")
            e.printStackTrace()
            stopPlaying()
            onCompletion()
        }
    }

    fun stopPlaying() {
        try {
            mediaPlayer?.apply {
                if (isPlaying) {
                    stop()
                    Log.d("AudioManager", "⏹️ Reproducción detenida")
                }
                release()
            }
        } catch (e: Exception) {
            Log.e("AudioManager", "❌ Error al detener reproducción: ${e.message}")
            e.printStackTrace()
        } finally {
            mediaPlayer = null
            isPlaying = false
        }
    }

    // NUEVO MÉTODO - obtener duración de un archivo de audio
    fun getAudioDuration(audioFilePath: String): Int {
        return try {
            val mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource(audioFilePath)
            mediaPlayer.prepare()
            val duration = mediaPlayer.duration / 1000 // convertir a segundos
            mediaPlayer.release()
            Log.d("AudioManager", "⏱️ Duración del audio: ${duration}s")
            duration
        } catch (e: Exception) {
            Log.e("AudioManager", "❌ Error obteniendo duración: ${e.message}")
            0
        }
    }

    // NUEVO MÉTODO - verificar si un archivo es reproducible
    fun isAudioFilePlayable(audioFilePath: String): Boolean {
        return try {
            val file = File(audioFilePath)
            val exists = file.exists()
            val size = file.length()
            val playable = exists && size > 1000

            Log.d("AudioManager", "🔍 Verificando archivo: $audioFilePath")
            Log.d("AudioManager", "   - Existe: $exists")
            Log.d("AudioManager", "   - Tamaño: $size bytes")
            Log.d("AudioManager", "   - Reproducible: $playable")

            playable
        } catch (e: Exception) {
            Log.e("AudioManager", "❌ Error verificando archivo: ${e.message}")
            false
        }
    }

    fun getRecordingState(): Boolean = isRecording
    fun getPlayingState(): Boolean = isPlaying
    fun getAudioFilePath(): String? = audioFile?.absolutePath

    fun deleteRecording() {
        stopRecording()
        stopPlaying()
        audioFile?.delete()
        audioFile = null
        Log.d("AudioManager", "🗑️ Grabación eliminada")
    }

    fun hasRecording(): Boolean {
        val exists = audioFile?.exists() == true
        val size = audioFile?.length() ?: 0
        val valid = exists && size > 1000

        Log.d("AudioManager", "📊 hasRecording: $valid (existe=$exists tamaño=$size)")
        return valid
    }

    fun cleanup() {
        stopRecording()
        stopPlaying()
        Log.d("AudioManager", "🧹 Cleanup completado")
    }
}


DiarioAudioRepository.kt
package mx.edu.utng.appdiario.repository

import mx.edu.utng.appdiario.local.dao.DiarioAudioDao
import mx.edu.utng.appdiario.local.entity.Diario.DiarioAudio

class DiarioAudioRepository(
    private val diarioAudioDao: DiarioAudioDao
) {
    suspend fun insertarDiarioAudio(diarioAudio: DiarioAudio) {
        diarioAudioDao.insertar(diarioAudio)
    }

    suspend fun actualizarDiarioAudio(diarioAudio: DiarioAudio) {
        diarioAudioDao.actualizar(diarioAudio)
    }

    suspend fun eliminarDiarioAudio(diarioAudio: DiarioAudio) {
        diarioAudioDao.eliminar(diarioAudio)
    }

    suspend fun obtenerTodosLosDiariosAudio(): List<DiarioAudio> {
        return diarioAudioDao.obtenerTodos()
    }

    suspend fun obtenerDiarioAudioPorId(id: Int): DiarioAudio? {
        return diarioAudioDao.obtenerTodos().find { it.idDiarioAudio == id }
    }

    suspend fun obtenerDiariosAudioPorTarjeta(tarjetaId: Int): List<DiarioAudio> {
        return diarioAudioDao.obtenerPorTarjeta(tarjetaId)
    }

    suspend fun buscarDiariosAudioPorTitulo(titulo: String): List<DiarioAudio> {
        return diarioAudioDao.obtenerTodos().filter {
            it.titulo.contains(titulo, ignoreCase = true)
        }
    }

    suspend fun obtenerDiariosAudioPorDuracion(minDuracion: Int, maxDuracion: Int): List<DiarioAudio> {
        return diarioAudioDao.obtenerTodos().filter {
            it.audioDuracion in minDuracion..maxDuracion
        }
    }
}

DiarioTextoRepository.kt
package mx.edu.utng.appdiario.repository


import mx.edu.utng.appdiario.local.dao.DiarioTextoDao
import mx.edu.utng.appdiario.local.entity.Diario.DiarioTexto

class DiarioTextoRepository(
    private val diarioTextoDao: DiarioTextoDao
) {
    suspend fun insertarDiarioTexto(diarioTexto: DiarioTexto) {
        diarioTextoDao.insertar(diarioTexto)
    }

    suspend fun actualizarDiarioTexto(diarioTexto: DiarioTexto) {
        diarioTextoDao.actualizar(diarioTexto)
    }

    suspend fun eliminarDiarioTexto(diarioTexto: DiarioTexto) {
        diarioTextoDao.eliminar(diarioTexto)
    }

    suspend fun obtenerTodosLosDiariosTexto(): List<DiarioTexto> {
        return diarioTextoDao.obtenerTodos()
    }

    suspend fun obtenerDiarioTextoPorId(id: Int): DiarioTexto? {
        return diarioTextoDao.obtenerTodos().find { it.idDiarioTexto == id }
    }

    suspend fun obtenerDiariosTextoPorTarjeta(tarjetaId: Int): List<DiarioTexto> {
        return diarioTextoDao.obtenerPorTarjeta(tarjetaId)
    }

    suspend fun buscarDiariosTextoPorTitulo(titulo: String): List<DiarioTexto> {
        return diarioTextoDao.obtenerTodos().filter {
            it.titulo.contains(titulo, ignoreCase = true)
        }
    }
}

TarjetaRepository.kt
package mx.edu.utng.appdiario.repository


import android.content.Context
import mx.edu.utng.appdiario.local.dao.TarjetaDao
import mx.edu.utng.appdiario.local.entity.Tarjeta.Tarjeta

class TarjetaRepository(
    private val tarjetaDao: TarjetaDao,
    private val context: Context
) {


    suspend fun actualizarTarjeta(tarjeta: Tarjeta) {
        tarjetaDao.actualizar(tarjeta)
    }

    suspend fun eliminarTarjeta(tarjeta: Tarjeta) {
        tarjetaDao.eliminar(tarjeta)
    }

    suspend fun obtenerTodasLasTarjetas(): List<Tarjeta> {
        return tarjetaDao.obtenerTodas()
    }

    suspend fun obtenerTarjetaPorId(id: Int): Tarjeta? {
        return tarjetaDao.obtenerTodas().find { it.idTarjeta == id }
    }

    suspend fun obtenerTarjetasPorUsuario(usuarioId: Int): List<Tarjeta> {
        return tarjetaDao.obtenerPorUsuario(usuarioId)
    }

    suspend fun obtenerTarjetasPorTipo(tipo: String): List<Tarjeta> {
        return tarjetaDao.obtenerTodas().filter { it.tipo.name == tipo }
    }

    suspend fun insertarTarjeta(tarjeta: Tarjeta): Int {
        return tarjetaDao.insertar(tarjeta).toInt()
    }

    suspend fun obtenerTarjetasPorTipoYUsuario(tipo: String, usuarioId: Int): List<Tarjeta> {
        return tarjetaDao.obtenerPorTipoYUsuario(tipo, usuarioId)
    }









}

UsuarioRepository.kt
package mx.edu.utng.appdiario.repository

import mx.edu.utng.appdiario.local.dao.UsuarioDao
import mx.edu.utng.appdiario.local.entity.Ususario.TipoUsuario
import mx.edu.utng.appdiario.local.entity.Ususario.Usuario

class UsuarioRepository(
    private val usuarioDao: UsuarioDao
) {
    suspend fun insertarUsuario(usuario: Usuario) {
        usuarioDao.insertar(usuario)
    }

    suspend fun actualizarUsuario(usuario: Usuario) {
        usuarioDao.actualizar(usuario)
    }

    suspend fun eliminarUsuario(usuario: Usuario) {
        usuarioDao.eliminar(usuario)
    }

    // 🔹 MÉTODO PARA OBTENER TODOS LOS USUARIOS
    suspend fun obtenerTodosLosUsuarios(): List<Usuario> {
        return usuarioDao.obtenerTodos()
    }

    suspend fun obtenerUsuarioPorId(id: Int): Usuario? {
        return usuarioDao.obtenerTodos().find { it.idUsua == id }
    }

    // Método más eficiente para obtener por ID (si lo prefieres)
    suspend fun obtenerUsuarioPorIdDirecto(id: Int): Usuario? {
        return usuarioDao.obtenerTodos().firstOrNull { it.idUsua == id }
    }

    suspend fun obtenerUsuarioPorEmail(email: String): Usuario? {
        return usuarioDao.obtenerTodos().find { it.email == email }
    }

    suspend fun validarUsuarioPorEmail(email: String): Boolean {
        val usuario = usuarioDao.obtenerTodos().find { it.email == email }
        return usuario != null
    }



    suspend fun login(email: String, password: String): Usuario? {
        return usuarioDao.login(email, password)
    }

    // Método adicional para verificar email único
    suspend fun verificarEmailUnico(email: String): Boolean {
        return usuarioDao.obtenerTodos().none { it.email == email }
    }

    suspend fun crearUsuarioAdmin() {
        val admin = Usuario(
            nombre = "Admin",
            apellidoPa = "Sistema",
            apellidoMa = "AppDiario",
            fechNaci = "2000-01-01",
            email = "admin@gmail.com",
            password = "Admin.123", // Puedes cambiar esta contraseña
            tipo = TipoUsuario.ADMIN
        )
        usuarioDao.insertar(admin)
    }

    suspend fun existeAdmin(): Boolean {
        val usuarios = usuarioDao.obtenerTodos()
        return usuarios.any { it.tipo == TipoUsuario.ADMIN }
    }
}


Autores
Natalia Estefania Gutierrez Vargas
Brayam Rafael Garcia Martinez

