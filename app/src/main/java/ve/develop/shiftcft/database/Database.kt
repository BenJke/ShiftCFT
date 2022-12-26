package ve.develop.shiftcft.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ve.develop.shiftcft.model.RequestAttempt

@Database(entities = [RequestAttempt::class], version = 1, exportSchema = false)
@androidx.room.TypeConverters(TypeConverters::class)
abstract class Database : RoomDatabase() {
    abstract fun detailDao(): DetailDao
}

