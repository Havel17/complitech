package com.example.complitech.dao

import android.content.Context
import androidx.room.Room
import com.example.complitech.database.CharacterDatabase

object Database {
    lateinit var database: CharacterDatabase
    
    fun getDatabase(context: Context):CharacterDatabase{
        if (!::database.isInitialized){
            database = Room.databaseBuilder(
                context,
                CharacterDatabase::class.java,
                "Character Database"
            ).fallbackToDestructiveMigration()
                .build()
        }
        return database
    }
}