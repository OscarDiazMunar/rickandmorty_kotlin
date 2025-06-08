package com.oscar.rickandmorty_kotlin

import com.oscar.rickandmorty_kotlin.data.data_remote.networking.CharactersClient
import com.oscar.rickandmorty_kotlin.data.data_remote.networking.model.detail.CharacterDetailDTO
import com.oscar.rickandmorty_kotlin.data.data_remote.networking.model.list.CharactersDTO
import com.oscar.rickandmorty_kotlin.data.data_remote.source.RemoteListCharactersDataSourceImpl
import com.oscar.rickandmorty_kotlin.domain.entity.detail.CharacterDetailEntity
import com.oscar.rickandmorty_kotlin.domain.entity.list.CharacterEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RemoteListCharactersDataSourceImplTest {
    private lateinit var charactersService: CharactersClient
    private lateinit var remoteListCharactersDataSource: RemoteListCharactersDataSourceImpl

    @Before
    fun setUp() {
        charactersService = mockk()
        remoteListCharactersDataSource = RemoteListCharactersDataSourceImpl(charactersService)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getListCharactersEmitsConvertedEntitiesWhenApiReturnsValidData() = runBlocking {
        // Given
        val page = 1
        val apiResponse = listOf(
            CharactersDTO(id = "1", name = "Rick", status = "Alive", image = "image_url", species = "Human")
        )
        val expectedEntities = listOf(
            CharacterEntity(id = "1", name = "Rick", status = "Alive", image = "image_url", species = "Human")
        )
        coEvery { charactersService.getListCharacters(page) } returns apiResponse

        // When
        val result = remoteListCharactersDataSource.getListCharacters(page).toList()

        // Then
        assertEquals(expectedEntities[0].name, result[0][0].name)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getDetailCharacterEmitsConvertedEntityWhenApiReturnsValidData() = runBlocking {
        // Given
        val id = "1"
        val apiResponse = CharacterDetailDTO(
            id = "1", name = "Rick Sanchez", status = "Alive", image = "image_url",
            species = "Human", originName = "Earth", locationName = "Citadel of Ricks"
        )
        val expectedEntity = CharacterDetailEntity(
            id = "1", name = "Rick Sanchez", status = "Alive", image = "image_url",
            species = "Human", originName = "Earth", locationName = "Citadel of Ricks"
        )
        coEvery { charactersService.getDetailCharacter(id) } returns apiResponse

        // When
        val result = remoteListCharactersDataSource.getDetailCharacter(id).toList()

        // Then
        assertEquals(expectedEntity, result.first())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun returnsEmptyListWhenNoCharactersFound() = runBlocking {
        // Given
        coEvery { charactersService.getListCharacters(page = 1) } returns emptyList()

        // When
        val result = remoteListCharactersDataSource.getListCharacters(1).toList()

        // Then
        assertEquals(emptyList<CharacterEntity>(), result[0])
    }
}