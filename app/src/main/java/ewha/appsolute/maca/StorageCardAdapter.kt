package ewha.appsolute.maca

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class StorageCardAdapter(private val list: WordList, val context: Context) :
    RecyclerView.Adapter<StorageCardViewHolder>() {

    private lateinit var viewHolder: StorageCardViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vocacard, parent, false)
        viewHolder = StorageCardViewHolder(view, context)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.getItemCount()
    }

    override fun onBindViewHolder(holder: StorageCardViewHolder, position: Int) {
        holder.containerView.setBackgroundColor(context.getColor(R.color.color_white_grey))
        holder.textView.text = AppManager.storageList.getWord(position)?.voca
    }
}