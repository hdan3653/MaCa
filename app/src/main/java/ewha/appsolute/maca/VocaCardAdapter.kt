package ewha.appsolute.maca

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_vocacard.view.*

class VocaCardAdapter(val list: List<WordList>): RecyclerView.Adapter<VocaCardViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocaCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vocacard,parent,false)
        return VocaCardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: VocaCardViewHolder, position: Int) {
        holder.containerView.voca.text=list[position].voca
    }
}