package com.example.vocationaltestfinal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.vocationaltestfinal.chat.ChatViewModel
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig



val GenerativeViewModelFactory = object : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        viewModelClass: Class<T>,
        extras: CreationExtras
    ): T {
        val config = generationConfig {
            temperature = 0.7f
        }

        return with(viewModelClass) {
            when {

                isAssignableFrom(ChatViewModel::class.java) -> {
                    // Initialize a GenerativeModel with the `gemini-pro` AI model for chat
                    val generativeModel = GenerativeModel(
                        modelName = "gemini-1.0-pro",
                        apiKey = "AIzaSyBRun1w3m751D0T4AZylRszG_-OHRpZCC0",
                        generationConfig = config
                    )
                    ChatViewModel(generativeModel)
                }

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${viewModelClass.name}")
            }
        } as T
    }
}