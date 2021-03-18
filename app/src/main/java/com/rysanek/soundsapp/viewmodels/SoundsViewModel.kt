package com.rysanek.soundsapp.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rysanek.soundsapp.data.local.db.entities.Recording
import com.rysanek.soundsapp.data.repository.SoundsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SoundsViewModel @Inject constructor(
    private val repository: SoundsRepository
    ): ViewModel() {
    
    fun insertToDatabase(recording: Recording) = viewModelScope.launch {
        repository.insertRecording(recording)
    }
    
    fun deleteFromDatabase(recording: Recording) = viewModelScope.launch {
        repository.deleteRecording(recording)
    }
    
    fun getAllRecordings() = repository.getAllRecordings()
}