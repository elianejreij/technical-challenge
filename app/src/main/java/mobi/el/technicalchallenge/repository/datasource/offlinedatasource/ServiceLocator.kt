package mobi.el.technicalchallenge.repository.datasource.offlinedatasource

import android.content.Context
import androidx.room.Room

object ServiceLocator {

    private const val DATABASE_NAME = "TECHNICAL_CHALLENGE_DATABASE"
    private var mDatabase: TechnicalChallengeDatabase? = null

    fun initializeDatabase(context: Context) {
        if (mDatabase == null) {
            mDatabase = Room.databaseBuilder(context, TechnicalChallengeDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }

    fun getDatabase(): TechnicalChallengeDatabase? {
        return mDatabase
    }

}