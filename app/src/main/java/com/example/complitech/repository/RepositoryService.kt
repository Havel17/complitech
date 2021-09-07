package com.example.complitech.repository

import com.example.complitech.model.GetCharacter
import retrofit2.http.GET

interface RepositoryService {
    @GET("/api/character")
    suspend fun getAllCharacter():GetCharacter
}