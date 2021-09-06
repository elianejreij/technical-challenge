package mobi.el.technicalchallenge.repository.datasource

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.delay
import retrofit2.Response
import java.io.IOException

abstract class BaseDataSource {
    private val gBuilder: Gson = GsonBuilder().create()
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = retryIO(times = 3){ call()}

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }

            return error(" ${gBuilder.fromJson(response.errorBody()!!.string(), String::class.java)}")

        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.error(message)
    }

    private suspend fun <T> retryIO(
        times: Int = Int.MAX_VALUE,
        initialDelay: Long = 2500, // 0.1 second
        maxDelay: Long = 15000,    // 1 second
        factor: Double = 2.0,
        block: suspend () -> T): T
    {
        var currentDelay = initialDelay
        repeat(times - 1) {
            try {
                return block()
            } catch (e: IOException) {
                // you can log an error here and/or make a more finer-grained
                // analysis of the cause to see if retry is needed
            }
            delay(currentDelay)
            currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
        }
        return block() // last attempt
    }
}