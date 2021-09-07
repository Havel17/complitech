package com.example.complitech.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(indices = arrayOf())
data class Character(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "status") var status: String? = null,
    @ColumnInfo(name = "species") var species: String? = null,
    @ColumnInfo(name = "type") var type: String? = null,
    @ColumnInfo(name = "gender") var gender: String? = null,
    @ColumnInfo(name = "origin") var origin: String? = null,
    @ColumnInfo(name = "location") var location: String? = null,
    @ColumnInfo(name = "image") var image: String? = null,
    @ColumnInfo(name = "episode") var episode: String? = null,
    @ColumnInfo(name = "url") var url: String? = null,
    @ColumnInfo(name = "created") var created: String? = null
)
