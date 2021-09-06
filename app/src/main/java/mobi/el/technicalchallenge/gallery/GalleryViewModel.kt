package mobi.el.technicalchallenge.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mobi.el.technicalchallenge.repository.IRepository
import mobi.el.technicalchallenge.repository.datasource.Resource
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(val repositoryInterface: IRepository) : ViewModel() {

    private var job: Job? = null
    private val _galleryViewState: MutableStateFlow<GalleryViewState> =
        MutableStateFlow(value = GalleryViewState(pixabayResponse = null))

    val viewState: StateFlow<GalleryViewState> = _galleryViewState

    fun getImages(page: Int) {
        job?.cancel()
        job = viewModelScope.launch {
            repositoryInterface.getImages(page).collect {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        _galleryViewState.emit(GalleryViewState(false, it.data!!))
                    }
                    Resource.Status.ERROR -> {

                    }
                    Resource.Status.LOADING -> {
                        _galleryViewState.emit(GalleryViewState(true, null))
                    }
                }
            }
        }
    }

}