package ewha.appsolute.maca

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.popup_newword.*
import org.json.JSONObject

/*TODO
    - toggle button 서로 안 꼬이게 만들기
    - 버튼 이벤트 연결
    - 파파고 연결
*/

class NewWordPopup(context: Context) : AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_newword)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, AppManager.url
            , JSONObject().put("source", "en").put("target", "ko").put("text", "감자")
            , Response.Listener { response ->
                Log.e("API Request :: ", response.toString())
            }
            , Response.ErrorListener { error ->
                Log.e("API Request :: ", error.toString())
            }
        )

        btn_translate.setOnClickListener {
            AppManager.getInstance(context).addToRequestQueue(jsonObjectRequest)
        }
    }
}

