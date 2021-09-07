package com.example.complitech

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.complitech.repository.LocalRepository

class ViewModelFactory(private val repository: LocalRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass == MainViewModel::class.java)
        MainViewModel(repository) as T
        else throw IllegalArgumentException("Wrong ViewModel class")
    }
}