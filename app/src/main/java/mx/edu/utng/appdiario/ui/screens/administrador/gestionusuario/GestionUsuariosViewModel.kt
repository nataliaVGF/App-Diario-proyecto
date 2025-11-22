package mx.edu.utng.appdiario.ui.screens.administrador.gestionusuario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.edu.utng.appdiario.Repository.UsuarioRepository
import mx.edu.utng.appdiario.local.entity.Ususario.TipoUsuario

data class UsuarioState(
    val usuarios: List<Usuario> = emptyList(),
    val usuariosFiltrados: List<Usuario> = emptyList(),
    val searchText: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val usuarioSeleccionado: Usuario? = null
)

class GestionUsuariosViewModel(
    private val repository: UsuarioRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UsuarioState())
    val state: StateFlow<UsuarioState> = _state.asStateFlow()

    init {
        cargarUsuarios()
    }

    fun onSearchTextChange(text: String) {
        _state.update { currentState ->
            currentState.copy(
                searchText = text,
                usuariosFiltrados = filtrarUsuarios(currentState.usuarios, text)
            )
        }
    }

    fun cargarUsuarios() {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true, error = null) }

                val usuariosEntity = repository.obtenerTodosLosUsuarios()
                val usuariosUi = usuariosEntity.map { entity ->
                    Usuario(
                        id = entity.idUsua.toString(),
                        nombre = "${entity.nombre} ${entity.apellidoPa} ${entity.apellidoMa}".trim(),
                        email = entity.email,
                        rol = when (entity.tipo) {
                            TipoUsuario.ADMIN -> "Administrador"
                            TipoUsuario.NORMAL -> "Usuario"
                        },
                        fechaRegistro = entity.fechaRegistro // 🔹 Guardamos timestamp
                    )
                }

                _state.update { currentState ->
                    currentState.copy(
                        usuarios = usuariosUi,
                        usuariosFiltrados = filtrarUsuarios(usuariosUi, currentState.searchText),
                        isLoading = false
                    )
                }

            } catch (e: Exception) {
                _state.update { currentState ->
                    currentState.copy(
                        error = "Error al cargar usuarios: ${e.message}",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun eliminarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            try {
                val usuarioEntity = repository.obtenerUsuarioPorId(usuario.id.toInt())

                if (usuarioEntity != null) {
                    repository.eliminarUsuario(usuarioEntity)
                    cargarUsuarios()
                } else {
                    _state.update { currentState ->
                        currentState.copy(error = "Usuario no encontrado")
                    }
                }

            } catch (e: Exception) {
                _state.update { currentState ->
                    currentState.copy(error = "Error al eliminar usuario: ${e.message}")
                }
            }
        }
    }

    fun actualizarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            try {
                val usuarioExistente = repository.obtenerUsuarioPorId(usuario.id.toInt())

                if (usuarioExistente != null) {
                    // Verificar si el email ya existe (excluyendo el usuario actual)
                    val emailExistente = repository.obtenerUsuarioPorEmail(usuario.email)
                    if (emailExistente != null && emailExistente.idUsua != usuario.id.toInt()) {
                        _state.update { currentState ->
                            currentState.copy(error = "El email ya está en uso por otro usuario")
                        }
                        return@launch
                    }

                    val nombres = usuario.nombre.split(" ")
                    val usuarioActualizado = usuarioExistente.copy(
                        nombre = nombres.getOrElse(0) { "" },
                        apellidoPa = nombres.getOrElse(1) { "" },
                        apellidoMa = nombres.getOrElse(2) { "" },
                        email = usuario.email,
                        tipo = when (usuario.rol) {
                            "Administrador" -> TipoUsuario.ADMIN
                            else -> TipoUsuario.NORMAL
                        }
                    )

                    repository.actualizarUsuario(usuarioActualizado)
                    cargarUsuarios()
                } else {
                    _state.update { currentState ->
                        currentState.copy(error = "Usuario no encontrado para actualizar")
                    }
                }

            } catch (e: Exception) {
                _state.update { currentState ->
                    currentState.copy(error = "Error al actualizar usuario: ${e.message}")
                }
            }
        }
    }

    fun limpiarError() {
        _state.update { it.copy(error = null) }
    }

    private fun filtrarUsuarios(usuarios: List<Usuario>, searchText: String): List<Usuario> {
        return if (searchText.isBlank()) {
            usuarios
        } else {
            usuarios.filter { usuario ->
                usuario.nombre.contains(searchText, ignoreCase = true) ||
                        usuario.email.contains(searchText, ignoreCase = true) ||
                        usuario.rol.contains(searchText, ignoreCase = true)
            }
        }
    }
}

