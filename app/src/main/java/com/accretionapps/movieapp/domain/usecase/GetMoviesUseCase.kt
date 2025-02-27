package com.accretionapps.movieapp.domain.usecase

import com.accretionapps.movieapp.domain.model.movies.Movie
import com.accretionapps.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(
        apiKey: String,
        page: Int,
        language: String,
        query: String
    ): Flow<List<Movie>> = flow {
        val movies = repository.getMovies(apiKey, language, page, query).movies
        emit(movies)
    }
}
