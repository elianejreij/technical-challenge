package mobi.el.technicalchallenge.repository.datasource.onlinedatasource

import mobi.el.technicalchallenge.repository.datasource.BaseDataSource
import mobi.el.technicalchallenge.repository.datasource.DataSource
import mobi.el.technicalchallenge.repository.datasource.Resource
import mobi.el.technicalchallenge.repository.models.response.PixabayResponse
import mobi.el.technicalchallenge.service.RetrofitService
import retrofit2.Call
import retrofit2.Response

class RetrofitOnlineDataSource (val retrofitService: RetrofitService)
    : BaseDataSource(), DataSource.Online {

    override suspend fun getImages(key: String, page: Int) : Resource<PixabayResponse> =
        getResult {
            retrofitService.getImages(key, page)
        }

}