package com.oscar.rickandmorty_kotlin.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.rickandmorty_kotlin.domain.entity.detail.CharacterDetailEntity
import com.oscar.rickandmorty_kotlin.domain.usecase.DetailCharacterUseCase
import com.oscar.rickandmorty_kotlin.domain.usecase.LocalGetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailCharacterViewModel @Inject constructor(
    private val detailCharacterUseCase: DetailCharacterUseCase,
    private val localGetCharacterUseCase: LocalGetCharacterUseCase
): ViewModel() {
    private val _getDetailFlow: MutableStateFlow<CharacterDetailEntity> = MutableStateFlow(value = CharacterDetailEntity())
    val getDetailFlow: MutableStateFlow<CharacterDetailEntity> get() = _getDetailFlow
    fun fetchDetailCharacter(id: String){
        if (id.equals("local")){
            loadLocalDetailCharacter(id)
        }else {
            loadDetailCharacter(id)
        }
    }

    fun loadDetailCharacter(id: String){
        viewModelScope.launch {
            detailCharacterUseCase.execute(id)
                .collect{
                    _getDetailFlow.value = it
                }
        }
    }

    fun loadLocalDetailCharacter(id: String){
        viewModelScope.launch {
            localGetCharacterUseCase.execute(id)
                .collect{
                    _getDetailFlow.value = it
                }
        }
    }
}