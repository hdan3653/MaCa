package ewha.appsolute.maca

import android.util.Log
import java.lang.Exception
import java.lang.IndexOutOfBoundsException

class WordList {
    private var wordList = ArrayList<Word>()
    private var index = ArrayList<Int>()

    fun init() {
        if (wordList.isEmpty()) {
            Thread {
                wordList = AppManager.database.wordDao().getAll() as ArrayList<Word>
                for (i in 0 until wordList.lastIndex) {
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
        wordList.add(0, word)
        index = ArrayList()
        for (i in 0 until wordList.lastIndex) {
            index.add(i)
        }
    }

    fun removeWord(i: Int) {
        val dump: Word = getWord(i) as Word
        wordList.remove(dump)
        index = ArrayList()
        for (j in 0 until wordList.lastIndex) {
            index.add(j)
        }
    }

    fun printWordList() {
        for (i in 0..index.lastIndex) {
            val index = index[i]
            val id = wordList[index].id
            val voca = wordList[index].voca
            val count = wordList[index].count_mem
            Log.println(Log.ERROR, "", "$i\t:\tindex[$i] = $index\t||\t$count\t||\tid=$id\t$voca")
        }
    }

    fun printWordList(at: Int) {
        val index = index[at]
        val id = wordList[index].id
        val voca = wordList[index].voca
        val count = wordList[index].count_mem
        Log.println(Log.ERROR, "", "$at\t:\tindex[$at] = $index\t||\t$count\t||\tid=$id\t$voca")
    }

    fun getWord(i: Int): Word? {
        return try {
            wordList[index[i]]
        } catch (e: IndexOutOfBoundsException) {
            null
        }
    }

    fun getWord(s: String): Word? {
        var word: Word? = null
        try {
            for(i in 0..wordList.size) {
                if(wordList[index[i]].voca == s) {
                    word = wordList[index[i]]
                    break
                }
            }
        } catch (e: Exception) {
            word = null
        }

        return word
    }

    fun isExist(voca: String): Boolean {
        wordList.forEach {
            if(voca == it.voca) {
                return true
            }
        }

        return false
    }

    fun getIndex(s: String): Int {
        var i: Int = -1
        for(j in 0..wordList.size) {
            if(wordList[index[j]].voca == s) {
                i = j
                break
            }
        }
        return i
    }

}
