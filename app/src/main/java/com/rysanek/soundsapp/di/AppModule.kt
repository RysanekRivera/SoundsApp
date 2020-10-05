package com.rysanek.soundsapp.di

import android.content.Context
import androidx.room.Room
import com.rysanek.soundsapp.db.RecordingsDataBase
import com.rysanek.soundsapp.utils.Constants.RECORDINGS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    
    @Singleton
    @Provides
    fun provideRunningDatabase(
            @ApplicationContext context: Context
    ) = Room.databaseBuilder(
            context,
            RecordingsDataBase::class.java,
            RECORDINGS_DATABASE_NAME
    ).build()
    
    @Singleton
    @Provides
    fun provideRecordingDao(dataBase: RecordingsDataBase) = dataBase.getSoundsDao()
}