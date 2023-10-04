package com.example.fitnessapp.di

import androidx.lifecycle.MutableLiveData
import com.example.data.remote.client.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideTokenErrorListener() = MutableLiveData<String>()

    @Singleton
    @Provides
    fun provideAuthenticatorApiService(
        authenticatorClient: NetworkClient.AuthenticatorClient
    ) = authenticatorClient.provideAuthenticatorApiService()

    @Singleton
    @Provides
    fun provideFooApiService(
        networkClient: NetworkClient
    ) = networkClient.provideFitnessApiService()
}