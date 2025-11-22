package mx.edu.utng.appdiario.Repository

import mx.edu.utng.appdiario.local.dao.UsuarioDao
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

    suspend fun login(email: String, password: String): Usuario? {
        return usuarioDao.login(email, password)
    }

    // Método adicional para verificar email único
    suspend fun verificarEmailUnico(email: String): Boolean {
        return usuarioDao.obtenerTodos().none { it.email == email }
    }
}