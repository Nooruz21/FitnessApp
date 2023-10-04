package com.example.data.local.db

import android.content.Context
import androidx.room.Room
import com.example.data.local.db.daos.FitnessDao

class RoomClient {

    fun provideAppDatabase(context: Context) = Room
        .databaseBuilder(context, AppDatabase::class.java, "fitness.db")
        .build()

    fun provideFitnessDao(appDatabase: AppDatabase): FitnessDao = appDatabase.fooDao()
}