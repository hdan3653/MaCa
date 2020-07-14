package ewha.appsolute.maca

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

//list 데이터 타입 수정함

class VocaCardAdapter(val list: WordList, val context:Context): RecyclerView.Adapter<VocaCardViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocaCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vocacard,parent,false)
        return VocaCardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.getItemCount()
    }

    override fun onBindViewHolder(holder: VocaCardViewHolder, position: Int) {
        holder.bindItem(list.getWordByIndex(position), context)
    }
}
