package com.oscar.rickandmorty_kotlin.domain.usecase

import com.oscar.rickandmorty_kotlin.commons.BaseUseCase
import com.oscar.rickandmorty_kotlin.domain.entity.detail.CharacterDetailEntity
import com.oscar.rickandmorty_kotlin.domain.repository.GetListCharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailCharacterUseCase @Inject constructor(
    private val getListCharacterRepository: GetListCharacterRepository
): BaseUseCase<String, CharacterDetailEntity> {

    override fun process(request: String): Flow<CharacterDetailEntity> =
        getListCharacterRepository.getDetailCharacter(id = request)
}