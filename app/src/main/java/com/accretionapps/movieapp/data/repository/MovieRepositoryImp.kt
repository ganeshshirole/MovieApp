package com.accretionapps.movieapp.data.repository

import com.accretionapps.movieapp.domain.model.movies.MoviesResponse
import com.accretionapps.movieapp.data.remote.MovieApiService
import com.accretionapps.movieapp.domain.repository.MovieRepository

class MovieRepositoryImp(private val apiService: MovieApiService) : MovieRepository {
    override suspend fun getMovies(
        apiKey: String, language: String, page: Int, query: String
    ): MoviesResponse =
        apiService.searchMovie(apiKey = apiKey, language = language, page = page, query = query)

    override suspend fun nowPlaying(
        apiKey: String, language: String, page: Int
    ): MoviesResponse = apiService.nowPlaying(apiKey = apiKey, language = language, page = page)
}
