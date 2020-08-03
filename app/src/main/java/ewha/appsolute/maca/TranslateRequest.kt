package ewha.appsolute.maca

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class TranslateRequest(context:Context) {

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun callRequest(request: StringRequest) {
        requestQueue.add(request)
    }

    fun makeRequest(string: String, response: Response.Listener<String>, error: Response.ErrorListener): StringRequest {
        return object: StringRequest(Method.POST, AppManager.url, response, error) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/x-www-form-urlencoded; charset=UTF-8"
                headers["X-Naver-Client-Id"] = AppManager.client_id
                headers["X-Naver-Client-Secret"] = AppManager.client_secret
                return headers
            }

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["source"] = "en"
                params["target"] = "ko"
                params["text"] = string
                return params
            }
        }
    }
}
