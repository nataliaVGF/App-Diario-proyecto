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