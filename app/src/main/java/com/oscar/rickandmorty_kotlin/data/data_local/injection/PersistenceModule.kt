package com.oscar.rickandmorty_kotlin.data.data_local.injection

import android.content.Context
import androidx.room.Room
import com.oscar.rickandmorty_kotlin.data.data_local.db.CharacterDao
import com.oscar.rickandmorty_kotlin.data.data_local.db.CharacterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): CharacterDatabase =
        Room.databaseBuilder(
            context,
            CharacterDatabase::class.java, "my-database"
        ).build()

    @Provides
    fun provideCharacterDao(characterDatabase: CharacterDatabase): CharacterDao = characterDatabase.characterDao()

}