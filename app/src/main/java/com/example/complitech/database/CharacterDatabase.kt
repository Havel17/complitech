package com.example.complitech.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.complitech.dao.CharacterDao
import com.example.complitech.model.Character

@Database(version = 1, entities = arrayOf(Character::class))
abstract class CharacterDatabase :RoomDatabase(){
    abstract fun getCharacterDao(): CharacterDao
}