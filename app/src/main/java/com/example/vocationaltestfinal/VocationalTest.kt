package com.example.vocationaltestfinal

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

class VocationalTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            VocationalTestScreen(navController)
        }
    }
}

@Composable
fun VocationalTestScreen(navController: NavController) {
    // Preguntas y opciones de respuesta
    val questions = listOf(
        "Solventar problemas contables y financieros, controlar presupuestos y contabilidad.",
        "Trabajar en un banco o caja postal.",
        "Realizar programas de gestión empresarial.",
        "Contratar pólizas de seguros. Entrevistarse con el cliente para averiguar la clase de seguro que necesita.",
        "Trabajar ordenando, clasificando y archivando documentos.",
        "Ejercer de sociólogo.",
        "Trabajar como notario.",
        "Trabajar como auditor.",
        "Organizar y controlar la recepción, almacenamiento y expedición de mercancías, anotando las entradas y las salidas.",
        "Realizar trabajos de oficina, manejando ordenadores, teléfonos, fotocopiadoras, etc."
    )

    val options = listOf(
        listOf("Me gusta", "Tengo dudas", "No me gusta", "No conozco esa actividad"),
        listOf("Me gusta", "Tengo dudas", "No me gusta", "No conozco esa actividad"),
        listOf("Me gusta", "Tengo dudas", "No me gusta", "No conozco esa actividad"),
        listOf("Me gusta", "Tengo dudas", "No me gusta", "No conozco esa actividad"),
        listOf("Me gusta", "Tengo dudas", "No me gusta", "No conozco esa actividad"),
        listOf("Me gusta", "Tengo dudas", "No me gusta", "No conozco esa actividad"),
        listOf("Me gusta", "Tengo dudas", "No me gusta", "No conozco esa actividad"),
        listOf("Me gusta", "Tengo dudas", "No me gusta", "No conozco esa actividad"),
        listOf("Me gusta", "Tengo dudas", "No me gusta", "No conozco esa actividad"),
        listOf("Me gusta", "Tengo dudas", "No me gusta", "No conozco esa actividad")
    )

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var showResult by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showResult) {
            Text(
                text = "Has completado el cuestionario.",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF11366A)
            )
        } else {
            Text(
                text = questions[currentQuestionIndex],
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF11366A),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            options[currentQuestionIndex].forEachIndexed { index, option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedOption == index,
                        onClick = { selectedOption = index }
                    )
                    Text(
                        text = option,
                        fontSize = 16.sp,
                        color = Color(0xFF11366A),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (selectedOption == null) {
                        Toast.makeText(
                            context,
                            "Por favor selecciona una respuesta",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        if (currentQuestionIndex < questions.size - 1) {
                            currentQuestionIndex++
                            selectedOption = null
                        } else {
                            showResult = true
                        }
                    }
                }
            ) {
                Text(text = "Siguiente")
            }
        }
    }
}
