package ewha.appsolute.maca

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_vocacard.view.*
import java.security.AccessController
import java.security.AccessController.getContext

class VocaCardViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer{
    fun bindItem(data : Word, main:MainActivity) {
        containerView.voca.text = data.voca //수정함
        containerView.setOnClickListener {
            main.vocaCardPopup()
        }
    }
}