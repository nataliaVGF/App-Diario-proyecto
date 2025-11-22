package mx.edu.utng.appdiario.ui.screens.cliente.diarioTexto.DiarioTextoScreen


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
