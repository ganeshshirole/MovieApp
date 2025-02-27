package com.accretionapps.movieapp.presentation.ui.screens.movie

import com.accretionapps.movieapp.domain.model.movies.Movie

sealed class MovieState {
    data object Loading : MovieState()
    data class Success(val movies: List<Movie>) : MovieState()
    data class Error(val message: String) : MovieState()
}