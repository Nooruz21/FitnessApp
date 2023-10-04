package com.example.fitnessapp.di

import com.example.data.repositories.FitnessRepositoryImpl
import com.example.domain.repositories.FitnessRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindFooRepository(
        repositoryImpl: FitnessRepositoryImpl
    ): FitnessRepository
}