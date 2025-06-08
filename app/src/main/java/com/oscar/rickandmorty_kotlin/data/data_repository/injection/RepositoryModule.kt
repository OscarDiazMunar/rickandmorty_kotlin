package com.oscar.rickandmorty_kotlin.data.data_repository.injection

import com.oscar.rickandmorty_kotlin.data.data_repository.repository.GetCharacterLocalRepositoryImpl
import com.oscar.rickandmorty_kotlin.data.data_repository.repository.GetListCharacterRepositoryImpl
import com.oscar.rickandmorty_kotlin.domain.repository.CharacterLocalRepository
import com.oscar.rickandmorty_kotlin.domain.repository.GetListCharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGetListCharacterRepository(getListCharacterRepositoryImpl: GetListCharacterRepositoryImpl): GetListCharacterRepository

    @Binds
    abstract fun bindCharacterLocalRepository(getCharacterLocalRepositoryImpl: GetCharacterLocalRepositoryImpl): CharacterLocalRepository
}