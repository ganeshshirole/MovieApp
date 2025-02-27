package com.accretionapps.movieapp.presentation.ui.screens.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.accretionapps.movieapp.BuildConfig
import com.accretionapps.movieapp.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow<MovieState>(MovieState.Loading)
    val state: StateFlow<MovieState> = _state

    fun handleIntents(intent: MovieIntent) {
        viewModelScope.launch {
            when (intent) {
                is MovieIntent.LoadMovies -> fetchMovies()
            }
        }
    }

    private suspend fun fetchMovies() {
        _state.value = MovieState.Loading
        getMoviesUseCase(
            apiKey = BuildConfig.TMDB_API_KEY,
            page = 1,
            language = "en-US",
            query = "inte"
        ).catch {
            _state.value = MovieState.Error(it.message ?: "Unknown error occurred")
        }.collect {
            _state.value = MovieState.Success(it)
        }
    }
}
