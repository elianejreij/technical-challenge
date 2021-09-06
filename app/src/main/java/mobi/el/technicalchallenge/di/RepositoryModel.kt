package mobi.el.technicalchallenge.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mobi.el.technicalchallenge.repository.IRepository
import mobi.el.technicalchallenge.repository.MainRepository
import mobi.el.technicalchallenge.repository.datasource.DataSource
import mobi.el.technicalchallenge.repository.datasource.offlinedatasource.RoomOfflineDataSource
import mobi.el.technicalchallenge.repository.datasource.onlinedatasource.RetrofitOnlineDataSource
import mobi.el.technicalchallenge.service.RetrofitService

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModel {

    @Provides
    fun provideOnlineDataSource(): DataSource.Online =
        RetrofitOnlineDataSource(provideRetrofitService())

    @Provides
    fun provideOfflineDataSource(): DataSource.Offline = RoomOfflineDataSource()

    @Provides
    fun provideRepository(
        offlineDataSource: DataSource.Offline,
        onlineDataSource: DataSource.Online
    ): IRepository = MainRepository(offlineDataSource, onlineDataSource)

    fun provideRetrofitService(): RetrofitService {
        return RetrofitService.getInstance()
    }
}