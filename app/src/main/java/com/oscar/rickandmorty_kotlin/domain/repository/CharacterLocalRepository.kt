package com.oscar.rickandmorty_kotlin.domain.repository

import com.oscar.rickandmorty_kotlin.domain.entity.detail.CharacterDetailEntity
import kotlinx.coroutines.flow.Flow

interface CharacterLocalRepository {
    fun getDetailCharacter(id: String): Flow<CharacterDetailEntity>
}