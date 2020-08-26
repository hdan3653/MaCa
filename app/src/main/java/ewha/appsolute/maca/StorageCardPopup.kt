package ewha.appsolute.maca

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.wordcard.*
import java.util.*

class StorageCardPopup(context: Context, private var position: Int, private var word: Word) : AlertDialog(context) {

    private lateinit var tts:TTS

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wordcard)

        tts = TTS(context)
        tts.onInit(TextToSpeech.SUCCESS)

        text_voca.text = word.voca

        ttsButton.setOnClickListener{
            val message = word.voca
            tts.speak(message)
        }

        editbutton.setOnClickListener {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.popup_editword, null)

            val alertDialog = EditWordPopup(context, word)

            val adapter = VocaCardAdapter(AppManager.storageList, context)

            alertDialog.setContentView(view)
            alertDialog.show()
            alertDialog.setOnDismissListener{
                adapter.notifyDataSetChanged()
            }
        }

        tableLayout.setOnClickListener {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.wordcard2_storage, null)

            val alertDialog = StorageCardPopup2(context, word)

            alertDialog.setContentView(view)
            alertDialog.show()
            alertDialog.setOnDismissListener{
                Log.e("Alert Dialog", "WordCardPopup2 Dismissed.")

                val manager = AppManager.storageList
                position += 1

                if(position > 0 && position < manager.getItemCount()) {
                    val temp = manager.getWord(position)
                    if(temp != null) {
                        word = temp
                        text_voca.text = word.voca
                    } else {
                        Log.e("Alert Dialog", "StorageCardPopup2 Dismissed. - null word")
                        this.dismiss()
                    }
                } else {
                    Log.e("Alert Dialog", "StorageCardPopup2 Dismissed. - $position")
                    this.dismiss()
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        tts.destroy()
    }
}
