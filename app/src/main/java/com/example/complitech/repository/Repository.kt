package com.example.complitech.repository

import com.example.complitech.model.Character

interface Repository {
    suspend fun getAll(): MutableList<Character>
    suspend fun addAll(list:MutableList<Character>)
}