package mobi.el.technicalchallenge.service

import mobi.el.technicalchallenge.repository.models.response.PixabayResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET(".")
    suspend fun getImages(@Query("key") key: String,
                  @Query("page") page: Int) : Response<PixabayResponse>

    companion object {
        var BASE_URL = "https://pixabay.com/api/"
        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {

            if (retrofitService == null) {
                var retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)

            }
            return retrofitService!!

        }
    }
}