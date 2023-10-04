package com.example.data.remote.pagingsources

import com.example.data.remote.apiservices.FooApiService
import com.example.data.remote.dtos.foo.FitnessDto
import com.example.data.base.BasePagingSource
import com.example.domain.models.foo.Fitness

class FitnessPagingSource(
    private val service: FooApiService
) : BasePagingSource<FitnessDto, Fitness>(
    { service.fetchFooPaging(it) }
)