package ewha.appsolute.maca

import android.content.Intent
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


        var cardView = findViewById<CardView>(R.id.CardView)
        cardView.setOnClickListener {
            val main = MainActivity()
            main.newCardPopup(this,position)
        }
    }
}
