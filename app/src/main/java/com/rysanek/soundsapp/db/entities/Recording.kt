package com.rysanek.soundsapp.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recordings_table")
data class Recording(
        val name: String,
        val duration: Long,
        val fileLocation:String
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
    
}