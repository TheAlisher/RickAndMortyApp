package com.alis.rickandmorty.di

import android.content.Context
import com.alis.rickandmorty.data.db.RoomClient
import com.alis.rickandmorty.data.db.RoomDatabase
import com.alis.rickandmorty.data.local.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = RoomClient().provideRoom(context)

    @Singleton
    @Provides
    fun provideCharacterDao(roomDatabase: RoomDatabase): CharacterDao {
        return RoomClient().provideCharacterDao(roomDatabase)
    }
}