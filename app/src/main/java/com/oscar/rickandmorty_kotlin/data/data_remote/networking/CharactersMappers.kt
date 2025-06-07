package com.oscar.rickandmorty_kotlin.data.data_remote.networking

import com.oscar.rickandmorty_kotlin.CharactersQuery
import com.oscar.rickandmorty_kotlin.data.data_remote.networking.model.CharactersDTO

fun CharactersQuery.Result.toCharactersDTO(): CharactersDTO {
    return CharactersDTO(
        id = id,
        name = name,
        status = status,
        image = image,
        species = species
    )
}