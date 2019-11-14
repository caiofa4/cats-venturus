package caiofa.com.catsventurus.ViewHolder

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import caiofa.com.catsventurus.Entities.Cat
import caiofa.com.catsventurus.R
import com.bumptech.glide.Glide

class CatViewHolder (itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {

    private val mImageCat: ImageView = itemView.findViewById(R.id.img_cat)

    fun bindData(cat: Cat){

        Glide
            .with(context)
            .load(cat.image.link)
            .placeholder(R.drawable.ic_android_black_24dp)
            .into(mImageCat);
    }
}