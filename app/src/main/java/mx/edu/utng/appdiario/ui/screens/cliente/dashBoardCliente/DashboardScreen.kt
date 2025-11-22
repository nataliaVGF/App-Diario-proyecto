package mx.edu.utng.appdiario.ui.screens.cliente.dashBoardCliente

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun DashboardScreen(
    localNavController: NavHostController,
    globalNavController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6D3)) // ✅ FONDO AGREGADO
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título de bienvenida
        Text(
            text = "¡Bienvenido a tu Diario!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6D3B1A),
            modifier = Modifier.padding(bottom = 40.dp)
        )

        // Botón Diario Texto - usa localNavController para navegación interna
        Button(
            onClick = { localNavController.navigate("lista_diarios") },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6D3B1A)
            )
        ) {
            Text(
                text = "Diario Texto",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        // Botón Diario Audio - usa localNavController para navegación interna
        Button(
            onClick = { localNavController.navigate("lista_diarios_audio") }, // ✅ CORREGIDO para ir a la lista de audios
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8D4E25)
            )
        ) {
            Text(
                text = "Diario Audio",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        // Mensaje adicional
        Text(
            text = "Elige cómo quieres expresarte hoy",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 40.dp)
        )
    }
}