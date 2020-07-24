package ewha.appsolute.maca

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.wordcard.*
import kotlinx.android.synthetic.main.wordcard3.*

class WordCardPopup2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newwordcard)

        var manager = AppManager

        var meaning = findViewById<TextView>(R.id.meaning)
        var meaning2 = findViewById<TextView>(R.id.meaning2)
        var pos = findViewById<TextView>(R.id.pos)
        var position: Int = intent.getIntExtra("position", 0)
        meaning.text = manager.wordList.getWordByIndex(position).primary
        meaning2.text = manager.wordList.getWordByIndex(position).secondary
        var posNum = manager.wordList.getWordByIndex(position).pos
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

        //swipe
        val swipeDetector = SwipeDetector()
        var cardview = findViewById<CardView>(R.id.CardView)

        cardview.setOnTouchListener(swipeDetector)
        cardview.setOnClickListener{
            if(swipeDetector.swipeDetected()){
                if(swipeDetector.action.equals(SwipeDetector.Action.LR)){
                    var intent = Intent(this, WordCardPopup::class.java)
                    intent.putExtra("position",position+1)
                    this.startActivity(intent)
                }else if(swipeDetector.action.equals(SwipeDetector.Action.RL)){
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