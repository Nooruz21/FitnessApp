package com.example.data.local.db.daos

import androidx.room.*
import com.example.data.local.db.entities.foo.FitnessEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FitnessDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFitness(foo: FitnessEntity)

    @Query("SELECT * FROM foo")
    fun getAllFitness(): Flow<List<FitnessEntity>>
}