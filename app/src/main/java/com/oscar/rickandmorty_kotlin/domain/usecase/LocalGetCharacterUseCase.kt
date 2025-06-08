package com.oscar.rickandmorty_kotlin.domain.usecase

import com.oscar.rickandmorty_kotlin.commons.BaseUseCase
import com.oscar.rickandmorty_kotlin.domain.entity.detail.CharacterDetailEntity
import com.oscar.rickandmorty_kotlin.domain.repository.CharacterLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalGetCharacterUseCase @Inject constructor(
    private val characterLocalRepository: CharacterLocalRepository
): BaseUseCase<String, CharacterDetailEntity> {

    override fun process(request: String): Flow<CharacterDetailEntity> {
        return characterLocalRepository.getDetailCharacter(request)
    }
}