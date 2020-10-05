package com.rysanek.soundsapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recordings_table")
data class Recording(
        val name: String,
        val duration: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
    
}