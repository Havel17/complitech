package com.example.complitech

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.complitech.dao.Database
import com.example.complitech.model.Character
import com.example.complitech.repository.LocalRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    
    
    private lateinit var viewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter
    private var characters: MutableList<Character> = mutableListOf()
    private val coroutine = CoroutineScope(Dispatchers.IO)
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        Database.getDatabase(this)
        
        mainAdapter = MainAdapter()
        
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(LocalRepository)
        ).get(MainViewModel::class.java)
        viewModel.allCharacter.observe(this, refresh)
        
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = mainAdapter
        
        coroutine.launch {
            try {
                viewModel.addAll()
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "НЕТ ИНТЕРНЕТ СОЕДИНЕНИЯ", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            
            
            viewModel.getAll()
            withContext(Dispatchers.Main) {
                refresh
            }
        }
        
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
    
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    mainAdapter.characters = viewModel.find(newText)!!
                    mainAdapter.notifyDataSetChanged()
                }
                return true
            }
        })
    }
    
    private val refresh = Observer<MutableList<Character>> {
        characters = it
        mainAdapter.characters = characters
        mainAdapter.notifyDataSetChanged()
    }
    
    
}


