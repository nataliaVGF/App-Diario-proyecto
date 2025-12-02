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
