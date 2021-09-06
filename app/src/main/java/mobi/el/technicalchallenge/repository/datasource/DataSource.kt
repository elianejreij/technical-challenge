package mobi.el.technicalchallenge.repository.datasource

import mobi.el.technicalchallenge.repository.models.response.PixabayResponse

interface DataSource {

    interface Offline {
        //login
        suspend fun login(email: String, password: String) : Boolean

        //register
        suspend fun register(email: String, password: String, age: Int) : Boolean
    }

    interface Online {
        suspend fun getImages(key: String, page: Int): Resource<PixabayResponse>
    }
}