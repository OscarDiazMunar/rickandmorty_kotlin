package com.oscar.rickandmorty_kotlin.data.data_repository.data_source.local

import com.oscar.rickandmorty_kotlin.domain.entity.detail.CharacterDetailEntity
import kotlinx.coroutines.flow.Flow

interface LocalCharacterDataSource {
    fun getCharacter(): Flow<CharacterDetailEntity>

    suspend fun addCharacter(movie: CharacterDetailEntity)
}