package ewha.appsolute.maca

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
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

                }

                Log.e("Selection", AppManager.selected.toString())

            } else {
                val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val view = inflater.inflate(R.layout.wordcard2, null)

                val word = AppManager.wordList.getWord(adapterPosition) as Word

                val alertDialog = WordCardPopup(context, adapterPosition, word)

                alertDialog.setContentView(view)
                alertDialog.show()
                alertDialog.setOnDismissListener{
                    Log.e("Alert Dialog", "WordCardPopup Dismissed.")
                }
            }
        }
    }

}
