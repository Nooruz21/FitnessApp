package com.example.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.db.daos.FitnessDao
import com.example.data.local.db.entities.foo.FitnessEntity

@Database(entities = [FitnessEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun fooDao(): FitnessDao
}