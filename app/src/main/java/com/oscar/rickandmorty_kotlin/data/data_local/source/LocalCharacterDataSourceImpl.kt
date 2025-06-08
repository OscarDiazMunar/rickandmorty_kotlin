package com.oscar.rickandmorty_kotlin.data.data_local.source

import com.oscar.rickandmorty_kotlin.data.data_local.db.CharacterDBEntity
import com.oscar.rickandmorty_kotlin.data.data_local.db.CharacterDao
import com.oscar.rickandmorty_kotlin.data.data_repository.data_source.local.LocalCharacterDataSource
import com.oscar.rickandmorty_kotlin.domain.entity.detail.CharacterDetailEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalCharacterDataSourceImpl @Inject constructor(
    private val characterDao: CharacterDao
): LocalCharacterDataSource {

    override fun getCharacter(): Flow<CharacterDetailEntity> = flow {
        emit(characterDao.getCharacterById("1"))
    }.map {
        convert(it)
    }

    private fun convert(character: CharacterDBEntity?): CharacterDetailEntity {
        return character?.let {
            CharacterDetailEntity(
                id = it.id,
                name = it.name,
                status = it.status,
                species = it.species,
                locationName = it.locationName,
                originName = it.originName,
                image = it.image,
            )
        } ?: CharacterDetailEntity()
    }

    override suspend fun addCharacter(character: CharacterDetailEntity) =
        characterDao.insertOrUpdateCharacter(
            CharacterDBEntity(
                id = character.id,
                idImage = "1",
                name = character.name,
                status = character.status,
                species = character.species,
                locationName = character.locationName,
                originName = character.originName,
                image = character.image
            )
        )
}