package com.oscar.rickandmorty_kotlin.domain.repository

import androidx.paging.PagingData
import com.oscar.rickandmorty_kotlin.domain.entity.detail.CharacterDetailEntity
import com.oscar.rickandmorty_kotlin.domain.entity.list.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface GetListCharacterRepository {
    fun getListCharacters(): Flow<PagingData<CharacterEntity>>
    fun getDetailCharacter(id: String): Flow<CharacterDetailEntity>
}