package com.oscar.rickandmorty_kotlin.data.data_remote.injection

import com.apollographql.apollo3.ApolloClient
import com.oscar.rickandmorty_kotlin.commons.Constants
import com.oscar.rickandmorty_kotlin.data.data_remote.networking.ApolloRickMortyClient
import com.oscar.rickandmorty_kotlin.data.data_remote.networking.CharactersClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(Constants.urlBase)
            .build()
    }

    @Provides
    @Singleton
    fun provideApolloRickMortyClient(apolloClient: ApolloClient): CharactersClient {
        return ApolloRickMortyClient(apolloClient)
    }
}