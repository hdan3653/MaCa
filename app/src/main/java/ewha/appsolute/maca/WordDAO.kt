package ewha.appsolute.maca

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDAO {
    @Query("SELECT * FROM WORD")
    fun getAll(): List<Word>

    @Query("SELECT * FROM WORD WHERE VOCA IN (:voca)")
    fun getWord(voca: String): List<Word>

    @Insert
    fun insert(vararg people: Word)

    @Insert
    fun insertAll(vararg words: Word)

    @Delete
    fun delete(words: Word)
}