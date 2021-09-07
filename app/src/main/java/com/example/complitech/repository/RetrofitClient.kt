package com.example.complitech.repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    
    private lateinit var service: RepositoryService
    
    fun getRepositoryService(): RepositoryService {
       
            if (!::service.isInitialized) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                service = retrofit.create(RepositoryService::class.java)
            }
        
        return service
    }

}