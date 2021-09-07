package com.example.complitech

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.complitech.repository.LocalRepository
import com.example.complitech.model.Character
import com.example.complitech.model.Result
import com.example.complitech.repository.RetrofitClient

class MainViewModel(private val repository: LocalRepository) : ViewModel() {
    var allCharacter = MutableLiveData<MutableList<Character>>()
    
    suspend fun getAll(): LiveData<MutableList<Character>> {
        allCharacter.postValue(repository.getAll())
        return allCharacter
    }
    
    suspend fun addAll() {
        val result = RetrofitClient.getRepositoryService().getAllCharacter().results
        repository.addAll(convert(result))
    }
    
    fun convert(result: MutableList<Result>): MutableList<Character> {
        val newlist: MutableList<Character> = mutableListOf()
        
        result.forEach {
            val ch = Character()
            ch.created = it.created
            val episode = StringBuffer("")
            it.episode.forEachIndexed() { i: Int, s: String ->
                if (i == 0) {
                    episode.append(s.drop(40))
                } else {
                    episode.append(", " + s.drop(40))
                }
            }
            ch.episode = episode.toString()
            ch.gender = it.gender
            ch.id = it.id
            ch.image = it.image
            ch.location = it.location.name
            ch.origin = it.origin.name
            ch.name = it.name
            ch.species = it.species
            ch.status = it.status
            ch.type = it.type
            ch.url = it.url
            newlist.add(ch)
        }
        return newlist
    }
}