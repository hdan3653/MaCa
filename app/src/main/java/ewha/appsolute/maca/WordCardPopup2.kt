package ewha.appsolute.maca

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.wordcard2.*

class WordCardPopup2(context: Context, private val word: Word) : AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wordcard2)

        meaning3.text = word.primary
        meaning4.text = word.secondary

        var posText: String? = null
        when (word.pos) {
            POS.NOUN.ordinal -> posText = "N."
            POS.ADJECTIVE.ordinal -> posText = "A."
            POS.VERB.ordinal -> posText = "V."
            POS.ADVERB.ordinal -> posText = "AD."
            POS.PREPOSITION.ordinal -> posText = "PREP."
            POS.IDIOM.ordinal -> posText = "ID."
        }
        pos2.text = posText


        editbutton2.setOnClickListener {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.popup_editword, null)

            val alertDialog = EditWordPopup(context, word)

            val adapter = VocaCardAdapter(AppManager.wordList, context)

            alertDialog.setContentView(view)
            alertDialog.show()
            alertDialog.setOnDismissListener{
                adapter.notifyDataSetChanged()
            }
        }

        //swipe
        val swipeDetector = SwipeDetector()

        tableLayout2.setOnTouchListener(swipeDetector)
        tableLayout2.setOnClickListener{
            if(swipeDetector.swipeDetected()){
                if(swipeDetector.action == SwipeDetector.Action.LR){
                    Log.e("SWIPE :: ", "모르겠어요.")
                    this.dismiss()
                }else if(swipeDetector.action == SwipeDetector.Action.RL){
                    //외웠어요 +1 하기
                    word.memorize()
                    Log.e("SWIPE :: ", "외웠어요.")
                    this.dismiss()
                }
            }else{
                Log.e("SWIPE :: ", "터치.")
                // TODO 진도 팝업 호출

            }
        }

    }
}