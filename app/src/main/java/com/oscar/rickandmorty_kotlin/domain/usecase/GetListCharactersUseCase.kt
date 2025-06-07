package com.oscar.rickandmorty_kotlin.domain.usecase

import androidx.paging.PagingData
import com.oscar.rickandmorty_kotlin.commons.BaseUseCase
import com.oscar.rickandmorty_kotlin.domain.entity.list.CharacterEntity
import com.oscar.rickandmorty_kotlin.domain.repository.GetListCharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListCharactersUseCase @Inject constructor(
    private val getListCharacterRepository: GetListCharacterRepository
): BaseUseCase<Unit, PagingData<CharacterEntity>> {

    override fun process(request: Unit): Flow<PagingData<CharacterEntity>> =
        getListCharacterRepository.getListCharacters()
}