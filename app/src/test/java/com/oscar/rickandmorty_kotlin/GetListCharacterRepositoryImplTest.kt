package com.oscar.rickandmorty_kotlin

import com.oscar.rickandmorty_kotlin.data.data_repository.data_source.local.LocalCharacterDataSource
import com.oscar.rickandmorty_kotlin.data.data_repository.data_source.remote.RemoteListCharactersDataSource
import com.oscar.rickandmorty_kotlin.data.data_repository.repository.GetListCharacterRepositoryImpl
import com.oscar.rickandmorty_kotlin.domain.entity.detail.CharacterDetailEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetListCharacterRepositoryImplTest {
    private lateinit var remoteListCharactersDataSource: RemoteListCharactersDataSource
    private lateinit var localCharactersDataSource: LocalCharacterDataSource
    private lateinit var getListCharactersRepository: GetListCharacterRepositoryImpl

    @Before
    fun setUp() {
        remoteListCharactersDataSource = mockk()
        localCharactersDataSource = mockk(relaxed = true)
        getListCharactersRepository = GetListCharacterRepositoryImpl(remoteListCharactersDataSource, localCharactersDataSource)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun returnsCharactersDetailEntityAndSavesToLocal() = runBlocking {
        // Given
        val characterDetailEntity =
            CharacterDetailEntity(id = "1", name = "Rick", species = "Human", status = "Alive", image = "http://example.com/image.png")

        coEvery { remoteListCharactersDataSource.getDetailCharacter("1") } returns flowOf(characterDetailEntity)

        // When
        val result = getListCharactersRepository.getDetailCharacter("1") .toList()

        // Then
        assertEquals(1, result.size)
        assertEquals(characterDetailEntity, result[0])
        coVerify { localCharactersDataSource.addCharacter(characterDetailEntity) }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun returnsEmptyFlowWhenNoCharacterDetailFound() = runBlocking {
        coEvery { remoteListCharactersDataSource.getDetailCharacter("1") } returns flowOf()

        val result = getListCharactersRepository.getDetailCharacter("1").toList()

        assertEquals(emptyList<CharacterDetailEntity>(), result)
    }
}