package com.oscar.rickandmorty_kotlin.data.data_local.injection

import com.oscar.rickandmorty_kotlin.data.data_local.source.LocalCharacterDataSourceImpl
import com.oscar.rickandmorty_kotlin.data.data_repository.data_source.local.LocalCharacterDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindLocalCharacterDataSourceImpl(localCharacterDataSourceImpl: LocalCharacterDataSourceImpl): LocalCharacterDataSource

}