package com.alis.rickandmorty.data.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alis.rickandmorty.data.db.room.daos.CharacterDao
import com.alis.rickandmorty.data.network.dtos.character.CharacterDto

@Database(entities = [CharacterDto::class], version = 1)
@TypeConverters(Converters::class)
abstract class RoomDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
}