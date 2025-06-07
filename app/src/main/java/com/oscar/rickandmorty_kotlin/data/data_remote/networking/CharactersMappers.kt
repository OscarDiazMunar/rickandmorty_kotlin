package com.oscar.rickandmorty_kotlin.data.data_remote.networking

import com.oscar.rickandmorty_kotlin.CharacterQuery
import com.oscar.rickandmorty_kotlin.CharactersQuery
import com.oscar.rickandmorty_kotlin.data.data_remote.networking.model.detail.CharacterDetailDTO
import com.oscar.rickandmorty_kotlin.data.data_remote.networking.model.list.CharactersDTO

fun CharactersQuery.Result.toCharactersDTO(): CharactersDTO {
    return CharactersDTO(
        id = id,
        name = name,
        status = status,
        image = image,
        species = species
    )
}

fun CharacterQuery.Character.toCharacterDetailDTO(): CharacterDetailDTO {
    return CharacterDetailDTO(
        id = id,
        name = name,
        status = status,
        image = image,
        species = species,
        originName = origin?.name,
        locationName = location?.name
    )
}