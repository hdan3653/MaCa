package ewha.appsolute.maca

import android.content.Context
import androidx.room.*
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1)
abstract class WordDB : RoomDatabase() {
    abstract fun wordDao(): WordDAO

    companion object {

        private var instance: RoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): RoomDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                    RoomDatabase::class.java, "weknot_database")
                    .fallbackToDestructiveMigration()
//                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}

interface WordDAO {
    @Query("SELECT * FROM WORD")
    fun getAll(): WordList

    @Query("SELECT * FROM WORD WHERE VOCA IN (:vocabulary)")
    fun getWord(vocabulary: String): WordList

    @Insert
    fun insert(vararg people: Word)

    @Insert
    fun insertAll(vararg words: Word)

    @Delete
    fun delete(words: Word)
}
