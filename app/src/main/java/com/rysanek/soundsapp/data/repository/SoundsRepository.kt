package com.rysanek.soundsapp.data.repository

import com.rysanek.soundsapp.data.local.db.RecordingsDao
import com.rysanek.soundsapp.data.local.db.entities.Recording

class SoundsRepository(
    private val dao: RecordingsDao
) {
    suspend fun insertRecording(recording:Recording) = dao.insertRecording(recording)
    
    suspend fun deleteRecording(recording: Recording) = dao.deleteRecording(recording)
    
    fun getAllRecordings() = dao.getAllRecordings()
}