package com.oscar.rickandmorty_kotlin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.oscar.rickandmorty_kotlin.domain.entity.detail.CharacterDetailEntity
import com.oscar.rickandmorty_kotlin.domain.usecase.DetailCharacterUseCase
import com.oscar.rickandmorty_kotlin.domain.usecase.LocalGetCharacterUseCase
import com.oscar.rickandmorty_kotlin.presentation.detail.DetailCharacterViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailCharacterViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var detailCharactersUseCase: DetailCharacterUseCase
    private lateinit var localGetCharacterUseCase: LocalGetCharacterUseCase
    private lateinit var viewModel: DetailCharacterViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        detailCharactersUseCase = mockk()
        localGetCharacterUseCase = mockk()
        viewModel = DetailCharacterViewModel(detailCharactersUseCase, localGetCharacterUseCase)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchDetailCharacterUpdatesFlowWithRemoteDataWhenIdIsValid() = runBlocking {
        // Given
        val validId = "1"
        val expectedEntity = CharacterDetailEntity(id = validId, name = "Rick Sanchez")
        coEvery { detailCharactersUseCase.execute(validId) } returns flowOf(expectedEntity)

        // When
        viewModel.fetchDetailCharacter(validId)

        // Then
        assertEquals(expectedEntity, viewModel.getDetailFlow.value)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun fetchDetailCharacterUpdatesFlowWithLocalDataWhenIdIsLocal() = runBlocking {
        // Given
        val localId = "local"
        val expectedEntity = CharacterDetailEntity(id = localId, name = "Morty Smith")
        coEvery { localGetCharacterUseCase.execute(localId) } returns flowOf(expectedEntity)

        // When
        viewModel.fetchDetailCharacter(localId)

        // Then
        assertEquals(expectedEntity, viewModel.getDetailFlow.value)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchDetailMovieDoesNotUpdateFlowWhenUseCaseThrowsException() = runBlocking {
        // Given
        val invalidId = "invalid_id"
        coEvery { detailCharactersUseCase.execute(invalidId) } throws IllegalArgumentException("Invalid ID")

        // When
        viewModel.fetchDetailCharacter(invalidId)

        // Then
        assertEquals(CharacterDetailEntity(), viewModel.getDetailFlow.value)
    }

}