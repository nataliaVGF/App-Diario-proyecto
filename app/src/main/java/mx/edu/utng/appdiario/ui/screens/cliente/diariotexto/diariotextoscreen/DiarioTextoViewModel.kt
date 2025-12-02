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
