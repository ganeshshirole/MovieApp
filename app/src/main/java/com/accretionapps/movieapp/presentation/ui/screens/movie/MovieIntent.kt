package com.accretionapps.movieapp.presentation.ui.screens.movie

sealed class MovieIntent {
    data object LoadMovies : MovieIntent()
}