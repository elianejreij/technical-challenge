package mobi.el.technicalchallenge.repository.models.response

import mobi.el.technicalchallenge.repository.models.GalleryPhoto

data class PixabayResponse(val hits: ArrayList<GalleryPhoto>)