package ewha.appsolute.maca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.wordcard.*

class WordCardPopup2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wordcard)

        var position: Int = intent.getIntExtra("position", 0)

        //temp
        text_voca.text = AppManager.wordList.getWordByIndex(position).primary
    }
}