package com.rysanek.soundsapp.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.os.storage.StorageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.rysanek.soundsapp.R
import com.rysanek.soundsapp.databinding.FragmentPlayBinding

class PlayFragment: Fragment() {
    
    private var _binding: FragmentPlayBinding? = null
    private val binding get() = _binding!!
    private val selected = mutableListOf<MaterialButton>()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        
        _binding = FragmentPlayBinding.inflate(inflater, container, false)
        
        listOf(binding.btn1, binding.btn2, binding.btn3, binding.btn4, binding.btn5, binding.btn6, binding.btn7, binding.btn8, binding.btn9).onEach { button ->
            
            button.setOnClickListener {
                if (!selected.isNullOrEmpty()) {
                    if (!selected.contains(button)) {
                        selected[0].setTextColor(resources.getColor(R.color.white, resources.newTheme()))
                        selected.clear()
                    }
                }
                selected.add(button)
                button.setTextColor(resources.getColor(R.color.yellow, resources.newTheme()))
            }
            
            button.setOnLongClickListener {
                val builder = AlertDialog.Builder(it.context)
                builder.setMessage("Select Sound File")
                    .setView(R.layout.file_dialog)
                    .setPositiveButton("Done") { dialog, id ->
            
                    }
                    .setNegativeButton("Cancel") { dialog, id ->
            
                    }
    
                builder.create().show()
                true
            }
        }
        
        return binding.root
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}