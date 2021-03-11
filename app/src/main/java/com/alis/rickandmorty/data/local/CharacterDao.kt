package com.alis.rickandmorty.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alis.rickandmorty.models.character.Character

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: MutableList<Character>)

    @Query("SELECT * FROM character")
    fun getAll(): MutableList<Character>
}