package com.example.complitech.model

data class GetCharacter(
    val info: Info,
    val results: MutableList<Result>
)
data class Result(
    val created: String,
    val episode: MutableList<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)
data class Location(
    val name: String,
    val url: String
)
data class Origin(
    val name: String,
    val url: String
)
