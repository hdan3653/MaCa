package ewha.appsolute.maca

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import ewha.appsolute.maca.AppManager.storageList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btn_cancel
import kotlinx.android.synthetic.main.activity_main.btn_confirm
import kotlinx.android.synthetic.main.activity_main.btn_delete
import kotlinx.android.synthetic.main.activity_main.btn_sort
import kotlinx.android.synthetic.main.activity_main.text_selection
import kotlinx.android.synthetic.main.activity_main.vocaCardView
import kotlinx.android.synthetic.main.activity_storage.*

class StorageActivity : AppCompatActivity() {

    private var isRandom: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        //WordList Initialize
        storageList.init()
        storageList.printWordList()

        //Recycler View
        val adapter = StorageCardAdapter(storageList, this)

        storageCardView.adapter = adapter
        storageCardView.layoutManager = GridLayoutManager(this, 2)

        //Sort Button
        btn_sort.setOnClickListener {
            isRandom = !isRandom
           storageList.shuffle(isRandom)
            (storageCardView.adapter as StorageCardAdapter).notifyDataSetChanged()
            storageList.printWordList()
        }

        //Home Button
        btn_home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
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
            (vocaCardView.adapter as StorageCardAdapter).notifyDataSetChanged()
            setSelectionMode()
        }

        btn_confirm.setOnClickListener {
            Log.e("Confirm", AppManager.selected.toString())
            AppManager.selected.forEach { index ->
                storageList.printWordList(index)

                Thread {
                    val dump: Word = AppManager.storageList.getWord(index) as Word
                    AppManager.database.wordDao().delete(dump.voca)
                    storageList.removeWord(index)
                }.start()
            }

            AppManager.selectionMode = false
            AppManager.selectionCount = 0
            (vocaCardView.adapter as StorageCardAdapter).notifyDataSetChanged()
            setSelectionMode()
        }

    }

    private fun setSelectionMode() {
        if(AppManager.selectionMode) {
            //Delete
            btn_sort.visibility = View.GONE
            btn_delete.visibility = View.INVISIBLE
            btn_storage.visibility = View.INVISIBLE

            btn_cancel.visibility = View.VISIBLE
            btn_confirm.visibility = View.VISIBLE

            text_selection.text = String.format(getString(R.string.text_selection), 0)
            text_selection.visibility = View.VISIBLE
        } else {
            //Normal
            btn_sort.visibility = View.VISIBLE
            btn_delete.visibility = View.VISIBLE
            btn_storage.visibility = View.VISIBLE

            btn_cancel.visibility = View.GONE
            btn_confirm.visibility = View.GONE

            text_selection.visibility = View.INVISIBLE
        }
    }
}
