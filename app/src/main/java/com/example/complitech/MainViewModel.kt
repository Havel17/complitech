package com.example.complitech

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.complitech.repository.LocalRepository
import com.example.complitech.model.Character
import com.example.complitech.model.Result
import com.example.complitech.repository.RetrofitClient

class MainViewModel(private val repository: LocalRepository) : ViewModel() {
    var allCharacter = MutableLiveData<MutableList<Character>>()
    
    suspend fun getAll() {
        allCharacter.postValue(repository.getAll())
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
    
    fun find(findWord: String): MutableList<Character>? {
        val findedList: MutableList<Character> = mutableListOf()
        if (findWord == ""){
            return allCharacter.value
        }
        allCharacter.value?.forEachIndexed { i, s ->
            var count = 0
            var j = 0
            for (i in 0..s.name!!.lastIndex) {
                if (s.name!![i].equals(findWord[j], ignoreCase = true)) {
                    count++; j++
                    if (count == findWord.length) {
                        j = 0; count = 0
                        findedList.add(s)
                        return@forEachIndexed
                    }
                } else {
                    j = 0; count = 0
                }
            }
        }
        
        return findedList
    }
}