package mobi.el.technicalchallenge.repository.datasource.offlinedatasource

import mobi.el.technicalchallenge.repository.datasource.DataSource
import mobi.el.technicalchallenge.repository.models.User
import java.lang.Exception

class RoomOfflineDataSource : DataSource.Offline {

    override suspend fun login(email: String, password: String): Boolean {
        return ServiceLocator.getDatabase()!!.userDao().loadUser(email, password) != null
    }

    override suspend fun register(email: String, password: String, age: Int): Boolean {
        try {
            return ServiceLocator.getDatabase()!!.userDao()
                .insertUser(User(email, password, age)) != null
        } catch (e: Exception) {
            return false
        }
    }
}