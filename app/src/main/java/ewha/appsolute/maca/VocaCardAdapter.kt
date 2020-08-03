package ewha.appsolute.maca

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//list 데이터 타입 수정함

class VocaCardAdapter(val list: WordList, val context:Context): RecyclerView.Adapter<VocaCardViewHolder>(){

    lateinit var viewHolder: VocaCardViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocaCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vocacard, parent,false)
        viewHolder = VocaCardViewHolder(view, context)
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
