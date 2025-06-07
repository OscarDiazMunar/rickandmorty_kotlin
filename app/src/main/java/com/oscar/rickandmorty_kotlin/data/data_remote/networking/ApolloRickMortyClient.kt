package com.oscar.rickandmorty_kotlin.data.data_remote.networking

import com.apollographql.apollo3.ApolloClient
import com.oscar.rickandmorty_kotlin.CharactersQuery
import com.oscar.rickandmorty_kotlin.data.data_remote.networking.model.CharactersDTO

class ApolloRickMortyClient(
    private val apolloClient: ApolloClient
): CharactersClient {
    override suspend fun getListCharacters(page: Int): List<CharactersDTO> {
        return apolloClient
            .query(CharactersQuery(page ?: 1))
            .execute()
            .data?.characters?.results?.map { it!!.toCharactersDTO() } ?: emptyList()
    }
}