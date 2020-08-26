package ewha.appsolute.maca

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.wordcard3.*

class WordCardPopup3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wordcard3)

        val manager = AppManager
        val position: Int = intent.getIntExtra("position", 0)

        val db : Int? = manager.dbsize
        val storage : Int? = 0

        dbNum.text="$db 개 중"

        storageNum.text="$storage"

        //닫기
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }

        //터치해서 계속하기
        frame.setOnClickListener{
            val intent = Intent(this, WordCardPopup::class.java)
            intent.putExtra("position",position+1)
            this.startActivity(intent)
        }
        cardview.setOnClickListener {
            val intent = Intent(this, WordCardPopup::class.java)
            intent.putExtra("position",position+1)
            this.startActivity(intent)
        }
    }
}