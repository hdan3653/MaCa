package ewha.appsolute.maca

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_vocacard.view.*

class VocaCardViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer{
    fun bindItem(data : Word, context: Context) {
        val main = MainActivity()
        containerView.voca.text = data.voca //수정함
        containerView.setOnClickListener {
            main.vocaCardPopup(context)
        }
    }
}
