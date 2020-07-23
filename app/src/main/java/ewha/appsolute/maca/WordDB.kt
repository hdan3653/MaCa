package ewha.appsolute.maca

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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
                    WordDB::class.java, "weknot_database")
                    .fallbackToDestructiveMigration()
//                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}
