package ve.develop.shiftcft

import android.content.Context
import androidx.room.Room
import com.bignerdranch.android.photogallery.api.NetworkApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import ve.develop.shiftcft.database.Database
import ve.develop.shiftcft.model.CardDetail
import ve.develop.shiftcft.model.RequestAttempt

private const val DATABASE_NAME = "database_table"

class Repository private constructor(
    context: Context
) {

    private val database: Database = Room
        .databaseBuilder(
            context.applicationContext,
            Database::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
        .build()

    suspend fun addAttempt(attempt: RequestAttempt) {
        database.detailDao().addAttempt(attempt)
    }

    fun getAttemptList(): Flow<List<RequestAttempt>> = database.detailDao().getListOfAttempts()

    private val networkApi: NetworkApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        networkApi = retrofit.create()
    }

    suspend fun fetchCardDetails(cardNumber: String): CardDetail =
        networkApi.fetchCardDetail(cardNumber)

    companion object {
        private var INSTANCE: Repository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = Repository(context)
            }
        }

        fun get(): Repository {
            return INSTANCE
                ?: throw IllegalStateException("Repository must be initialized")
        }
    }
}