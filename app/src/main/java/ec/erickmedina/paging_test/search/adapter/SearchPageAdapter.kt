package ec.erickmedina.paging_test.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ec.erickmedina.paging_test.R
import ec.erickmedina.paging_test.entity.LastFmResponses
import ec.erickmedina.paging_test.entity.getImage
import ec.erickmedina.paging_test.util.ImageLoader
import kotlinx.android.synthetic.main.item_artist.view.*

class SearchPageAdapter:
    PagedListAdapter<LastFmResponses.Artist, SearchPageAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bindItem(it) }

    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bindItem(artist:LastFmResponses.Artist) {
            ImageLoader.loadInImageView(artist.getImage(), itemView.item_artist_image)
            itemView.item_artist_name.text = artist.name
        }
    }

    class DiffUtilCallback: DiffUtil.ItemCallback<LastFmResponses.Artist>() {
        override fun areItemsTheSame(oldItem: LastFmResponses.Artist, newItem: LastFmResponses.Artist): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: LastFmResponses.Artist, newItem: LastFmResponses.Artist): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.mbid == newItem.mbid
        }
    }
}