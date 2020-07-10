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
            var id = wordlist[i].id
            var voca = wordlist[i].voca
            Log.println(Log.DEBUG, "", "$i\t:\tindex[$i] = $val1\t||\tid=$id\t$voca")
        }
    }

    fun getWordByIndex(i:Int):Word {
        return wordlist[index[i]]
    }
}
