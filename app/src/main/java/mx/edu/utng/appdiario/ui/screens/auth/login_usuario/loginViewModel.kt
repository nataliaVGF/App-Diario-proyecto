package mx.edu.utng.appdiario.ui.screens.auth.login_usuario

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.Repository.UsuarioRepository
import mx.edu.utng.appdiario.local.entity.Ususario.TipoUsuario

class LoginViewModel(
    private val repository: UsuarioRepository,
    private val sessionManager: SessionManager // ✅ Añadido SessionManager como parámetro
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

    ////////////////////////////////ONELOGINCHANGED(Actualizar login)
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

    ////////////////////////////// FUNCIÓN PARA LOGIN CON SQLite
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
                // PASO 2: Buscar usuario en SQLite
                val usuario = repository.obtenerUsuarioPorEmail(currentEmail)

                if (usuario == null) {
                    _errorMessage.value = "Usuario no encontrado"
                    _isLoading.value = false
                    return@launch
                }

                // Verificar contraseña
                if (usuario.password != currentPassword) {
                    _errorMessage.value = "Contraseña incorrecta"
                    _isLoading.value = false
                    return@launch
                }

                // ✅ GUARDAR SESIÓN después del login exitoso
                println("DEBUG: Login exitoso, guardando userId: ${usuario.idUsua}")
                sessionManager.saveUserId(usuario.idUsua)

                // Login exitoso - navegar según tipo de usuario
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

    ////////////////////////////// FUNCIONES PARA LIMPIAR ESTADOS DE NAVEGACIÓN
    fun onNavigateToRegisterCompleted() {
        _navigateToRegister.value = false
    }

    fun onNavigateToAdminCompleted() {
        _navigateToAdmin.value = false
    }

    fun onNavigateToUserCompleted() {
        _navigateToUser.value = false
    }

    ////////////////////////////// FUNCIÓN PARA NAVEGAR A REGISTRO
    fun navigateToRegister() {
        _navigateToRegister.value = true
    }
}