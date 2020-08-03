package ewha.appsolute.maca

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.wordcard.editbutton

class WordCardPopup2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wordcard2)

        var manager = AppManager

        var meaning = findViewById<TextView>(R.id.meaning)
        var meaning2 = findViewById<TextView>(R.id.meaning2)
        var pos = findViewById<TextView>(R.id.pos)
        var position: Int = intent.getIntExtra("position", 0)
        var word: Word = manager.wordList.getWord(position) as Word
        meaning.text = word.primary
        meaning2.text = word.secondary
        var posNum = word.pos
        var posText: String? = null

        when (posNum) {
            POS.NOUN.ordinal -> posText = "N."
            POS.ADJECTIVE.ordinal -> posText = "A."
            POS.VERB.ordinal -> posText = "V."
            POS.ADVERB.ordinal -> posText = "AD."
            POS.PREPOSITION.ordinal -> posText = "PREP."
            POS.IDIOM.ordinal -> posText = "ID."
        }
        pos.text = posText

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

        //swipe
        val swipeDetector = SwipeDetector()
        var cardview = findViewById<CardView>(R.id.CardView)

        cardview.setOnTouchListener(swipeDetector)
        cardview.setOnClickListener{
            if(swipeDetector.swipeDetected()){
                if(swipeDetector.action == SwipeDetector.Action.LR){
                    var intent = Intent(this, WordCardPopup::class.java)
                    intent.putExtra("position",position+1)
                    this.startActivity(intent)
                }else if(swipeDetector.action == SwipeDetector.Action.RL){
                    //외웠어요 +1 하기
                    var intent = Intent(this, WordCardPopup::class.java)
                    intent.putExtra("position",position+1)
                    this.startActivity(intent)
                }
            }else{
                var intent = Intent(this, WordCardPopup3::class.java)
                intent.putExtra("position", position)
                this.startActivity(intent)
            }
        }
    }
}