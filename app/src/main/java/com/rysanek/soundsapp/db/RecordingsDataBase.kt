package com.rysanek.soundsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rysanek.soundsapp.db.entities.Recording

@Database(
    entities = [Recording::class],
    version = 1,
    exportSchema = false
)
abstract class RecordingsDataBase : RoomDatabase(){
    
    abstract fun getSoundsDao(): RecordingsDao
}