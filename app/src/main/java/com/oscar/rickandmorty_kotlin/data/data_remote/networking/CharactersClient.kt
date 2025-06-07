package com.oscar.rickandmorty_kotlin.data.data_remote.networking

import com.oscar.rickandmorty_kotlin.data.data_remote.networking.model.detail.CharacterDetailDTO
import com.oscar.rickandmorty_kotlin.data.data_remote.networking.model.list.CharactersDTO

interface CharactersClient {
    suspend fun getListCharacters(page: Int): List<CharactersDTO>

    suspend fun getDetailCharacter(id: String): CharacterDetailDTO
}