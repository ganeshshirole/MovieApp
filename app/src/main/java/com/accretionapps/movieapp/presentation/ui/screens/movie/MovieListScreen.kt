package com.accretionapps.movieapp.presentation.ui.screens.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.accretionapps.movieapp.domain.model.movies.Movie
import com.accretionapps.movieapp.presentation.ui.components.MovieItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(viewModel: MovieViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleIntents(MovieIntent.LoadMovies)
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Movies List") })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                is MovieState.Loading -> CircularProgressIndicator()
                is MovieState.Success -> MovieList(movies = (state as MovieState.Success).movies)
                is MovieState.Error -> Text("Error: ${(state as MovieState.Error).message}")
            }
        }
    }
}

@Composable
fun MovieList(movies: List<Movie>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(movies.size) { index ->
            MovieItem(movies[index])
        }
    }
}
