package com.itunes.ituneswiki.ui.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.itunes.ituneswiki.R
import com.itunes.ituneswiki.model.ITuneSongs
import com.itunes.ituneswiki.utils.getProgressDrawable
import com.itunes.ituneswiki.utils.loadImage

@RequiresApi(Build.VERSION_CODES.M)
class SongsAdapter(private var iTuneSongsList: List<ITuneSongs>): RecyclerView.Adapter<SongsAdapter.SongsViewHolder>() {

    fun updateAdapter(iTuneSongsList: List<ITuneSongs>) {

        this.iTuneSongsList = iTuneSongsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_songs, parent, false)
        return SongsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {

        holder.artistNameText.text = iTuneSongsList[position].artistName
        holder.trackNameText.text = iTuneSongsList[position].trackName
        holder.bind(iTuneSongsList[position].trackUrl)
    }

    override fun getItemCount(): Int {

        return iTuneSongsList.size
    }

    class SongsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val albumImage: ImageView = itemView.findViewById(R.id.albumImage)
        private val progressDrawable = getProgressDrawable(itemView.context)
        val artistNameText: TextView = itemView.findViewById(R.id.artistNameText)
        val trackNameText: TextView = itemView.findViewById(R.id.trackNameText)

        fun bind(url: String) {

            albumImage.loadImage(url, progressDrawable)
        }
    }
}