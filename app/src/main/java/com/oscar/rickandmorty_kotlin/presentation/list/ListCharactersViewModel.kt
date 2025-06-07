package com.oscar.rickandmorty_kotlin.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.oscar.rickandmorty_kotlin.domain.entity.list.CharacterEntity
import com.oscar.rickandmorty_kotlin.domain.usecase.GetListCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListCharactersViewModel @Inject constructor(
    private val getListCharactersUseCase: GetListCharactersUseCase
): ViewModel() {
    private val searchQuery = MutableStateFlow("")

    private val _getListFlow: MutableStateFlow<PagingData<CharacterEntity>> = MutableStateFlow(value = PagingData.empty())
    val getListFlow: MutableStateFlow<PagingData<CharacterEntity>> get() = _getListFlow

    init {
        getListCharacters()
    }

    private fun getListCharacters() {
        viewModelScope.launch {
            searchQuery.
            flatMapLatest { query ->
                getListCharactersUseCase.execute(Unit)
                    .distinctUntilChanged()
                    .map { pagingData ->
                        pagingData.filter { it.name!!.contains(query, ignoreCase = true) }
                    }
                    .cachedIn(viewModelScope)
            }
            .collect{
                _getListFlow.value = it
            }
        }
    }

    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }
}