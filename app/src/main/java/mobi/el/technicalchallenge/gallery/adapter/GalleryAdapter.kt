package mobi.el.technicalchallenge.gallery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mobi.el.technicalchallenge.R
import mobi.el.technicalchallenge.repository.models.GalleryPhoto

class GalleryAdapter(private val context: Context,
                     private val galleryPhotos: ArrayList<GalleryPhoto>,
                     private val onPhotoClicked: (GalleryPhoto) -> Unit,
                     private val loadMore: () -> Unit) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GalleryAdapter.GalleryViewHolder {
        return GalleryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_gallery, parent, false))
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {

        val galleryPhoto = galleryPhotos[position]
        holder.rootRelativeLayout.setOnClickListener{
            onPhotoClicked(galleryPhoto)
        }
        holder.usernameTextView.setText(galleryPhoto.user)
        Glide.with(context).load(galleryPhoto.previewURL).into(holder.imageView)

        if(position == itemCount -1){
            loadMore()
        }
    }

    fun setData(photos: ArrayList<GalleryPhoto>){
        val oldSize = itemCount
        this.galleryPhotos.addAll(photos)
        notifyItemRangeInserted(oldSize, photos.size)
    }

    override fun getItemCount(): Int {
      return galleryPhotos.size
    }

    class GalleryViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val imageView = v.findViewById<ImageView>(R.id.imageview)
        val usernameTextView = v.findViewById<TextView>(R.id.textview_username)
        val rootRelativeLayout = v.findViewById<RelativeLayout>(R.id.relativelayout_root)
    }
}