package com.oscar.rickandmorty_kotlin.domain.entity.detail

data class CharacterDetailEntity(
    val id: String? = "",
    val name: String? = "",
    val status: String? = "",
    val image: String? = "",
    val species: String? = "",
    val originName: String? = "",
    val locationName: String? = ""
)