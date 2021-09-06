package mobi.el.technicalchallenge.repository.datasource.offlinedatasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mobi.el.technicalchallenge.repository.models.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertUser(users: User)

    @Query("SELECT * FROM User WHERE email = :userEmail AND password = :userPassword")
    fun loadUser(userEmail: String, userPassword: String): User
}
