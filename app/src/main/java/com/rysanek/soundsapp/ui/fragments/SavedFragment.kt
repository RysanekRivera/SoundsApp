package com.rysanek.soundsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rysanek.soundsapp.R
import com.rysanek.soundsapp.adapters.SavedSoundsAdapter
import com.rysanek.soundsapp.data.Recording
import kotlinx.android.synthetic.main.fragment_saved.*

class SavedFragment: Fragment(R.layout.fragment_saved) {
    
   
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val exampleRecord1 = Recording("Example Sound1", 10000)
        val exampleRecord2 = Recording("Example Sound2", 5000)
        val exampleRecord3 = Recording("Example Sound3", 7000)
        val soundRecords = listOf(exampleRecord1, exampleRecord2, exampleRecord3)
    
        val adapter = SavedSoundsAdapter(soundRecords)
        rvSaved.adapter = adapter
        rvSaved.layoutManager = LinearLayoutManager(requireContext())
        
        super.onViewCreated(view, savedInstanceState)
    }
    
}