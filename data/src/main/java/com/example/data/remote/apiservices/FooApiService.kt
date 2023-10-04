package com.example.data.remote.apiservices

import com.example.data.remote.dtos.foo.FitnessDto
import com.example.data.remote.dtos.foo.FitnessPagingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FooApiService {

    @GET("/api/foo")
    suspend fun fetchFoo(): Response<FitnessDto>

    @GET("/api/foo")
    suspend fun fetchFooPaging(
        @Query("page") page: Int
    ): Response<FitnessPagingResponse<FitnessDto>>
}