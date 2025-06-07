package com.oscar.rickandmorty_kotlin.data.data_remote.networking

import com.oscar.rickandmorty_kotlin.CharactersQuery
import com.oscar.rickandmorty_kotlin.data.data_remote.networking.model.CharactersDTO

interface CharactersClient {
    suspend fun getListCharacters(page: Int): List<CharactersDTO>
}