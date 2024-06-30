package com.example.vocationaltestfinal.chat

import ChatMessage
import ChatUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel(
    private val generativeModel: GenerativeModel
) : ViewModel() {
    private val chat = generativeModel.startChat()

    private val _uiState: MutableStateFlow<ChatUiState> =
        MutableStateFlow(ChatUiState())

    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init {
        // Mensaje de bienvenida inicial
        sendInitialWelcomeMessage()
    }

    private fun sendInitialWelcomeMessage() {
        _uiState.value.addMessage(
            ChatMessage(
                text = "¡Hola! Soy un experto en carreras del área de la salud. Puedes preguntarme sobre las carreras disponibles, plan de estudios, aptitudes requeridas, etc. ",
                participant = Participant.MODEL,
                isPending = false
            )
        )
    }

    fun sendMessage(userMessage: String) {
        // Filtrar preguntas relacionadas con carreras en el área de la salud
        if (isHealthScienceQuestion(userMessage)) {
            _uiState.value.addMessage(
                ChatMessage(
                    text = userMessage,
                    participant = Participant.USER,
                    isPending = true
                )
            )

            viewModelScope.launch {
                try {
                    val response = chat.sendMessage(userMessage)

                    _uiState.value.replaceLastPendingMessage()

                    _uiState.value.addMessage(
                        ChatMessage(
                            text = response.text ?: "",
                            participant = Participant.MODEL,
                            isPending = false
                        )
                    )
                } catch (e: Exception) {
                    _uiState.value.replaceLastPendingMessage()
                    _uiState.value.addMessage(
                        ChatMessage(
                            text = e.localizedMessage ?: "Error desconocido",
                            participant = Participant.ERROR,
                            isPending = false
                        )
                    )
                }
            }
        } else {
            // Mensaje de error para preguntas fuera del área de la salud
            _uiState.value.addMessage(
                ChatMessage(
                    text = "Lo siento, no puedo responder preguntas fuera del área de la salud.",
                    participant = Participant.MODEL,
                    isPending = false
                )
            )
        }
    }

    private fun isHealthScienceQuestion(message: String): Boolean {
        // Lógica para determinar si la pregunta está relacionada con carreras en salud
        val healthScienceKeywords = listOf(
            "carreras de salud",
            "estudios en el área de la salud",
            "profesiones médicas",
            "programas de salud",
            "especialidades médicas",
            "grados en salud",
            "formación en salud",
            "cursos de salud",
            "medicina",
            "opciones de carrera en salud",
            "universidades de salud",
            "facultades de medicina",
            "enfermería",
            "medicina general",
            "cirugía",
            "pediatría",
            "ginecología",
            "obstetricia",
            "oncología",
            "cardiología",
            "neurología",
            "psiquiatría",
            "fisioterapia",
            "terapia ocupacional",
            "odontología",
            "farmacia",
            "nutrición",
            "biomedicina",
            "biotecnología médica",
            "investigación médica",
            "salud pública",
            "epidemiología",
            "administración en salud",
            "gestión hospitalaria",
            "bioquímica clínica",
            "microbiología médica",
            "patología",
            "anatomía patológica",
            "radiología",
            "medicina deportiva",
            "salud ambiental",
            "enfermería pediátrica",
            "enfermería geriátrica",
            "terapia respiratoria",
            "rehabilitación física",
            "técnicas quirúrgicas",
            "atención primaria de salud",
            "psicología clínica",
            "salud materno-infantil",
            "salud sexual y reproductiva",
            "salud mental",
            "médico",
            "médico general",
            "mapa curricular medicina",
            "mapa curricular fisioterapia",
            "mapa curricular enfermería",
            "optometría",
            "logopedia",
            "audiología",
            "terapia ocupacional",
            "nutrición deportiva",
            "genética médica",
            "biotecnología sanitaria",
            "ciencias biomédicas",
            "biotecnología médica",
            "odontología forense",
            "medicina legal",
            "cirugía plástica",
            "enfermería comunitaria",
            "enfermería de urgencias",
            "enfermería oncológica",
            "enfermería quirúrgica",
            "enfermería obstétrica",
            "psicología de la salud",
            "medicina veterinaria",
            "terapia familiar",
            "materia farmacología",
            "doctor",
            "doctora"

        )


        // Convertir el mensaje a minúsculas para una comparación sin distinción entre mayúsculas y minúsculas
        val lowerCaseMessage = message.lowercase()

        // Verificar si alguna de las palabras clave está contenida en el mensaje
        return healthScienceKeywords.any { keyword ->
            // Convertir la palabra clave a minúsculas y verificar su existencia en el mensaje
            keyword.split(" ").all { it in lowerCaseMessage }
        }
    }
}