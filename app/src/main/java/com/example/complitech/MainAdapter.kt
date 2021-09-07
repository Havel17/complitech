package com.example.complitech

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.complitech.model.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.model_character.view.*
import java.lang.Exception


class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    var characters = mutableListOf<Character>()
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.model_character, parent, false)
        return MainViewHolder(view)
        
    }
    
    override fun getItemCount() = characters.size
    
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
        var type = true
        if (character.type == "") type = false
        
        holder.itemView.setOnClickListener {
            if (holder.itemView.episode.visibility == View.GONE) {
                holder.itemView.episode.visibility = View.VISIBLE
                holder.itemView.location.visibility = View.VISIBLE
                holder.itemView.status.visibility = View.VISIBLE
                if (type) holder.itemView.type.visibility = View.VISIBLE
                holder.itemView.gender.visibility = View.VISIBLE
            } else (viewGone(holder))
        }
        viewGone(holder)
    }
    
    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image = itemView.image
        private val name = itemView.name
        private val episode = itemView.episode
        private val location = itemView.location
        private val status = itemView.status
        private val type = itemView.type
        private val gender = itemView.gender
        fun bind(ch: Character) {
            name.text = "name: " + ch.name
            Picasso.get().load(ch.image).into(image)
            episode.text = "episods: " + ch.episode
            location.text = "location: " + ch.location
            status.text = "status: " + ch.status
            type.text = "type: " + ch.type
            gender.text = "gender: " + ch.gender
        }
    }
    
    fun viewGone(holder: MainViewHolder) {
        holder.itemView.episode.visibility = View.GONE
        holder.itemView.location.visibility = View.GONE
        holder.itemView.status.visibility = View.GONE
        holder.itemView.type.visibility = View.GONE
        holder.itemView.gender.visibility = View.GONE
    }
    
    
}