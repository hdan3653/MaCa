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

        var manager = AppManager
        var position: Int = intent.getIntExtra("position", 0)


        var db : Int? = manager.dbsize
        var storage : Int? = 0

        dbNum.text="$db 개 중"

        storageNum.text="$storage"

        //닫기
        backButton.setOnClickListener {
            var intent = Intent(this, StorageActivity::class.java)
            this.startActivity(intent)
        }

        //터치해서 계속하기
        frame.setOnClickListener{
            var intent = Intent(this, WordCardPopup::class.java)
            intent.putExtra("position",position+1)
            this.startActivity(intent)
        }
        cardview.setOnClickListener {
            var intent = Intent(this, WordCardPopup::class.java)
            intent.putExtra("position",position+1)
            this.startActivity(intent)
        }
    }
}