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
