package com.oscar.rickandmorty_kotlin.data.data_remote.source

import com.oscar.rickandmorty_kotlin.data.data_remote.networking.CharactersClient
import com.oscar.rickandmorty_kotlin.data.data_remote.networking.model.CharactersDTO
import com.oscar.rickandmorty_kotlin.data.data_repository.data_source.remote.RemoteListCharactersDataSource
import com.oscar.rickandmorty_kotlin.domain.entity.list.CharacterEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteListCharactersDataSourceImpl @Inject constructor(
    private val charactersClient: CharactersClient
): RemoteListCharactersDataSource {
    override fun getListCharacters(page: Int): Flow<List<CharacterEntity>> = flow {
        emit(charactersClient.getListCharacters(page))
    }.map { character ->
        convertToCharacterEntity(character)
    }

    private fun convertToCharacterEntity(character: List<CharactersDTO>): List<CharacterEntity> {
        return character.map {item ->
            CharacterEntity(
                id = item.id,
                name = item.name,
                status = item.status,
                image = item.image,
                species = item.species
            )
        }
    }
}