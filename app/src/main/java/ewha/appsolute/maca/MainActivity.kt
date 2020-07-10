package ewha.appsolute.maca

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_vocacard.*
import java.util.*

class MainActivity : AppCompatActivity() {
    /*
    메인 화면
    - collection view
    - call word card popup
    - call new word popup
    - deletion
    - sorting
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            WordDB::class.java, "database-name"
        ).build()

        var test = Word(0, "apple", POS.NOUN.ordinal
            , "사과", "사과의 긴 뜻을 표현하고자 주저리 주저리\n그리고 어쩌구 저쩌구."
            , false, 0, "2020-07-10", null
        )

        var wordList = WordList()
        wordList.addWord(test)
        wordList.addWord(test)
        wordList.addWord(test)

        wordList.printWordList()

//        val adapter = VocaCardAdapter(wordList)
//
//        vocaCardView.adapter = adapter
//        vocaCardView.layoutManager = GridLayoutManager(this, 2)
    }

    fun vocaCardPopup() {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.wordcard, null)

        val alertDialog = AlertDialog.Builder(this)
            .create()

        alertDialog.setView(view)
        alertDialog.show()
    }
}
