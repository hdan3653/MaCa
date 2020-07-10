package ewha.appsolute.maca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class WordCardPopup(val list: WordList) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wordcard)
    }
}