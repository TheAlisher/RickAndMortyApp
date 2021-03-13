package com.alis.rickandmorty.data.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alis.rickandmorty.data.local.CharacterDao
import com.alis.rickandmorty.models.character.Character

@Database(entities = [Character::class], version = 2)
@TypeConverters(Converters::class)
abstract class RoomDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
}