package mobi.el.technicalchallenge.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import mobi.el.technicalchallenge.R
import mobi.el.technicalchallenge.gallery.adapter.GalleryAdapter
import mobi.el.technicalchallenge.imagedetails.ImageDetailsFragment
import mobi.el.technicalchallenge.repository.models.GalleryPhoto
import mobi.el.technicalchallenge.repository.models.response.PixabayResponse

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private val galleryViewModel: GalleryViewModel by viewModels()
    private var galleryAdapter: GalleryAdapter? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var pageNumber = 1
    private var onLoadMore = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_gallery, container, false)
        recyclerView = view.findViewById(R.id.recyclerview)
        progressBar = view.findViewById(R.id.progressbar)
        galleryViewModel.getImages(pageNumber)
        galleryViewModel.viewState.asLiveData().observe(viewLifecycleOwner, {
            if (it.isLoading && !onLoadMore) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
                setAdapter(it.pixabayResponse)
            }
        })

        return view
    }

    override fun onResume() {
        super.onResume()
        onLoadMore = false
    }

    private fun setAdapter(pixabayResponse: PixabayResponse?) {
        pixabayResponse?.apply {
            if (galleryAdapter == null) {
                galleryAdapter = GalleryAdapter(requireContext(), pixabayResponse.hits, {
                    navigateToImageDetails(it)
                },
                    {
                        onLoadMore = true
                        galleryViewModel.getImages(++pageNumber)
                    })
                recyclerView.layoutManager =
                    androidx.recyclerview.widget.LinearLayoutManager(activity)
                recyclerView.adapter = galleryAdapter
            } else {
                galleryAdapter?.setData(pixabayResponse.hits)
                if (!onLoadMore) {
                    recyclerView.layoutManager =
                        androidx.recyclerview.widget.LinearLayoutManager(activity)
                    recyclerView.adapter = galleryAdapter
                }
            }
        }
    }

    private fun navigateToImageDetails(photo: GalleryPhoto) {
        val navBundle = Bundle()
        navBundle.putSerializable(ImageDetailsFragment.ARG_GALLERY_PHOTO, photo)
        Navigation.findNavController(requireView()).navigate(
            R.id.action_galleryFragment_to_imageDetailsFragment,
            navBundle
        )
    }
}