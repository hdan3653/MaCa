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

    fun shuffle(isRandom: Boolean) {
        if(isRandom) {
            index.shuffle()
            Log.e("Info ::: ", "Shuffled!")
        } else {
            index.sort()
            Log.e("Info ::: ", "Rearranged!")
        }
    }

    fun addWord(word: Word) {
        wordlist.add(0, word)
        index = ArrayList()
        for (i in 0 until wordlist.lastIndex) {
            index.add(i)
        }
    }

    fun removeWord(i: Int) {
        var dump: Word = getWordByIndex(i)
        wordlist.remove(dump)
        index = ArrayList()
        for (i in 0 until wordlist.lastIndex) {
            index.add(i)
        }
    }

    fun printWordList() {
        for (i in 0..index.lastIndex) {
            var index = index[i]
            var id = wordlist[index].id
            var voca = wordlist[index].voca
            Log.println(Log.DEBUG, "", "$i\t:\tindex[$i] = $index\t||\tid=$id\t$voca")
        }
    }

    fun printWordList(at: Int) {
        var index = index[at]
        var id = wordlist[index].id
        var voca = wordlist[index].voca
        Log.println(Log.DEBUG, "", "$at\t:\tindex[$at] = $index\t||\tid=$id\t$voca")
    }

    fun getWordByIndex(i: Int): Word {
        return wordlist[index[i]]
    }

    fun isExist(voca: String): Boolean {
        wordlist.forEach {
            if(voca == it.voca) {
                return true
            }
        }

        return false
    }
}
