package com.example.data.repositories

import com.example.data.base.BaseRepository
import com.example.data.local.db.daos.FitnessDao
import com.example.data.local.db.entities.foo.toEntity
import com.example.data.remote.apiservices.FooApiService
import com.example.data.remote.pagingsources.FitnessPagingSource
import com.example.domain.repositories.FitnessRepository
import javax.inject.Inject

class FitnessRepositoryImpl @Inject constructor(
    private val service: FooApiService,
    private val dao: FitnessDao
) : BaseRepository(), FitnessRepository {

    override fun fetchFitness() = doNetworkRequestWithMapping {
        service.fetchFoo().onSuccess { data ->
            dao.insertFitness(data.toEntity())
        }
    }

    override fun getFitness() = doLocalRequestForList { dao.getAllFitness() }

    override fun fetchFitnessPaging() = doPagingRequest({ FitnessPagingSource(service) })
}