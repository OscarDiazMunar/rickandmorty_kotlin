package com.oscar.rickandmorty_kotlin.data.data_local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterDBEntity(
    @PrimaryKey val idImage: String,
    val id: String?,
    val name: String?,
    val status: String?,
    val image: String?,
    val species: String?,
    val originName: String?,
    val locationName: String?
)