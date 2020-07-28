package ewha.appsolute.maca

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.wordcard.*
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

        var voca=findViewById<TextView>(R.id.text_voca)

        var position:Int=intent.getIntExtra("position",0)
        voca.text = manager.wordList.getWordByIndex(position).voca

        var ttsButton=findViewById<ImageButton>(R.id.ttsButton)
        ttsButton.setOnClickListener{
            var message=voca.text
            tts.speak(message)
        }

        editbutton.setOnClickListener {
            val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.popup_editword, null)

            val alertDialog = EditWordPopup(this)

            val adapter = VocaCardAdapter(AppManager.wordList, this)

            alertDialog.setContentView(view)
            alertDialog.show()
            alertDialog.setOnDismissListener{
                adapter.notifyDataSetChanged()
            }
        }

        var cardView = findViewById<CardView>(R.id.CardView)
        cardView.setOnClickListener {
            var intent = Intent(this, WordCardPopup2::class.java)
            //여기 순서가 틀려서 position 값이 안 넘어갔음.
            intent.putExtra("position", position)
            this.startActivity(intent)
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
