package ve.develop.shiftcft.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ve.develop.shiftcft.model.RequestAttempt

@Dao
interface DetailDao {

    @Query("SELECT * FROM database_table")
    fun getListOfAttempts(): Flow<List<RequestAttempt>>


    @Insert
    suspend fun addAttempt(attempt: RequestAttempt)
}
