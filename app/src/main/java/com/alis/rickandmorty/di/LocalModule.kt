package com.alis.rickandmorty.di

import android.content.Context
import com.alis.rickandmorty.data.db.room.RoomClient
import com.alis.rickandmorty.data.db.room.RoomDatabase
import com.alis.rickandmorty.data.db.room.daos.CharacterDao
import com.alis.rickandmorty.datastore.DataStorePreferences
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

    @Singleton
    @Provides
    fun provideDataStorePreferences(
        @ApplicationContext context: Context
    ) = DataStorePreferences(context)
}