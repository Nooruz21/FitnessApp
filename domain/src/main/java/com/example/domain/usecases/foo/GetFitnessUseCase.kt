package com.example.domain.usecases.foo

import com.example.domain.repositories.FitnessRepository
import javax.inject.Inject

class GetFitnessUseCase @Inject constructor(
    private val repository: FitnessRepository
) {
    operator fun invoke() = repository.getFitness()
}