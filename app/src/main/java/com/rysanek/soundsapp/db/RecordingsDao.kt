package com.rysanek.soundsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rysanek.soundsapp.db.entities.Recording

@Dao
interface RecordingsDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecording(recording: Recording)
    
    @Delete
    suspend fun deleteRecording(recording: Recording)
    
    @Query("SELECT * FROM recordings_table ORDER BY id DESC")
    fun getAllRecordings(): LiveData<List<Recording>>
}