package ewha.appsolute.maca

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.wordcard3.*

class WordCardPopup3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wordcard3)

        backButton.setOnClickListener {
            var intent = Intent(this, StorageActivity::class.java)
            this.startActivity(intent)
        }

        var db : Int? = null
        var storage : Int? = null

        dbNum.text="$db 개 중"
        storageNum.text="$storage"
    }
}