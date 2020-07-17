package ewha.appsolute.maca

object AppManager {
    lateinit var database: WordDB
    var wordList = WordList()

    //papago
    const val url = "https://openapi.naver.com/v1/papago/n2mt"
    const val client_id = "JNi1monoSUrGcVc6SNHq"
    const val client_secret = "uAKk1kCBbn"
}
