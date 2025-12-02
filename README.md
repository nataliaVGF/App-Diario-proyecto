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

Tambien ya esta disponible la aplicacion en AppStore

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



Autores
Natalia Estefania Gutierrez Vargas
Brayam Rafael Garcia Martinez

