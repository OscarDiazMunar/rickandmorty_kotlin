package com.oscar.rickandmorty_kotlin.data.data_remote.networking

import com.apollographql.apollo3.ApolloClient
import com.oscar.rickandmorty_kotlin.CharacterQuery
import com.oscar.rickandmorty_kotlin.CharactersQuery
import com.oscar.rickandmorty_kotlin.data.data_remote.networking.model.detail.CharacterDetailDTO
import com.oscar.rickandmorty_kotlin.data.data_remote.networking.model.list.CharactersDTO

class ApolloRickMortyClient(
    private val apolloClient: ApolloClient
): CharactersClient {
    override suspend fun getListCharacters(page: Int): List<CharactersDTO> {
        return apolloClient
            .query(CharactersQuery(page ?: 1))
            .execute()
            .data?.characters?.results?.map { it!!.toCharactersDTO() } ?: emptyList()
    }

    override suspend fun getDetailCharacter(id: String): CharacterDetailDTO {
        return apolloClient
            .query(CharacterQuery(id = id))
            .execute()
            .data?.character?.toCharacterDetailDTO()
            ?: throw IllegalArgumentException("Character with id $id not found")
    }
}