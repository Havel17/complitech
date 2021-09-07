package com.example.complitech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.complitech.dao.Database
import com.example.complitech.model.Character
import com.example.complitech.repository.LocalRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
            viewModel.addAll()
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
                find(newText!!)
                return false
            }
    
        })
    }
    
    private val refresh = Observer<MutableList<Character>> {
        characters = it
        mainAdapter.characters = characters
        mainAdapter.notifyDataSetChanged()
    }
    
    fun find(findWord: String) {
        val test = characters
        val finded: MutableList<Character> = mutableListOf()
        if (findWord == "") {
        } else {
            test.forEachIndexed { i, s ->
                var count = 0
                var j = 0
                for (i in 0..s.name!!.lastIndex) {
                    if (s.name!![i].equals(
                            findWord[j],
                            ignoreCase = true
                        )
                    ) {
                        count++
                        j++
                        if (count == findWord.length) {
                            j = 0
                            count = 0
                            finded.add(s)
                            return@forEachIndexed
                        }
                    } else {
                        j = 0
                        count = 0
                    }
                }
                
            }
            
        }
        mainAdapter.characters.clear()
        mainAdapter.characters.addAll(finded)
        mainAdapter.notifyDataSetChanged()
    }
    
}


