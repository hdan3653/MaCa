package ewha.appsolute.maca

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.wordcard.*
import kotlinx.android.synthetic.main.wordcard2_storage.meaning3
import kotlinx.android.synthetic.main.wordcard2_storage.meaning4
import kotlinx.android.synthetic.main.wordcard2_storage.pos2
import kotlinx.android.synthetic.main.wordcard2_storage.*

class StorageCardPopup2(context: Context, private val word: Word) : AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wordcard2_storage)

        meaning3.text = word.primary
        meaning4.text = word.secondary

        var posText: String? = null
        when (word.pos) {
            POS.NOUN.ordinal -> posText = "N."
            POS.ADJECTIVE.ordinal -> posText = "A."
            POS.VERB.ordinal -> posText = "V."
            POS.ADVERB.ordinal -> posText = "AD."
            POS.PREPOSITION.ordinal -> posText = "PREP."
            POS.IDIOM.ordinal -> posText = "ID."
        }
        pos2.text = posText

        end_button.setOnClickListener {
        }

        tableLayout.setOnClickListener {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.wordcard, null)
        }
    }
}