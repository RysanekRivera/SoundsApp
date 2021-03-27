package com.rysanek.soundsapp.di

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.media.MediaPlayer
import androidx.room.Room
import com.rysanek.soundsapp.db.RecordingsDao
import com.rysanek.soundsapp.db.RecordingsDataBase
import com.rysanek.soundsapp.data.repository.SoundsRepository
import com.rysanek.soundsapp.utils.Constants.RECORDINGS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Singleton
    @Provides
    fun provideAppContext(@ApplicationContext context: Context) = context
    
    @Singleton
    @Provides
    fun provideRunningDatabase(
        context: Context
    ) = Room.databaseBuilder(
        context,
        RecordingsDataBase::class.java,
        RECORDINGS_DATABASE_NAME
    ).build()
    
    @Singleton
    @Provides
    fun provideRecordingDao(dataBase: RecordingsDataBase) = dataBase.getSoundsDao()
    
    @Singleton
    @Provides
    fun provideMediaPlayer() = MediaPlayer()
    
    @Singleton
    @Provides
    fun provideContentValues() = ContentValues()
    
    @Singleton
    @Provides
    fun provideContentResolver(context: Context): ContentResolver = context.contentResolver
    
    @Singleton
    @Provides
    fun provideRepository(dao: RecordingsDao) = SoundsRepository(dao)
}