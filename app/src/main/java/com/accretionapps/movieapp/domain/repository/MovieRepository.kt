package com.accretionapps.movieapp.domain.repository

import com.accretionapps.movieapp.domain.model.movies.MoviesResponse

interface MovieRepository {
    suspend fun getMovies(
        apiKey: String, language: String, page: Int, query: String
    ): MoviesResponse

    suspend fun nowPlaying(
        apiKey: String, language: String, page: Int
    ): MoviesResponse
}