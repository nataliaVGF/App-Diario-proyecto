package mx.edu.utng.appdiario.ui.screens.auth.registro_usuario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.Repository.UsuarioRepository
import mx.edu.utng.appdiario.local.entity.Ususario.TipoUsuario
import mx.edu.utng.appdiario.local.entity.Ususario.Usuario

// Enum para fuerza de contraseña
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
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,


    // Nuevos campos para validaciones
    val nombreError: String? = null,
    val apellidoPaternoError: String? = null,
    val apellidoMaternoError: String? = null,
    val fechaNacimientoError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val passwordStrength: PasswordStrength = PasswordStrength.EMPTY,
    val isFormValid: Boolean = false
)

class RegistroViewModel(
    private val repository: UsuarioRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RegistroState())
    val state: StateFlow<RegistroState> = _state.asStateFlow()

    fun onNombreChange(nombre: String) {
        val error = validateNombre(nombre)
        _state.value = _state.value.copy(
            nombre = nombre,
            nombreError = error,
            isFormValid = validateForm()
        )
    }

    fun onApellidoPaternoChange(apellido: String) {
        val error = validateApellido(apellido, "apellido paterno")
        _state.value = _state.value.copy(
            apellidoPaterno = apellido,
            apellidoPaternoError = error,
            isFormValid = validateForm()
        )
    }

    fun onApellidoMaternoChange(apellido: String) {
        val error = validateApellido(apellido, "apellido materno")
        _state.value = _state.value.copy(
            apellidoMaterno = apellido,
            apellidoMaternoError = error,
            isFormValid = validateForm()
        )
    }

    fun onFechaNacimientoChange(fecha: String) {
        val formattedFecha = formatDateInput(fecha)
        val error = validateFechaNacimiento(formattedFecha)
        _state.value = _state.value.copy(
            fechaNacimiento = formattedFecha,
            fechaNacimientoError = error,
            isFormValid = validateForm()
        )
    }

    fun onEmailChange(email: String) {
        val error = validateEmail(email)
        _state.value = _state.value.copy(
            email = email,
            emailError = error,
            isFormValid = validateForm()
        )
    }

    fun onPasswordChange(password: String) {
        val error = validatePassword(password)
        val strength = calculatePasswordStrength(password)
        _state.value = _state.value.copy(
            password = password,
            passwordError = error,
            passwordStrength = strength,
            isFormValid = validateForm()
        )
    }

    // ========== VALIDACIONES ==========

    private fun validateNombre(nombre: String): String? {
        return when {
            nombre.isEmpty() -> "El nombre es obligatorio"
            nombre.length < 2 -> "El nombre debe tener al menos 2 caracteres"
            !nombre.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+\$")) -> "Solo se permiten letras"
            else -> null
        }
    }

    private fun validateApellido(apellido: String, tipo: String): String? {
        return when {
            apellido.isEmpty() -> "El $tipo es obligatorio"
            apellido.length < 2 -> "El $tipo debe tener al menos 2 caracteres"
            !apellido.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+\$")) -> "Solo se permiten letras"
            else -> null
        }
    }

    private fun validateFechaNacimiento(fecha: String): String? {
        return when {
            fecha.isEmpty() -> "La fecha de nacimiento es obligatoria"
            !fecha.matches(Regex("^\\d{2}/\\d{2}/\\d{4}\$")) -> "Formato debe ser DD/MM/AAAA"
            else -> {
                try {
                    val parts = fecha.split("/")
                    val day = parts[0].toInt()
                    val month = parts[1].toInt()
                    val year = parts[2].toInt()

                    when {
                        day !in 1..31 -> "Día inválido (1-31)"
                        month !in 1..12 -> "Mes inválido (1-12)"
                        year < 1900 -> "Año debe ser mayor a 1900"
                        year > 2024 -> "Año no puede ser futuro"
                        else -> null
                    }
                } catch (e: Exception) {
                    "Fecha inválida"
                }
            }
        }
    }

    private fun validateEmail(email: String): String? {
        return when {
            email.isEmpty() -> "El email es obligatorio"
            !email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")) -> "Formato de email inválido"
            else -> null
        }
    }

    private fun validatePassword(password: String): String? {
        return when {
            password.isEmpty() -> "La contraseña es obligatoria"
            password.length < 8 -> "Mínimo 8 caracteres"
            !password.matches(Regex(".*[A-Z].*")) -> "Debe contener al menos una mayúscula"
            !password.matches(Regex(".*[a-z].*")) -> "Debe contener al menos una minúscula"
            !password.matches(Regex(".*\\d.*")) -> "Debe contener al menos un número"
            !password.matches(Regex(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) ->
                "Debe contener al menos un carácter especial"
            else -> null
        }
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

    private fun validateForm(): Boolean {
        return validateNombre(_state.value.nombre) == null &&
                validateApellido(_state.value.apellidoPaterno, "apellido paterno") == null &&
                validateApellido(_state.value.apellidoMaterno, "apellido materno") == null &&
                validateFechaNacimiento(_state.value.fechaNacimiento) == null &&
                validateEmail(_state.value.email) == null &&
                validatePassword(_state.value.password) == null
    }

    // Función para formatear fecha automáticamente
    private fun formatDateInput(input: String): String {
        if (input.length > 10) return input.take(10)

        val cleanInput = input.filter { it.isDigit() }
        return when {
            cleanInput.length <= 2 -> cleanInput
            cleanInput.length <= 4 -> "${cleanInput.take(2)}/${cleanInput.drop(2)}"
            else -> "${cleanInput.take(2)}/${cleanInput.drop(2).take(2)}/${cleanInput.drop(4).take(4)}"
        }
    }

    // Registrar nuevo usuario (POST) - ACTUALIZADO con validaciones
    fun registrarUsuario() {
        viewModelScope.launch {
            try {
                // Validar formulario antes de enviar
                if (!validateForm()) {
                    _state.value = _state.value.copy(
                        error = "Por favor corrige los errores del formulario",
                        isLoading = false
                    )
                    return@launch
                }

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

                repository.insertarUsuario(usuario)
                _state.value = _state.value.copy(isLoading = false, isSuccess = true)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Error al registrar usuario: ${e.message}"
                )
            }
        }
    }

    fun limpiarEstado() {
        _state.value = RegistroState()
    }
}