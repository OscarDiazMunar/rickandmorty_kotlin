package com.oscar.rickandmorty_kotlin

import com.oscar.rickandmorty_kotlin.domain.entity.detail.CharacterDetailEntity
import com.oscar.rickandmorty_kotlin.domain.repository.GetListCharacterRepository
import com.oscar.rickandmorty_kotlin.domain.usecase.DetailCharacterUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DetailCharacterUseCaseTest {
    private lateinit var getListCharacterRepository: GetListCharacterRepository
    private lateinit var detailCharacterUseCase: DetailCharacterUseCase

    @Before
    fun setUp() {
        getListCharacterRepository = mockk()
        detailCharacterUseCase = DetailCharacterUseCase(getListCharacterRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun returnsMovieDetailEntity() = runBlocking {
        val movieDetail = CharacterDetailEntity(id = "1", name = "Test Name")
        coEvery { getListCharacterRepository.getDetailCharacter("1") } returns flowOf(movieDetail)

        val result = detailCharacterUseCase.process("1").toList()

        assertEquals(1, result.size)
        assertEquals(movieDetail, result[0])
    }

    @ExperimentalCoroutinesApi
    @Test
    fun returnsEmptyFlowWhenNoMovieDetailFound() = runBlocking {
        coEvery { getListCharacterRepository.getDetailCharacter("1") } returns flowOf()

        val result = detailCharacterUseCase.process("1").toList()

        assertEquals(emptyList<CharacterDetailEntity>(), result)
    }

}