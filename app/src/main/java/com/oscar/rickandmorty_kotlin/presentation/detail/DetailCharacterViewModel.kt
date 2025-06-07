package com.oscar.rickandmorty_kotlin.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.rickandmorty_kotlin.domain.entity.detail.CharacterDetailEntity
import com.oscar.rickandmorty_kotlin.domain.usecase.DetailCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailCharacterViewModel @Inject constructor(
    private val detailCharacterUseCase: DetailCharacterUseCase,
): ViewModel() {
    private val _getDetailFlow: MutableStateFlow<CharacterDetailEntity> = MutableStateFlow(value = CharacterDetailEntity())
    val getDetailFlow: MutableStateFlow<CharacterDetailEntity> get() = _getDetailFlow
    fun fetchDetailMovie(id: String){
        if (id.equals("local")){
        }else {
            loadDetailMovie(id)
        }
    }

    fun loadDetailMovie(id: String){
        viewModelScope.launch {
            detailCharacterUseCase.execute(id)
                .collect{
                    _getDetailFlow.value = it
                }
        }
    }
}