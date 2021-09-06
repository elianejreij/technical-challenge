package mobi.el.technicalchallenge.repository.datasource.offlinedatasource

import androidx.room.Database
import androidx.room.RoomDatabase
import mobi.el.technicalchallenge.repository.datasource.offlinedatasource.dao.UserDao
import mobi.el.technicalchallenge.repository.models.User

@Database(entities = [User::class], version = 1)
abstract class TechnicalChallengeDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}