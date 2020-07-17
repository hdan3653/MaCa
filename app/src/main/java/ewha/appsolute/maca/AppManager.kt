package ewha.appsolute.maca

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class AppManager(context: Context) {
    companion object {
        lateinit var database: WordDB
        var wordList = WordList()

        //papago
        const val url = "https://openapi.naver.com/v1/papago/n2mt"
        const val client_id = "JNi1monoSUrGcVc6SNHq"
        const val client_secret = "uAKk1kCBbn"

        @Volatile
        private var INSTANCE: AppManager? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: AppManager(context).also {
                    INSTANCE = it
                }
            }
    }

    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun addToRequestQueue(req: JsonObjectRequest) {
        req.headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
        req.headers.put("X-Naver-Client-Id", client_id)
        req.headers.put("X-Naver-Client-Secret", client_secret)

        requestQueue.add(req)
    }
}