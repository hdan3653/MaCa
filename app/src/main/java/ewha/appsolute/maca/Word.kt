package ewha.appsolute.maca

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.synthetic.main.popup_newword.*
import org.jetbrains.annotations.NotNull

enum class POS{
    NOUN, ADJECTIVE, VERB, ADVERB, PREPOSITION, IDIOM
}

@Entity(tableName = "Word")
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Long
    , @ColumnInfo(name="VOCA") @NotNull var voca: String
    , @ColumnInfo(name="POS") @NotNull var pos: Int
    , @ColumnInfo(name="PRIMARY") @NotNull var primary: String
    , @ColumnInfo(name="SECONDARY") var secondary: String?
    , @ColumnInfo(name="IS_SAVED") @NotNull var isSaved: Boolean = false
    , @ColumnInfo(name="COUNT_MEM") @NotNull var count_mem: Int = 0
    , @ColumnInfo(name="DATE_ADD") @NotNull var date_add: String
    , @ColumnInfo(name="DATE_MEM") var date_mem: String?
) {
    fun memorize() {
        count_mem += 1
        if(count_mem >= AppManager.memorize_count) {
            Log.e("WORD :: ", "Data Moved.")
            this.isSaved = true
            AppManager.storageList.addWord(this)
            AppManager.wordList.removeWord(this)
        }

        //TEST
        AppManager.wordList.printWordList()
        AppManager.storageList.printWordList()
    }
}


