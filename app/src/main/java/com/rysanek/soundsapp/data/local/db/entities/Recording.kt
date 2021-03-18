package com.rysanek.soundsapp.data.local.db.entities

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