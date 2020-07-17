package ewha.appsolute.maca

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.popup_newword.*
import org.json.JSONObject


/*TODO
    - toggle button 서로 안 꼬이게 만들기
    - 버튼 이벤트 연결
*/

class NewWordPopup(context: Context) : AlertDialog(context) {

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
    }
}

//"message":{
//    "@type":"response"
//    ,"@service":"naverservice.nmt.proxy"
//    ,"@version":"1.0.0"
//    ,"result":{
//        "srcLangType":"en"
//        ,"tarLangType":"ko"
//        ,"translatedText":"감자를"
//        ,"engineType":"N2MT"
//        ,"pivot":null
//    }
//}

