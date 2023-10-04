package com.example.domain.usecases.foopaging

import com.example.domain.repositories.FitnessRepository
import javax.inject.Inject

class FetchFitnessPagingUseCase @Inject constructor(
    private val repository: FitnessRepository
) {
    operator fun invoke() = repository.fetchFitnessPaging()
}