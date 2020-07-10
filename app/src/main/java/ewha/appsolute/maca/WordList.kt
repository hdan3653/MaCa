package ewha.appsolute.maca

class WordList (val voca: String, meaning: String, part: String, correct: Int) {
    lateinit var list: ArrayList<Word>
    lateinit var index: List<Int>
}
