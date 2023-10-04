package com.example.data.utils

/**
 * Base mapper interface
 *
 * @param T domain layer model
 *
 *
 */
interface DataMapper<T> {

    /**
     * Function for map data layer model to domain layer model
     */
    fun mapToDomain(): T
}