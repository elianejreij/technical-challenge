package mobi.el.technicalchallenge.gallery

import mobi.el.technicalchallenge.repository.models.Hits
import mobi.el.technicalchallenge.repository.models.response.PixabayResponse

data class GalleryViewState(val isLoading : Boolean = false, val pixabayResponse: PixabayResponse?)
