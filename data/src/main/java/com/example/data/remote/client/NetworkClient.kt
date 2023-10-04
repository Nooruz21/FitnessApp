package com.example.data.remote.client

import com.example.data.remote.apiservices.AuthenticatorApiService
import com.example.data.remote.apiservices.FooApiService
import com.example.data.remote.client.authenticator.TokenAuthenticator
import com.example.data.remote.client.interceptors.AuthorizationInterceptor
import javax.inject.Inject

class NetworkClient @Inject constructor(
    authenticator: TokenAuthenticator,
    authorizationInterceptor: AuthorizationInterceptor
) {

    private val provideRetrofit = provideRetrofit(
        provideOkHttpClientBuilder().apply {
            authenticator(authenticator)
            addInterceptor(authorizationInterceptor)
        }.build()
    )

    fun provideFitnessApiService(): FooApiService = provideRetrofit.create(
        FooApiService::class.java
    )

    class AuthenticatorClient @Inject constructor() {

        private val provideRetrofit = provideRetrofit(provideOkHttpClientBuilder().build())

        fun provideAuthenticatorApiService(): AuthenticatorApiService = provideRetrofit.create(
            AuthenticatorApiService::class.java
        )
    }
}