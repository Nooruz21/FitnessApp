package com.example.domain.repositories

import com.example.domain.core.RemotePagingWrapper
import com.example.domain.core.RemoteWrapper
import com.example.domain.models.foo.Fitness
import kotlinx.coroutines.flow.Flow

interface FitnessRepository {

    fun fetchFitness(): RemoteWrapper<Fitness>

    fun fetchFitnessPaging(): RemotePagingWrapper<Fitness>

    fun getFitness(): Flow<List<Fitness>>
}