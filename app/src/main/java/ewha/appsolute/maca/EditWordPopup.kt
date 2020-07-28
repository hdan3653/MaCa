package ewha.appsolute.maca

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle

class EditWordPopup(context: Context) : AlertDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_editword)
    }
}