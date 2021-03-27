package com.rysanek.soundsapp.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rysanek.soundsapp.db.entities.Recording
import com.rysanek.soundsapp.databinding.SingleSavedSoundBinding
import java.util.concurrent.TimeUnit

class SavedSoundsAdapter(): RecyclerView.Adapter<SavedSoundsAdapter.SavedSoundViewHolder>() {
    
    private val diffCallback = object: DiffUtil.ItemCallback<Recording>() {
        override fun areItemsTheSame(oldItem: Recording, newItem: Recording): Boolean {
           return oldItem == newItem
        }
    
        override fun areContentsTheSame(oldItem: Recording, newItem: Recording): Boolean {
            return oldItem == newItem
        }
    }
    
    val differ = AsyncListDiffer(this, diffCallback)
    
    class SavedSoundViewHolder(private val binding: SingleSavedSoundBinding): RecyclerView.ViewHolder(binding.root){
        
        fun bind(recording: Recording) {
            binding.tvName.text = recording.name
            val seconds = TimeUnit.MILLISECONDS.toSeconds(recording.duration).toString()
            binding.tvDuration.text = "$seconds secs"
        }
        
    
        companion object{
            fun from(parent: ViewGroup): SavedSoundViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = SingleSavedSoundBinding.inflate(layoutInflater, parent, false)
                return SavedSoundViewHolder(view)
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedSoundViewHolder {
        return SavedSoundViewHolder.from(parent)
    }
    
    override fun onBindViewHolder(holder: SavedSoundViewHolder, position: Int) {
        val recording = differ.currentList[position]
        if (recording != null) {
            holder.bind(recording)
            holder.itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(recording.fileLocation)
                }
            }
        }
    }
    
    private var onItemClickListener:((String) -> Unit)? = null
    fun setOnItemClickListener(listener:(String) -> Unit) {
        onItemClickListener = listener
    }
    
    override fun getItemCount() = differ.currentList.size
    
}