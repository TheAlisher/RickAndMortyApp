package com.alis.rickandmorty.data.db.room

import android.content.Context
import androidx.room.Room
import com.alis.rickandmorty.data.db.room.daos.CharacterDao

class RoomClient {

    fun provideRoom(context: Context) = Room
        .databaseBuilder(context, RoomDatabase::class.java, "boilerplate.db")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    fun provideCharacterDao(roomDatabase: RoomDatabase): CharacterDao = roomDatabase.characterDao()
}