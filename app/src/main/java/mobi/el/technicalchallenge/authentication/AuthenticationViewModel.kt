package mobi.el.technicalchallenge.authentication

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mobi.el.technicalchallenge.R
import mobi.el.technicalchallenge.gallery.GalleryViewState
import mobi.el.technicalchallenge.repository.IRepository
import mobi.el.technicalchallenge.repository.datasource.Resource
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(val repositoryInterface: IRepository) :
    ViewModel() {

    private val mValidRegistration: MutableLiveData<Boolean> = MutableLiveData()
    private val mValidLogin: MutableLiveData<Boolean> = MutableLiveData()

    fun login( email: String, password: String) {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            var validLogin = repositoryInterface.login(email, password)
            mValidLogin.postValue(validLogin)
        }

    }

    fun register( email: String, password: String, age: Int) {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            var validRegistration = repositoryInterface.register(email, password, age)
            mValidRegistration.postValue(validRegistration)
        }
    }

    fun observerLoginValidity(): LiveData<Boolean> {
        return mValidLogin
    }

    fun observerRegistrationValidity(): LiveData<Boolean> {
        return mValidRegistration
    }
}