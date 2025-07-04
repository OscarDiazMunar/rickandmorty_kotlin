package com.oscar.rickandmorty_kotlin.data.data_repository.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.oscar.rickandmorty_kotlin.data.data_repository.data_source.local.LocalCharacterDataSource
import com.oscar.rickandmorty_kotlin.data.data_repository.data_source.remote.CharactersPagingSource
import com.oscar.rickandmorty_kotlin.data.data_repository.data_source.remote.RemoteListCharactersDataSource
import com.oscar.rickandmorty_kotlin.domain.entity.detail.CharacterDetailEntity
import com.oscar.rickandmorty_kotlin.domain.entity.list.CharacterEntity
import com.oscar.rickandmorty_kotlin.domain.repository.GetListCharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetListCharacterRepositoryImpl @Inject constructor(
    private val remoteListCharactersDataSource: RemoteListCharactersDataSource,
    private val localCharacterDataSource: LocalCharacterDataSource,
): GetListCharacterRepository {
    override fun getListCharacters(): Flow<PagingData<CharacterEntity>> {
        return Pager(
            initialKey = 1,
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = { CharactersPagingSource(remoteListCharactersDataSource) }
        ).flow
    }

    override fun getDetailCharacter(id: String): Flow<CharacterDetailEntity> = remoteListCharactersDataSource.getDetailCharacter(id)
        .onEach { characterDetailEntity ->
            localCharacterDataSource.addCharacter(characterDetailEntity)
        }
}