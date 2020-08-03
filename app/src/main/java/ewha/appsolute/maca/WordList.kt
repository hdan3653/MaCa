package ewha.appsolute.maca

import android.util.Log
import java.lang.Exception
import java.lang.IndexOutOfBoundsException

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
        var dump: Word = getWord(i) as Word
        wordlist.remove(dump)
        index = ArrayList()
        for (j in 0 until wordlist.lastIndex) {
            index.add(j)
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
        val index = index[at]
        val id = wordlist[index].id
        val voca = wordlist[index].voca
        val count = wordlist[index].count_mem
        Log.println(Log.ERROR, "", "$at\t:\tindex[$at] = $index\t||\t$count\t||\tid=$id\t$voca")
    }

    fun getWord(i: Int): Word? {
        return try {
            wordlist[index[i]]
        } catch (e: IndexOutOfBoundsException) {
            null
        }
    }

    fun getWord(s: String): Word? {
        var word: Word? = null
        try {
            for(i in 0..wordlist.size) {
                if(wordlist[index[i]].voca == s) {
                    word = wordlist[index[i]]
                    break
                }
            }
        } catch (e: Exception) {
            word = null
        }

        return word
    }

    fun isExist(voca: String): Boolean {
        wordlist.forEach {
            if(voca == it.voca) {
                return true
            }
        }

        return false
    }

    fun getIndex(s: String): Int {
        var i: Int = -1
        for(j in 0..wordlist.size) {
            if(wordlist[index[j]].voca == s) {
                i = j
                break
            }
        }
        return i
    }

}
