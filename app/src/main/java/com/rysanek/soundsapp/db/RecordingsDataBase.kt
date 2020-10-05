package com.rysanek.soundsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rysanek.soundsapp.data.Recording

@Database(
        entities = [Recording::class],
        version = 1
)
abstract class RecordingsDataBase : RoomDatabase(){
    
    abstract fun getSoundsDao(): RecordingsDao
}