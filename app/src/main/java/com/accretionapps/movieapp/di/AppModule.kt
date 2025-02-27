package com.accretionapps.movieapp.di

import com.accretionapps.movieapp.data.remote.MovieApiService
import com.accretionapps.movieapp.data.repository.MovieRepositoryImp
import com.accretionapps.movieapp.domain.repository.MovieRepository
import com.accretionapps.movieapp.domain.usecase.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideService(): MovieApiService {
        return MovieApiService.create()
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: MovieApiService): MovieRepository {
        return MovieRepositoryImp(apiService)
    }

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(repository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(repository)
    }
}