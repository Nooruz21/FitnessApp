package com.example.domain.usecases.foo

import com.example.domain.repositories.FitnessRepository
import javax.inject.Inject

class FetchFitnessUseCase @Inject constructor(
    private val repository: FitnessRepository
) {
    operator fun invoke() = repository.fetchFitness()
}