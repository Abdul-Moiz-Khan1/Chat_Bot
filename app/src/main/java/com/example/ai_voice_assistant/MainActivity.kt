package com.example.ai_voice_assistant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ai_voice_assistant.databinding.ActivityMainBinding
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var apikey = "AIzaSyCpY0SCEpYNkH7zxB7Il4LzttrTklfSlAc"
//    val apikey = BuildConfig.API_KEY
    lateinit var chat: Chat
    var stringBuilder: StringBuilder = java.lang.StringBuilder()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val generativeModel = GenerativeModel(
            // For text-only input, use the gemini-pro model
            modelName = "gemini-pro",
            // Access your API key as a Build Configuration variable (see "Set up your API key" above)
            apiKey = apikey
        )
        chat = generativeModel.startChat(
            history = listOf(
                content(role = "user") { text("Hello, I have 2 dogs in my house.") },
                content(role = "model") { text("Great to meet you. What would you like to know?") }
            )
        )
//        stringBuilder.append("Hello, I have 2 dogs in my house.\n\n")
//        stringBuilder.append("Great to meet you. What would you like to know?\n\n")

        binding.response.setText(stringBuilder.toString())


        binding.sendBtn.setOnClickListener {
            send_prompt()
        }
    }

    private fun send_prompt() {
        stringBuilder.append(binding.prompt.text.toString() + "\n\n")
        MainScope().launch {
            var result = chat.sendMessage(binding.prompt.text.toString())
            stringBuilder.append(result.text + "\n\n")

            binding.response.setText(stringBuilder.toString())
            binding.prompt.setText("")

        }
    }
}