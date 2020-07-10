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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_vocacard.*

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

        val wordList = listOf(
            WordList("voca","단어","N",0) ,
            WordList("voca","단어","N",0) ,
            WordList("voca","단어","N",0) ,
            WordList("voca","단어","N",0) ,
            WordList("voca","단어","N",0) ,
            WordList("voca","단어","N",0) ,
            WordList("voca","단어","N",0)
        )

        val adapter = VocaCardAdapter(wordList)
        vocaCardView.adapter=adapter
        vocaCardView.layoutManager=GridLayoutManager(this,2)
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
