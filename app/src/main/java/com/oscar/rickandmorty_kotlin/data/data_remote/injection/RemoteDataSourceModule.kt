package com.oscar.rickandmorty_kotlin.data.data_remote.injection

import com.oscar.rickandmorty_kotlin.data.data_remote.source.RemoteListCharactersDataSourceImpl
import com.oscar.rickandmorty_kotlin.data.data_repository.data_source.remote.RemoteListCharactersDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun bindRemoteListCharactersDataSource(remoteListCharactersDataSourceImpl: RemoteListCharactersDataSourceImpl): RemoteListCharactersDataSource
}