package ewha.appsolute.maca

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class VocaCardAdapter(private val list: WordList, val context:Context): RecyclerView.Adapter<VocaCardViewHolder>(){

    private lateinit var viewHolder: VocaCardViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocaCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vocacard, parent,false)
        viewHolder = VocaCardViewHolder(view, context, this)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.getItemCount()
    }

    override fun onBindViewHolder(holder: VocaCardViewHolder, position: Int) {
        holder.containerView.setBackgroundColor(context.getColor(R.color.color_white_grey))
        holder.textView.text = AppManager.wordList.getWord(position)?.voca
    }
}
