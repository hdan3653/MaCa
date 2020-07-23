package ewha.appsolute.maca

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.textclassifier.TextSelection
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

class VocaCardViewHolder(override val containerView: View, context: Context)
    : RecyclerView.ViewHolder(containerView), LayoutContainer{

    var textView: TextView = containerView.findViewById(R.id.text_voca)

    init {
        containerView.setOnClickListener {
            if(AppManager.selectionMode) {
                if(containerView.isSelected) {
                    containerView.isSelected = false
                    containerView.setBackgroundColor(context.getColor(R.color.color_white_grey))

                    AppManager.selectionCount-=1
                    AppManager.selected.remove(adapterPosition)
                    Log.e("holder", "selected : " + AppManager.selectionCount.toString())

                } else {
                    containerView.isSelected = true
                    containerView.setBackgroundColor(context.getColor(R.color.color_light_blue))

                    AppManager.selectionCount+=1
                    AppManager.selected.add(adapterPosition)
                    Log.e("holder", "selected : " + AppManager.selectionCount.toString())

//                    textSelection.text = String.format(context.getString(R.string.text_selection), AppManager.selectionCount)
                }

                Log.e("Selection", AppManager.selected.toString())

            } else {
                var intent = Intent(context, WordCardPopup::class.java)
                intent.putExtra("position", adapterPosition)
                context.startActivity(intent)
            }
        }
    }

}
