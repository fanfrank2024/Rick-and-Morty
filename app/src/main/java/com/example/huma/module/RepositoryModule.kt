package com.example.huma.module

import com.example.huma.data.ApiService
import com.example.huma.data.FigureRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideFigureRepository(apiService: ApiService): FigureRepository {
        return FigureRepository(apiService)
    }
}