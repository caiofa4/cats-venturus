package caiofa.com.catsventurus.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import caiofa.com.catsventurus.Entities.Cat
import caiofa.com.catsventurus.R
import caiofa.com.catsventurus.ViewHolder.CatViewHolder

class CatListAdapter (val catList: List<Cat>) : RecyclerView.Adapter<CatViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from( context )
        val view = inflater.inflate(R.layout.layout_cat_grid, parent, false)

        return CatViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return catList.count()
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {

        val cat = catList[position]
        holder.bindData(cat)

    }
}