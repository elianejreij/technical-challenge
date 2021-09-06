package mobi.el.technicalchallenge.repository.models

import java.io.Serializable

data class GalleryPhoto(
    val id: String,
    val pageUrl: String,
    val type: String,
    val tags: String,
    val previewURL: String,
    val webformatURL: String,
    val largeImageURL: String,
    val imageSize: String,
    val views: String,
    val downloads: String,
    val likes: String,
    val comments: String,
    val user: String
) : Serializable