package com.oscar.rickandmorty_kotlin.data.data_repository.data_source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.oscar.rickandmorty_kotlin.domain.entity.list.CharacterEntity

class CharactersPagingSource(
    private val remoteListCharactersDataSource: RemoteListCharactersDataSource
): PagingSource<Int, CharacterEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterEntity> {

        return try {

            val currentPage = params.key ?: 1
            var allMovies = emptyList<CharacterEntity>()
            remoteListCharactersDataSource.getListCharacters(page = currentPage).collect {
                allMovies = it
            }

            LoadResult.Page(
                data = allMovies,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (allMovies.isEmpty()) null else currentPage + 1
            )
        }catch (excption: Exception){
            LoadResult.Error(excption)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterEntity>): Int? {
        return state.anchorPosition
    }
}