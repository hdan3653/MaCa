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

class MainActivity : AppCompatActivity() {

    private var isRandom: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Database Initialize
        AppManager.database = Room.databaseBuilder(
            applicationContext,
            WordDB::class.java, "database-name"
        ).build()

        //WordList Initialize
        AppManager.wordList.init()
        AppManager.wordList.printWordList()

        //Recycler View
        val adapter = VocaCardAdapter(AppManager.wordList, this)

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
