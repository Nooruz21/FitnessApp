package com.example.data.local.db.entities.foo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.remote.dtos.foo.FitnessDto
import com.example.data.utils.DataMapper
import com.example.domain.models.foo.Fitness

@Entity(tableName = "foo")
class FitnessEntity(
    @PrimaryKey
    val id: Long,
    val bar: String
) : DataMapper<Fitness> {

    override fun mapToDomain() = Fitness(id, bar)
}

fun FitnessDto.toEntity() = FitnessEntity(id, bar)