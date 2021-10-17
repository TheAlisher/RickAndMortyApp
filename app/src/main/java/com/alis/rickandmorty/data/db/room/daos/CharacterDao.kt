package com.alis.rickandmorty.data.db.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alis.rickandmorty.data.network.dtos.character.CharacterDto

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: MutableList<CharacterDto>)

    @Query("SELECT * FROM characterdto")
    fun getAll(): MutableList<CharacterDto>
}