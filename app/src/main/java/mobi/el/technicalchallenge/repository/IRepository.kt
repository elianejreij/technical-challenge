package mobi.el.technicalchallenge.repository

import kotlinx.coroutines.flow.Flow
import mobi.el.technicalchallenge.repository.datasource.DataSource
import mobi.el.technicalchallenge.repository.datasource.Resource
import mobi.el.technicalchallenge.repository.models.response.PixabayResponse

interface IRepository {

    val offlineDataSource: DataSource.Offline
    val onlineDataSource: DataSource.Online

    fun getImages(page: Int): Flow<Resource<PixabayResponse>>
    suspend fun login(email: String, password: String) : Boolean
    suspend fun register(email: String, password: String, age: Int) : Boolean
}