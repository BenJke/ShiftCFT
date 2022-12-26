package ve.develop.shiftcft.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "database_table")
data class RequestAttempt(
    @PrimaryKey(autoGenerate = true) val key: Long,
    val time: Date,
    val status: String
)
