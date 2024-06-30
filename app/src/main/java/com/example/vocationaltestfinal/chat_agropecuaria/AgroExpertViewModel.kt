package com.example.vocationaltestfinal.chat_agropecuaria

import ChatMessage
import ChatUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AgroExpertViewModel(
    private val generativeModel: GenerativeModel
) : ViewModel() {
    private val chat = generativeModel.startChat()

    private val _uiState: MutableStateFlow<ChatUiState> =
        MutableStateFlow(ChatUiState())

    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init {
        sendInitialWelcomeMessage()
    }

    private fun sendInitialWelcomeMessage() {
        _uiState.value.addMessage(
            ChatMessage(
                text = "¡Hola! Soy un experto en carreras del área agropecuaria. Puedes preguntarme sobre las carreras disponibles, plan de estudios, aptitudes requeridas, etc.",
                participant = Participant.MODEL,
                isPending = false
            )
        )
    }

    fun sendMessage(userMessage: String) {
        if (isAgroQuestion(userMessage)) {
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
            _uiState.value.addMessage(
                ChatMessage(
                    text = "Lo siento, no puedo responder preguntas fuera del área agropecuaria.",
                    participant = Participant.MODEL,
                    isPending = false
                )
            )
        }
    }

    private fun isAgroQuestion(message: String): Boolean {
        val agroKeywords = listOf(
            "agronomía",
            "ciencias agropecuarias",
            "ingeniería agronómica",
            "agricultura",
            "agroindustria",
            "zootecnia",
            "recursos naturales",
            "producción animal",
            "producción vegetal",
            "fitotecnia",
            "horticultura",
            "agrobiotecnología",
            "manejo de suelos",
            "entomología",
            "patología vegetal",
            "agroforestería",
            "hidrología agrícola"
        )

        val lowerCaseMessage = message.lowercase()

        return agroKeywords.any { keyword ->
            keyword.split(" ").all { it in lowerCaseMessage }
        }
    }
}
