package com.rysanek.soundsapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rysanek.soundsapp.data.local.db.entities.Recording

@Database(
    entities = [Recording::class],
    version = 1,
    exportSchema = false
)
abstract class RecordingsDataBase : RoomDatabase(){
    
    abstract fun getSoundsDao(): RecordingsDao
}