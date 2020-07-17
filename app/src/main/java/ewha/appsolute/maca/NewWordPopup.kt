package ewha.appsolute.maca

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ewha.appsolute.maca.POS.*

/*TODO
    - toggle button 서로 안 꼬이게 만들기
    - 버튼 이벤트 연결
    - 작은 화면에서 어떻게 나오는지 점검
    - 파파고 연결
*/

class NewWordPopup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newwordcard)

        var manager = AppManager

        var meaning=findViewById<TextView>(R.id.meaning)
        var meaning2 = findViewById<TextView>(R.id.meaning2)
        var pos = findViewById<TextView>(R.id.pos)
        var position:Int=intent.getIntExtra("position",0)
        meaning.text = manager.wordList.getWordByIndex(position).primary
        meaning2.text = manager.wordList.getWordByIndex(position).secondary
        var posNum = manager.wordList.getWordByIndex(position).pos
        var posText:String? = null

        when (posNum) {
            NOUN.ordinal -> posText = "N."
            ADJECTIVE.ordinal -> posText = "A."
            VERB.ordinal -> posText = "V."
            ADVERB.ordinal -> posText = "AD."
            PREPOSITION.ordinal -> posText = "PREP."
            IDIOM.ordinal -> posText = "ID."
        }
        pos.text = posText
    }
}

