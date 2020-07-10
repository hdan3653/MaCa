package ewha.appsolute.maca

import android.util.Log

class WordList () {
    var wordlist = ArrayList<Word>()
    var index = ArrayList<Int>()

    fun addWord(word:Word) {
        wordlist.add(word)
        var new:Int = wordlist.lastIndex
        index.add(new)
    }

    fun printWordList() {
        for(i in 0..index.lastIndex) {
            var val1 = index[i]
            var val2 = wordlist[i].voca
            Log.println(Log.DEBUG, "", "$i : $val1 || $val2")
        }
    }

    fun getWordByIndex(i:Int):Word {
        return wordlist[index[i]]
    }
}
