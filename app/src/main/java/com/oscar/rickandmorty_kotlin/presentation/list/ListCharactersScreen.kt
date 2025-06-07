package com.oscar.rickandmorty_kotlin.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.oscar.rickandmorty_kotlin.R
import com.oscar.rickandmorty_kotlin.commons.NavigationScreen
import com.oscar.rickandmorty_kotlin.domain.entity.list.CharacterEntity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCharactersScreen(
    viewModel: ListCharactersViewModel,
    navController: NavHostController,
) {
    val charactersList: LazyPagingItems<CharacterEntity> = viewModel.getListFlow.collectAsLazyPagingItems()
    var searchQuery by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    actions = {
                        IconButton(onClick = {
                            coroutineScope.launch{
                                navController.popBackStack()
                            }
                        }) {

                        }
                    }
                )
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        viewModel.setSearchQuery(searchQuery)
                                    },
                    placeholder = { Text(stringResource(R.string.search_character)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    singleLine = true
                )
            }
        },
                floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavigationScreen.Detail.route + "/local")
                },
                containerColor = Color.Yellow
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Add Character")
            }
        }
    ) { innerPadding ->

        when(charactersList.loadState.refresh){
            is LoadState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
            is LoadState.Error -> {
                val errorMessage = (charactersList.loadState.refresh as LoadState.Error).error.message ?: "Error"
                Error(errorMessage = errorMessage, Modifier)
            }

            else -> {
                AllCharacters(listCharactersModel = charactersList, innerPadding) { itemId ->
                    navController.navigate(NavigationScreen.Detail.route + "/" + itemId)
                }
            }
        }
    }
}

@Composable
fun AllCharacters(
    listCharactersModel: LazyPagingItems<CharacterEntity>,
    paddingA: PaddingValues,
    onRowClick: (String) -> Unit
) {
    GridCharacter(character = listCharactersModel, paddingA = paddingA, onRowClick)
}

@Composable
fun GridCharacter(
    character: LazyPagingItems<CharacterEntity>,
    paddingA: PaddingValues,
    onRowClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        modifier = Modifier.padding(paddingA))
    {
        items(character.itemCount) { index ->
            val movie = character[index] ?: return@items
            CharacterImage(modifier = Modifier, movie){
                onRowClick(it)
            }
        }
    }
}

@Composable
fun CharacterImage(
    modifier: Modifier,
    character: CharacterEntity,
    onRowClick: (String) -> Unit
) {
    val imageurl = character.image
    Column(
        modifier = Modifier
            .padding(10.dp)
            .clickable { onRowClick(character.id.toString()) }
    ) {
        Card {
            AsyncImage(
                model = imageurl,
                contentDescription = "",
                modifier = Modifier.padding(5.dp)
            )
        }
        Box {
            Text(text = stringResource(R.string.name, character.name.toString()) ?: "Unknown Character", modifier = Modifier.padding(1.dp))
        }
        Box {
            Text(text = stringResource(R.string.status, character.status.toString()) ?: "Unknown Character", modifier = Modifier.padding(1.dp))
        }
        Box {
            Text(text = stringResource(R.string.specie, character.species.toString()) ?: "Unknown Character", modifier = Modifier.padding(1.dp))
        }
    }
}

@Composable
fun Error(errorMessage: String, modifier: Modifier.Companion) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Snackbar {
            Text(text = errorMessage)
        }
    }
}