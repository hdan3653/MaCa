package ewha.appsolute.maca

import android.util.Log

class WordList {
    private var wordlist = ArrayList<Word>()
    private var index = ArrayList<Int>()

    fun init() {
        if (wordlist.isEmpty()) {
            Thread {
                wordlist = AppManager.database.wordDao().getAll() as ArrayList<Word>
                for (i in 0 until wordlist.lastIndex) {
                    index.add(i)
                }
            }.start()
        }
    }

    fun getItemCount(): Int {
        return index.size
    }

    fun shuffle() {
        index.shuffle()
        Log.d("Info ::: ", "Shuffled!")
    }

    fun addWord(word: Word) {
        wordlist.add(word)
        var new: Int = wordlist.lastIndex
        index.add(new)
    }

    fun printWordList() {
        for (i in 0..index.lastIndex) {
            var index = index[i]
            var id = wordlist[index].id
            var voca = wordlist[index].voca
            Log.println(Log.DEBUG, "", "$i\t:\tindex[$i] = $index\t||\tid=$id\t$voca")
        }
    }

    fun getWordByIndex(i: Int): Word {
        return wordlist[index[i]]
    }
}
