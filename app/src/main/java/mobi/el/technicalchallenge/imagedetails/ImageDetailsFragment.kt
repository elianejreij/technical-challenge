package mobi.el.technicalchallenge.imagedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import mobi.el.technicalchallenge.R
import mobi.el.technicalchallenge.repository.models.GalleryPhoto

class ImageDetailsFragment : Fragment(), MotionLayout.TransitionListener {

    private var galleryPhoto: GalleryPhoto? = null

    companion object{
        val ARG_GALLERY_PHOTO = "param1"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.layout_image_details, container, false)
        if (requireArguments().containsKey(ARG_GALLERY_PHOTO)) {
            galleryPhoto = requireArguments().getSerializable(ARG_GALLERY_PHOTO) as GalleryPhoto?
        }

        galleryPhoto?.apply {
            var imageView = view.findViewById<ImageView>(R.id.toolbar_image)
            Glide.with(requireActivity()).load(galleryPhoto?.previewURL).into(imageView)
            view.findViewById<TextView>(R.id.textview_uploader_name).text = galleryPhoto?.user
            view.findViewById<TextView>(R.id.textview_size).text = galleryPhoto?.imageSize
            view.findViewById<TextView>(R.id.textview_type).text = galleryPhoto?.type
            view.findViewById<TextView>(R.id.textview_tags).text = galleryPhoto?.tags
            view.findViewById<TextView>(R.id.textview_views).text = galleryPhoto?.views
            view.findViewById<TextView>(R.id.textview_likes).text = galleryPhoto?.likes
            view.findViewById<TextView>(R.id.textview_commments).text = galleryPhoto?.comments
            view.findViewById<TextView>(R.id.textview_downloads).text = galleryPhoto?.downloads
        }
        return view
    }

    override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
        TODO("Not yet implemented")
    }

    override fun onTransitionChange(
        motionLayout: MotionLayout?,
        startId: Int,
        endId: Int,
        progress: Float
    ) {
        TODO("Not yet implemented")
    }

    override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
        TODO("Not yet implemented")
    }

    override fun onTransitionTrigger(
        motionLayout: MotionLayout?,
        triggerId: Int,
        positive: Boolean,
        progress: Float
    ) {
        TODO("Not yet implemented")
    }
}