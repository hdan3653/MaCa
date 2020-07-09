package ewha.appsolute.maca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    /*
    메인 화면
    - collection view
    - call word card popup
    - call new word popup
    - deletion
    - sorting
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
