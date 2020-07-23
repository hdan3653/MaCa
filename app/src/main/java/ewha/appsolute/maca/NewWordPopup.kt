package ewha.appsolute.maca

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ToggleButton
import androidx.core.view.get
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.popup_newword.*
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewWordPopup(context: Context) : AlertDialog(context) {

    var checked: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_newword)

        btn_translate.setOnClickListener {
            if (edit_translate.text.isNotBlank()) {
                var req = TranslateRequest(this.context)
                var param = edit_translate.text.toString()
                var stringRequest: StringRequest = req.makeRequest(param
                    , Response.Listener { response ->
                        Log.d("API Request :: ", response.toString())
                        var translatedText = JSONObject(response.toString())
                            .getJSONObject("message")
                            .getJSONObject("result")
                            .getString("translatedText")

                        edit_translated.setText(translatedText)
                    }
                    , Response.ErrorListener { error ->
                        Log.e("API Error :: ", error.toString())
                    }
                )

                req.callRequest(stringRequest)
            }
        }

        for (i in 0 until toggle_group.childCount) {
            val button: ToggleButton = toggle_group[i] as ToggleButton
            button.setOnCheckedChangeListener { _, value ->
                if (value) {
                    checked = i
                    for (j in 0 until toggle_group.childCount) {
                        if (j != i) {
                            var button2: ToggleButton = toggle_group[j] as ToggleButton
                            button2.isChecked = false
                        }
                    }
                }
            }
        }

        btn_cancel.setOnClickListener {
            this.dismiss()
        }

        btn_add.setOnClickListener {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ISO_DATE
            val formatted = current.format(formatter)

            if (validate()) {
                val word = Word(
                    0
                    , edit_translate.text.toString()
                    , getPOS(checked).ordinal
                    , edit_translated.text.toString()
                    , edit_custom.text.toString()
                    , false, 0
                    , formatted, null
                )

                AppManager.wordList.addWord(word)

                Thread {
                    AppManager.database.wordDao().insert(word)
                }.start()

                this.dismiss()

            } else {
                /* TODO 예외처리 */
            }
        }
    }

    private fun getPOS(num: Int): POS {
        return when (num) {
            0 -> POS.ADVERB
            1 -> POS.ADJECTIVE
            2 -> POS.PREPOSITION
            3 -> POS.NOUN
            4 -> POS.IDIOM
            else -> POS.VERB
        }
    }

    private fun validate(): Boolean {
        if (edit_translate.text.isBlank()) {
            return false
        }

        if (edit_translated.text.isBlank()) {
            return false
        }

        if (checked < 0 || checked > 5) {
            return false
        }

        return true
    }
}
