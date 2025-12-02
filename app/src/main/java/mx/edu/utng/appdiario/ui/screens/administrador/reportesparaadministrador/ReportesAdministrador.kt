package mx.edu.utng.appdiario.ui.screens.administrador.reportesparaadministrador

import android.graphics.Color as AndroidColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import mx.edu.utng.appdiario.repository.UsuarioRepository
import mx.edu.utng.appdiario.local.database.AppDatabase

@Composable
fun ReportesAdmin(navController: NavController) {

    val context = LocalContext.current
    val usuarioRepository = UsuarioRepository(AppDatabase.getDatabase(context).usuarioDao())
    val viewModel: ReportesViewModel = viewModel(
        factory = ReportesViewModelFactory(usuarioRepository)
    )
    val state by viewModel.state.collectAsState()

    // 🔹 Scroll vertical
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3))
            .verticalScroll(scrollState), // ✅ Habilita scroll
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Título principal
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color(0xFF6D3B1A)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "REPORTES",
                color = Color.White,
                fontSize = 30.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Gráfico de usuarios por mes
        Text(
            text = "Usuarios por Mes",
            fontSize = 20.sp,
            color = Color(0xFF4E2A0E)
        )

        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            factory = { ctx ->
                BarChart(ctx).apply {
                    description.isEnabled = false
                    xAxis.position = XAxis.XAxisPosition.BOTTOM
                    axisRight.isEnabled = false
                    legend.isEnabled = false
                }
            },
            update = { chart ->
                val entries = state.usuariosPromedioPorMes.entries.mapIndexed { index, entry ->
                    BarEntry(index.toFloat(), entry.value.toFloat())
                }
                val dataSet = BarDataSet(entries, "Usuarios por Mes").apply {
                    color = AndroidColor.parseColor("#6D3B1A")
                    valueTextColor = AndroidColor.BLACK
                    valueTextSize = 12f
                }
                chart.data = BarData(dataSet)
                chart.xAxis.valueFormatter = IndexAxisValueFormatter(
                    state.usuariosPromedioPorMes.keys.toList()
                )
                chart.invalidate()
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 🔹 Gráfico de usuarios por tipo
        Text(
            text = "Usuarios por Tipo",
            fontSize = 20.sp,
            color = Color(0xFF4E2A0E)
        )

        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            factory = { ctx ->
                PieChart(ctx).apply {
                    description.isEnabled = false
                    legend.isEnabled = true
                    setUsePercentValues(true)
                    setEntryLabelColor(AndroidColor.BLACK)
                }
            },
            update = { chart ->
                val entries = state.usuariosPorTipo.entries.map {
                    PieEntry(
                        it.value.toFloat(),
                        it.key
                    )
                }
                val dataSet = PieDataSet(entries, "Usuarios").apply {
                    colors = listOf(
                        AndroidColor.parseColor("#6D3B1A"), // ADMIN
                        AndroidColor.parseColor("#D9A97C")  // NORMAL
                    )
                    valueTextColor = AndroidColor.BLACK
                    valueTextSize = 12f
                }
                chart.data = PieData(dataSet)
                chart.invalidate()
            }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
