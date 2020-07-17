package ewha.appsolute.maca

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Header
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest

/*TODO
    - toggle button 서로 안 꼬이게 만들기
    - 버튼 이벤트 연결
    - 작은 화면에서 어떻게 나오는지 점검
    - 파파고 연결
*/

class NewWordPopup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_newword)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, AppManager.url, null,
            Response.Listener { response ->
                Log.e("API Request :: ", response.toString())

            },
            Response.ErrorListener { error ->
                Log.e("API Request :: ", error.toString())
            }
        )
//        jsonObjectRequest.body.

        AppManager.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
}

