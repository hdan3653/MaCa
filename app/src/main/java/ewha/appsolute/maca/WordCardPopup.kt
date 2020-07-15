package ewha.appsolute.maca

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class WordCardPopup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wordcard)

        var manager = AppManager

        var voca=findViewById<TextView>(R.id.voca)
        var position:Int=intent.getIntExtra("position",0)
        voca.text = manager.wordList.getWordByIndex(position).voca
    }
}
