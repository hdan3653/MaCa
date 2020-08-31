package ewha.appsolute.maca

import androidx.room.*

@Dao
interface WordDAO {
    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    fun getAll(): List<Word>

    @Query("SELECT * FROM WORD WHERE IS_SAVED is 0 ORDER BY ID DESC")
    fun getWordList(): List<Word>

    @Query("SELECT * FROM WORD WHERE IS_SAVED is 1 ORDER BY ID DESC")
    fun getStorageList(): List<Word>

    @Query("SELECT * FROM WORD WHERE VOCA IN (:voca)")
    fun getWord(voca: String): List<Word>

    @Insert
    fun insert(word: Word)

    @Update
    fun update(words: Word)

    @Query("DELETE FROM WORD WHERE VOCA IN (:voca)")
    fun delete(voca: String)
}
