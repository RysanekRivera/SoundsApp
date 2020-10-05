package com.rysanek.soundsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rysanek.soundsapp.R
import com.rysanek.soundsapp.data.Recording
import kotlinx.android.synthetic.main.single_saved_sound.view.*
import java.util.concurrent.TimeUnit

class SavedSoundsAdapter(
        val recordingList: List<Recording>
): RecyclerView.Adapter<SavedSoundsAdapter.SavedSoundViewHolder>() {
    
    inner class SavedSoundViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedSoundViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_saved_sound, parent, false)
        return SavedSoundViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: SavedSoundsAdapter.SavedSoundViewHolder, position: Int) {
        val addedRecording = recordingList[position]
        holder.itemView.apply {   
            tvName.text = addedRecording.name
            val seconds = TimeUnit.MILLISECONDS.toSeconds(addedRecording.duration).toString()
            tvDuration.text = "$seconds secs"
        }
    }
    
    override fun getItemCount() = recordingList.size
    
}