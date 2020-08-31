package ewha.appsolute.maca

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_storage.*

class StorageActivity : AppCompatActivity() {

    private var isRandom: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        AppManager.storageList.printWordList()

        val count = AppManager.storageList.getItemCount()
        Log.e("LIST COUNT", "$count data.")

        //Recycler View
        val adapter = StorageCardAdapter(AppManager.storageList, this)

        storageCardView.adapter = adapter
        storageCardView.layoutManager = GridLayoutManager(this, 2)

        //Sort Button
        btn_sort.setOnClickListener {
            isRandom = !isRandom
            AppManager.storageList.shuffle(isRandom)
            (storageCardView.adapter as StorageCardAdapter).notifyDataSetChanged()
            AppManager.storageList.printWordList()
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
            (storageCardView.adapter as StorageCardAdapter).notifyDataSetChanged()
            setSelectionMode()
        }

        btn_confirm.setOnClickListener {
            Log.e("Confirm", AppManager.selected.toString())
            AppManager.selected.forEach { index ->
                AppManager.storageList.printWordList(index)

                Thread {
                    val dump: Word = AppManager.storageList.getWord(index) as Word
                    AppManager.database.wordDao().delete(dump.voca)
                    AppManager.storageList.removeWord(index)
                }.start()
            }

            AppManager.selectionMode = false
            AppManager.selectionCount = 0
            (storageCardView.adapter as StorageCardAdapter).notifyDataSetChanged()
            setSelectionMode()
        }

    }

    private fun setSelectionMode() {
        if(AppManager.selectionMode) {
            //Delete
            btn_sort.visibility = View.GONE
            btn_delete.visibility = View.INVISIBLE
            btn_home.visibility = View.INVISIBLE

            btn_cancel.visibility = View.VISIBLE
            btn_confirm.visibility = View.VISIBLE

            text_selection.text = String.format(getString(R.string.text_selection), 0)
            text_selection.visibility = View.VISIBLE
        } else {
            //Normal
            btn_sort.visibility = View.VISIBLE
            btn_delete.visibility = View.VISIBLE
            btn_home.visibility = View.VISIBLE

            btn_cancel.visibility = View.GONE
            btn_confirm.visibility = View.GONE

            text_selection.visibility = View.INVISIBLE
        }
    }
}
