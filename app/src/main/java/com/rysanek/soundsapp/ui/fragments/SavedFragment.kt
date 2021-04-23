package com.rysanek.soundsapp.ui.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rysanek.soundsapp.R
import com.rysanek.soundsapp.adapters.SavedSoundsAdapter
import com.rysanek.soundsapp.db.entities.Recording
import com.rysanek.soundsapp.databinding.FragmentSavedBinding
import com.rysanek.soundsapp.viewmodels.SoundsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment: Fragment() {
    
    private lateinit var binding: FragmentSavedBinding
    private lateinit var savedAdapter: SavedSoundsAdapter
    private val soundsViewModel: SoundsViewModel by viewModels()
    private var recordingList = listOf<Recording>()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(layoutInflater)
        savedAdapter = SavedSoundsAdapter()
        
        savedAdapter.setOnItemClickListener {
            Log.d("adapter", "uri: $it")
            MediaPlayer.create(requireContext(), it.toUri()).apply {
                start()
            }
        }
        
        soundsViewModel.getAllRecordings().observe(viewLifecycleOwner) {
            savedAdapter.differ.submitList(it)
        }
        
        binding.rvSaved.apply {
            adapter = savedAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        
        return binding.root
    }
}