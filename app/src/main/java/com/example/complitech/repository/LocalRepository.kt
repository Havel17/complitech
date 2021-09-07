package com.example.complitech.repository

import com.example.complitech.dao.CharacterDao
import com.example.complitech.dao.Database
import com.example.complitech.model.Character

object LocalRepository:Repository {
    private var characterDao: CharacterDao? = Database.database.getCharacterDao()
    override suspend fun getAll(): MutableList<Character> {
        return characterDao?.getAll()!!
        
    }
    
    override suspend fun addAll(list: MutableList<Character>) {
        characterDao?.addAll(list)
    }
    
}