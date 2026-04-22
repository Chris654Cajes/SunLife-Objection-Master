package com.objectionmaster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.objectionmaster.databinding.ActivityObjectionDetailBinding
import com.objectionmaster.utils.TextToSpeechManager
import com.objectionmaster.viewmodel.ObjectionViewModel

class ObjectionDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityObjectionDetailBinding
    private lateinit var viewModel: ObjectionViewModel
    private var textToSpeechManager: TextToSpeechManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityObjectionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(ObjectionViewModel::class.java)

        // Initialize TextToSpeech
        textToSpeechManager = TextToSpeechManager(this)

        // Get objection ID from intent
        val objectionId = intent.getIntExtra("objection_id", -1)

        // Load objection data
        if (objectionId != -1) {
            viewModel.getObjectionById(objectionId)
        }

        // Setup back button
        setupBackButton()

        // Setup TTS buttons
        setupAudioButtons()

        // Observe ViewModel
        observeViewModel()
    }

    private fun setupBackButton() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun setupAudioButtons() {
        binding.playResponseBtn.setOnClickListener {
            val text = binding.responseScriptText.text.toString()
            if (text.isNotEmpty()) {
                textToSpeechManager?.speak(text)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.selectedObjection.observe(this) { objection ->
            objection?.let {
                binding.apply {
                    detailTitle.text = it.title
                    responseScriptText.text = it.response
                    followUpText.text = it.followUp
                    psychologyText.text = it.psychology
                    confidenceTipText.text = it.confidenceTip
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeechManager?.shutdown()
    }
}
