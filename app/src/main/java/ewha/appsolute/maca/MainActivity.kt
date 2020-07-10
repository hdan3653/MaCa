package ewha.appsolute.maca

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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

        var wordList = WordList()
        testData(wordList)                          //TEMP
        wordList.printWordList()

        val adapter = VocaCardAdapter(wordList)

        vocaCardView.adapter = adapter
        vocaCardView.layoutManager = GridLayoutManager(this, 2)
    }

    fun vocaCardPopup() {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.wordcard, null)

        val alertDialog = AlertDialog.Builder(this)
            .create()

        alertDialog.setView(view)
        alertDialog.show()
    }

    private fun testData(wordList: WordList) {
        wordList.addWord(Word(0, "apple", POS.NOUN.ordinal
            , "사과", "사과의 긴 뜻을 표현하고자 주저리 주저리\n그리고 어쩌구 저쩌구."
            , false, 0, "2020-06-10", null
        ))
        wordList.addWord(Word(0, "banana", POS.NOUN.ordinal
            , "바나나", "노랗다"
            , false, 0, "2020-06-10", null
        ))
        wordList.addWord(Word(0, "caramel", POS.NOUN.ordinal
            , "캐러맬", null
            , false, 0, "2020-07-03", null
        ))
        wordList.addWord(Word(0, "dinosaur", POS.NOUN.ordinal
            , "공룡", null
            , false, 0, "2020-07-10", null
        ))
        wordList.addWord(Word(0, "airplane", POS.NOUN.ordinal
            , "비행기", null
            , false, 0, "2020-07-10", null
        ))
        wordList.addWord(Word(0, "phone", POS.NOUN.ordinal
            , "전화", null
            , false, 0, "2020-07-10", null
        ))
        wordList.addWord(Word(0, "aphasia", POS.NOUN.ordinal
            , "실어증", null
            , false, 0, "2020-07-10", null
        ))
        wordList.addWord(Word(0, "cake", POS.NOUN.ordinal
            , "케이크", null
            , false, 0, "2020-07-10", null
        ))
        wordList.addWord(Word(0, "fox", POS.NOUN.ordinal
            , "여우", null
            , false, 0, "2020-07-10", null
        ))
        wordList.addWord(Word(0, "book", POS.NOUN.ordinal
            , "책", null
            , false, 0, "2020-07-10", null
        ))
        wordList.addWord(Word(0, "note", POS.NOUN.ordinal
            , "공책", null
            , false, 0, "2020-07-10", null
        ))
        wordList.addWord(Word(0, "pen", POS.NOUN.ordinal
            , "펜", null
            , false, 0, "2020-07-10", null
        ))
        wordList.addWord(Word(0, "patriarchal", POS.ADJECTIVE.ordinal
            , "가부장적인", null
            , false, 0, "2020-07-10", null
        ))
        wordList.addWord(Word(0, "tie", POS.VERB.ordinal
            , "묶다", "다른 뜻도 있다."
            , false, 0, "2020-07-10", null
        ))
        wordList.addWord(Word(0, "discreetly", POS.ADVERB.ordinal
            , "신중히", null
            , false, 0, "2020-07-10", null
        ))
        wordList.addWord(Word(0, "neath", POS.PREPOSITION.ordinal
            , "아래에", null
            , false, 0, "2020-07-10", null
        ))
        wordList.addWord(Word(0, "take one's place", POS.IDIOM.ordinal
            , "자리를 차지하다", null
            , false, 0, "2020-07-10", null
        ))
    }
}
