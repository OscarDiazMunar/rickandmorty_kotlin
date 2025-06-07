package com.oscar.rickandmorty_kotlin.data.data_remote.networking.model.detail

data class CharacterDetailDTO(
    val id: String?,
    val name: String?,
    val status: String?,
    val image: String?,
    val species: String?,
    val originName: String?,
    val locationName: String?
)