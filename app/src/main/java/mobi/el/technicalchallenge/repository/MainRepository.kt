package mobi.el.technicalchallenge.repository

import kotlinx.coroutines.flow.Flow
import mobi.el.technicalchallenge.repository.datasource.DataSource
import mobi.el.technicalchallenge.repository.datasource.Resource
import mobi.el.technicalchallenge.repository.helpers.performGetOperation
import mobi.el.technicalchallenge.repository.models.response.PixabayResponse
import java.lang.Exception

class MainRepository(
    override val offlineDataSource: DataSource.Offline,
    override val onlineDataSource: DataSource.Online
) : IRepository {

    override fun getImages(page: Int): Flow<Resource<PixabayResponse>> = performGetOperation(
        databaseQuery = null, networkCall = {
            onlineDataSource.getImages("23243871-13123ee4d76425683ed5fd584", page)
        }, null
    )

    override suspend fun login(email: String, password: String): Boolean {
        return offlineDataSource.login(email, password)
    }

    override suspend fun register(email: String, password: String, age: Int): Boolean {
            return offlineDataSource.register(email, password, age)
    }
}