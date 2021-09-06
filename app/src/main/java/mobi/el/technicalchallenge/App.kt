package mobi.el.technicalchallenge

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import mobi.el.technicalchallenge.repository.datasource.offlinedatasource.ServiceLocator

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ServiceLocator.initializeDatabase(this)
    }
}