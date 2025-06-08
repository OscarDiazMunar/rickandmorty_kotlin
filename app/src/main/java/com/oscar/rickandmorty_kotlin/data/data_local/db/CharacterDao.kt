package com.oscar.rickandmorty_kotlin.data.data_local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: CharacterDBEntity)

    @Query("SELECT * FROM characters WHERE idImage = :imageId")
    fun getCharacterById(imageId: String): CharacterDBEntity?

    @Query("SELECT * FROM characters")
    fun getAllCharacter(): List<CharacterDBEntity>

    @Update
    fun updateCharacter(character: CharacterDBEntity)

    @Upsert
    fun insertOrUpdateCharacter(character: CharacterDBEntity)
}