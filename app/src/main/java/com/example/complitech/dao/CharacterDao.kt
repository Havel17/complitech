package com.example.complitech.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.complitech.model.Character
import java.util.*

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    suspend fun getAll():MutableList<Character>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun addAll(objects: MutableList<Character>)
}