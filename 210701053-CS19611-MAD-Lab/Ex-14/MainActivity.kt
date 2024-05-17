package com.example.speechtotext 
import android.content.Intent 
import android.speech.RecognizerIntent 
import androidx.appcompat.app.AppCompatActivity 
import android.os.Bundle 
import android.speech.RecognitionListener 
import android.speech.SpeechRecognizer 
import android.widget.Button 
import android.widget.TextView 
import java.util.* 
class MainActivity : AppCompatActivity() { 
    private lateinit var buttonRecord: Button 
    private lateinit var textViewResult: TextView 
    private lateinit var speechRecognizer: SpeechRecognizer 
    override fun onCreate(savedInstanceState: Bundle?) { 
        super.onCreate(savedInstanceState) 
        setContentView(R.layout.activity_main) 
        buttonRecord = findViewById(R.id.buttonRecord) 
        textViewResult = findViewById(R.id.textViewResult) 
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this) 
        buttonRecord.setOnClickListener {
 startSpeechToText() 
        } 
        speechRecognizer.setRecognitionListener(object : RecognitionListener { 
            override fun onReadyForSpeech(params: Bundle?) {} 
            override fun onBeginningOfSpeech() {} 
            override fun onRmsChanged(rmsdB: Float) {} 
            override fun onBufferReceived(buffer: ByteArray?) {} 
            override fun onEndOfSpeech() {} 
            override fun onError(error: Int) {} 
            override fun onResults(results: Bundle?) { 
                val matches = 
results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION) 
                if (matches != null) { 
                    val result = matches[0] 
                    textViewResult.text = "Result: $result" 
                } 
            } 
            override fun onPartialResults(partialResults: Bundle?) {} 
            override fun onEvent(eventType: Int, params: Bundle?) {} 
        }) 
    } 
    private fun startSpeechToText() { 
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
  intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, 
RecognizerIntent.LANGUAGE_MODEL_FREE_FORM) 
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, 
Locale.getDefault()) 
        speechRecognizer.startListening(intent) 
    } 
}