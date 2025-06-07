package com.oscar.rickandmorty_kotlin.commons

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface BaseUseCase<In, Out> {
    suspend fun execute(input: In) = process(input).flowOn(Dispatchers.IO)

    abstract fun process(request: In): Flow<Out>
}