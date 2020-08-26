package ewha.appsolute.maca

object AppManager {
    lateinit var database: WordDB
    var wordList = WordList()
    var storageList = WordList()

    const val memorize_count = 10

    //main activity
    var selectionMode: Boolean = false // true:삭제 / false:기본
    var selectionCount: Int = 0
    var selected = ArrayList<Int>()

    //papago
    const val url = "https://openapi.naver.com/v1/papago/n2mt"
    const val client_id = "JNi1monoSUrGcVc6SNHq"
    const val client_secret = "uAKk1kCBbn"

    //WordCard3
    var dbsize: Int = wordList.getItemCount()
}
