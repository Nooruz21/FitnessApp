package com.example.data.remote.dtos.foo

import com.example.data.utils.DataMapper
import com.example.domain.models.foo.Fitness
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class FitnessDto(
    @Json(name = "id")
    val id: Long,
    @Json(name = "bar")
    val bar: String
) : DataMapper<Fitness> {

    override fun mapToDomain() = Fitness(id, bar)
}