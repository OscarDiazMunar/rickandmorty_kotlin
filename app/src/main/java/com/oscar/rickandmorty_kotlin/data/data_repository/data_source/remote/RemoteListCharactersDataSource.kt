package com.oscar.rickandmorty_kotlin.data.data_repository.data_source.remote

import com.oscar.rickandmorty_kotlin.domain.entity.detail.CharacterDetailEntity
import com.oscar.rickandmorty_kotlin.domain.entity.list.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface RemoteListCharactersDataSource {
    fun getListCharacters(page: Int): Flow<List<CharacterEntity>>

    fun getDetailCharacter(id: String): Flow<CharacterDetailEntity>
}