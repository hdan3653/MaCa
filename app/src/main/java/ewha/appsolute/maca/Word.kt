package ewha.appsolute.maca

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.util.*

enum class POS{
    NOUN, ADJECTIVE, VERB, ADVERB, PREPOSITION, IDIOM
}

@Entity(tableName = "Word")
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Long
    , @ColumnInfo(name="VOCA") @NotNull var voca: String
    , @ColumnInfo(name="POS") @NotNull var pos: POS
    , @ColumnInfo(name="PRIMARY") @NotNull var primary: String
    , @ColumnInfo(name="SECONDARY") var secondary: String
    , @ColumnInfo(name="IS_SAVED") @NotNull var isSaved: Boolean = false
    , @ColumnInfo(name="COUNT_MEM") @NotNull var count_mem: Int = 0
    , @ColumnInfo(name="DATE_ADD") @NotNull var date_add: Date
    , @ColumnInfo(name="DATE_MEM") var date_mem: Date
)

