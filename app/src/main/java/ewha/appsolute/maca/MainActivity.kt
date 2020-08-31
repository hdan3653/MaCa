package ewha.appsolute.maca

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

/*
TODO
    - 스와이프 애니메이션
    - 보관함 갈 때 인텐트 효과 뭔가 어색함
    - no data
    - 선택함 개수 프린트 해주기
    - 데이터베이스 업데이트 언제 할 지
 */

class MainActivity : AppCompatActivity() {

    private var isRandom: Boolean = false

    //Recycler View
    private val adapter = VocaCardAdapter(AppManager.wordList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Database Initialize
        AppManager.database = Room.databaseBuilder(
            applicationContext,
            WordDB::class.java, "database-name"
        ).build()

        //WordList Initialize
        AppManager.wordList.init(0)
        AppManager.wordList.printWordList()

        //WordList Initialize
        AppManager.storageList.init(1)
        AppManager.storageList.addWord(
            Word(12938, "Testing", POS.NOUN.ordinal
                , "테스트", "테에에에에스으으으으트으으으"
                , true, 13, "2010-10-30", "2020-02-04")
        )

        AppManager.storageList.printWordList()

//        val count = wordList.size
//        Log.e("LIST COUNT", "$count data.")

        vocaCardView.adapter = adapter
        vocaCardView.layoutManager = GridLayoutManager(this, 2)

        //Sort Button
        btn_sort.setOnClickListener {
            isRandom = !isRandom
            AppManager.wordList.shuffle(isRandom)
            (vocaCardView.adapter as VocaCardAdapter).notifyDataSetChanged()
            AppManager.wordList.printWordList()
        }

        //Add Button
        btn_add.setOnClickListener {
            val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val alertDialog = NewWordPopup(this)

            val view = inflater.inflate(R.layout.popup_newword, null)

            alertDialog.setContentView(view)
            alertDialog.show()
            alertDialog.setOnDismissListener{
                adapter.notifyDataSetChanged()
            }
        }

        //Storage Button
        btn_storage.setOnClickListener {
            val intent = Intent(this, StorageActivity::class.java)
            this.startActivity(intent)
        }

        //Delete Button
        btn_delete.setOnClickListener {
            AppManager.selectionMode = true
            setSelectionMode()
        }

        btn_cancel.setOnClickListener {
            AppManager.selectionMode = false
            AppManager.selectionCount = 0
            (vocaCardView.adapter as VocaCardAdapter).notifyDataSetChanged()
            setSelectionMode()
        }

        btn_confirm.setOnClickListener {
            Log.e("Confirm", AppManager.selected.toString())
            AppManager.selected.forEach { index ->
                AppManager.wordList.printWordList(index)

                Thread {
                    val dump: Word = AppManager.wordList.getWord(index) as Word
                    AppManager.database.wordDao().delete(dump.voca)
                    AppManager.wordList.removeWord(index)
                }.start()
            }

            AppManager.selectionMode = false
            AppManager.selectionCount = 0
            (vocaCardView.adapter as VocaCardAdapter).notifyDataSetChanged()
            setSelectionMode()
        }
    }

    private fun setSelectionMode() {
        if(AppManager.selectionMode) {
            //Delete
            btn_sort.visibility = View.GONE
            btn_add.visibility = View.GONE
            btn_delete.visibility = View.INVISIBLE
            btn_storage.visibility = View.INVISIBLE

            btn_cancel.visibility = View.VISIBLE
            btn_confirm.visibility = View.VISIBLE

            text_selection.text = String.format(getString(R.string.text_selection), 0)
            text_selection.visibility = View.VISIBLE
        } else {
            //Normal
            btn_sort.visibility = View.VISIBLE
            btn_add.visibility = View.VISIBLE
            btn_delete.visibility = View.VISIBLE
            btn_storage.visibility = View.VISIBLE

            btn_cancel.visibility = View.GONE
            btn_confirm.visibility = View.GONE

            text_selection.visibility = View.INVISIBLE
        }
    }
}
