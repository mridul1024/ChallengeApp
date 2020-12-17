package com.example.challengeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeapp.R
import com.example.challengeapp.room.TuneEntity


/**
 * Adapter for recycler view
 * -Takes in list data from room database and puts them into the viewHolder
 */
class TuneAdapter(
    private var tuneListData: List<TuneEntity>
): RecyclerView.Adapter<TuneAdapter.TuneViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TuneAdapter.TuneViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.tune_view_holder, parent, false) as View
        return TuneViewHolder(view)
    }

    override fun onBindViewHolder(holder: TuneAdapter.TuneViewHolder, position: Int) {
        holder.bindData(tuneListData[position].artistName, tuneListData[position].trackName)
    }

    override fun getItemCount(): Int {
        return tuneListData.size
    }


    /**
     * Class for initializing the viewHolder widgets
     */
    class TuneViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val artistName: TextView = itemView.findViewById(R.id.text_view_artistName)
        private val trackName: TextView = itemView.findViewById(R.id.text_view_track_name)

        fun bindData(artistString: String, trackString: String){
            artistName.text = artistString
            trackName.text = trackString
        }
    }
}