package com.oscar.rickandmorty_kotlin.data.data_repository.repository

import com.oscar.rickandmorty_kotlin.data.data_repository.data_source.local.LocalCharacterDataSource
import com.oscar.rickandmorty_kotlin.domain.entity.detail.CharacterDetailEntity
import com.oscar.rickandmorty_kotlin.domain.repository.CharacterLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterLocalRepositoryImpl @Inject constructor(
    private val localCharacterDataSource: LocalCharacterDataSource
): CharacterLocalRepository {

    override fun getDetailCharacter(id: String): Flow<CharacterDetailEntity> = localCharacterDataSource.getCharacter()
}