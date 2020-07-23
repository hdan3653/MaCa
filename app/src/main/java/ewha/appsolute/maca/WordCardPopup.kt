package ewha.appsolute.maca

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import java.util.*

class WordCardPopup : AppCompatActivity() {

    private lateinit var tts:TTS

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wordcard)

        tts = TTS(this)
        tts.onInit(TextToSpeech.SUCCESS)
        Log.e("TTS","on Init")

        var manager = AppManager

        var voca=findViewById<TextView>(R.id.voca)
        var position:Int=intent.getIntExtra("position",0)
        voca.text = manager.wordList.getWordByIndex(position).voca
        var ttsButton=findViewById<ImageButton>(R.id.ttsButton)
        ttsButton.setOnClickListener{
            var message=voca.text
            tts.speak(message)
        }
        var cardView = findViewById<CardView>(R.id.CardView)
        cardView.setOnClickListener {
            val main = MainActivity()
            main.newCardPopup(this,position)
        }
    }

    override fun onDestroy(){
        super.onDestroy()
        tts.destroy()
    }
}


class TTS(private val context: Context) : TextToSpeech.OnInitListener{
    private var tts: TextToSpeech?=null

    override fun onInit(status: Int) {
        if(Build.VERSION.SDK_INT< Build.VERSION_CODES.LOLLIPOP){
            Toast.makeText(context, "Android version must be higher than Lollipop", Toast.LENGTH_SHORT).show()
            return
        }

        tts = TextToSpeech(context){
            if(it == TextToSpeech.SUCCESS){
                var result = tts?.setLanguage((Locale.US))

                if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                    Toast.makeText(context, "Language net supported", Toast.LENGTH_SHORT).show()
                    return@TextToSpeech
                }
            } else {
                Toast.makeText(context, "TTS initialize failed", Toast.LENGTH_SHORT).show()
                return@TextToSpeech
            }
        }
    }

    fun speak(msg:String){
        tts?.speak(msg, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    fun speak(msg:CharSequence){
        tts?.speak(msg, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    fun destroy(){
        tts?.stop()
        tts?.shutdown()
    }
}
