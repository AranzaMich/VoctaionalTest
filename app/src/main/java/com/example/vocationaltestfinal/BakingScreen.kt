package com.example.vocationaltestfinal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vocationaltestfinal.R

val Poppins = FontFamily(
    Font(R.font.poppins),
    Font(R.font.poppins, FontWeight.Bold),
    // Agrega más estilos si tienes diferentes pesos de fuente
)



@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun BakingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Hola, Evelyn!",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF11366A),
            fontFamily = Poppins,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "Bienvenido(a)",
            fontSize = 18.sp,
            color = Color(0xFF11366A),
            fontFamily = Poppins,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "Buscar...", fontFamily = Poppins, fontSize = 16.sp) },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = null)
            },

            shape = RoundedCornerShape(16.dp), // Redondea más los bordes
            modifier = Modifier
                .fillMaxWidth() // Asegura que el TextField ocupe todo el ancho disponible
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEBF1FD)),
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Bienvenido\n\nMapa Vocacional",
                    fontFamily = Poppins,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF11366A),
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.pic_banner),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .padding(end = 16.dp), // Ajuste de margen alrededor de la imagen
                    contentScale = ContentScale.Fit
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Programando proyectos",
            modifier = Modifier.padding(top = 8.dp),
            fontFamily = Poppins,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF11366A)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF11366A)),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate("vocationalTest") } // Navegar a la pantalla VocationalTest
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "29/06/2024", fontSize = 14.sp, color = Color.White, fontFamily = Poppins)
                Text(
                    text = "Test Vocacional",
                    fontSize = 18.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = 0.8f,
                    color = Color.White,
                    trackColor = Color(0xFFBDBDBD),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Progreso 80%", fontSize = 14.sp, color = Color.White, fontFamily = Poppins)
            }
        }


        // Bottom navigation bar
        Spacer(modifier = Modifier.weight(1f))
        NavigationBar {
            NavigationBarItem(
                icon = { Icon(painter = painterResource(id = R.drawable.ic_home), contentDescription = null) },
                label = { Text("Inicio", fontFamily = Poppins) },
                selected = false,
                onClick = { navController.navigate("bakingScreen") }
            )
            NavigationBarItem(
                icon = { Icon(painter = painterResource(id = R.drawable.ic_tips), contentDescription = null) },
                label = { Text("Consejos", fontFamily = Poppins) },
                selected = false,
                onClick = { navController.navigate("consejos") }
            )
            NavigationBarItem(
                icon = { Icon(painter = painterResource(id = R.drawable.ic_tests), contentDescription = null) },
                label = { Text("Otros Test", fontFamily = Poppins) },
                selected = true,
                onClick = { navController.navigate("otrosTest") }
            )
            NavigationBarItem(
                icon = { Icon(painter = painterResource(id = R.drawable.ic_account), contentDescription = null) },
                label = { Text("Cuenta", fontFamily = Poppins) },
                selected = false,
                onClick = { navController.navigate("cuenta") }
            )
        }
    }
}


@Composable
fun ConsejosScreen() {
    // Implementa el contenido de la pantalla Consejos aquí
}

@Composable
fun OtrosTestScreen() {
    // Implementa el contenido de la pantalla Otros Test aquí
}

@Composable
fun CuentaScreen(
    navController: NavController, // Añadir NavController como parámetro
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        // User information card
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF11366A)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile), // Reemplaza con tu imagen de usuario
                        contentDescription = null,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Evelyn Solano Portillo",
                        fontFamily = Poppins,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Estudiante",
                        fontFamily = Poppins,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "21 años", fontSize = 14.sp, color = Color.White, fontFamily = Poppins)
                            Text(text = "Edad", fontSize = 12.sp, color = Color.White)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "22/06/2024", fontSize = 14.sp, color = Color.White, fontFamily = Poppins)
                            Text(text = "Fecha de Registro", fontSize = 12.sp, color = Color.White, fontFamily = Poppins)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Tlaxcala", fontSize = 14.sp, color = Color.White, fontFamily = Poppins)
                            Text(text = "Ubicación", fontSize = 12.sp, color = Color.White, fontFamily = Poppins)
                        }
                    }
                }
            }
        }

        // Recursos section
        item {
            Text(
                text = "Recursos",
                fontFamily = Poppins,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF11366A),
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        // Cards for resources
        items(1) { index ->
            Row(modifier = Modifier.fillMaxWidth()) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEBF1FD)),
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.folder), // Reemplaza con tu icono
                            contentDescription = null
                        )
                        Text(
                            text = "Biblioteca de Recursos",
                            fontSize = 16.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEBF1FD)),
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.folder), // Reemplaza con tu icono
                            contentDescription = null
                        )
                        Text(
                            text = "Cursos Disponibles",
                            fontSize = 16.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }

        // Chatbots section
        item {
            Text(
                text = "Chatbots",
                fontFamily = Poppins,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF11366A),
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )
        }

        // Chatbot cards
        val chatbots = listOf(
            "AgroExpert" to "Ciencias Agropecuarias",
            "HealthGuide" to "Ciencias de la Salud",
            "ScienceMaster" to "Ciencias Naturales y Exactas",
            "SociedadSavant" to "Ciencias Sociales y Administrativas",
            "HumanidadesSage" to "Educación y Humanidades",
            "IngenioTech" to "Ingeniería y Tecnología"
        )

        items(chatbots.size) { index ->
            val (name, field) = chatbots[index]
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFF11366A)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        // Navegar a la pantalla del chatbot correspondiente al nombre seleccionado
                        navigateToChatbot(navController, name)
                    }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = name, fontSize = 16.sp, fontFamily = Poppins, color = Color.White, fontWeight = FontWeight.Bold)
                    Text(text = field, fontSize = 12.sp, color = Color.White, fontFamily = Poppins)
                }
            }
        }
    }
}

// Función para navegar al chatbot correspondiente al nombre seleccionado
private fun navigateToChatbot(navController: NavController, chatbotName: String) {
    when (chatbotName) {
        "HealthGuide" -> navController.navigate("chat")
        "AgroExpert" -> navController.navigate("chat")
        "ScienceMaster" -> navController.navigate("chat")
        "SociedadSavant" -> navController.navigate("chat")
        "HumanidadesSage" -> navController.navigate("chat")
        "IngenioTech" -> navController.navigate("chat")
        else -> {
            // Handle any other chatbot names or cases
        }
    }
}
