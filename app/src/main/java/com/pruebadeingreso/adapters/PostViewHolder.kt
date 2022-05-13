package com.pruebadeingreso.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebadeingreso.R
import com.pruebadeingreso.entities.Post

class PostViewHolder(view: View) :RecyclerView.ViewHolder(view) {

    fun bind(post:Post){
        itemView.findViewById<TextView>(R.id.tvTittle).text = post.tittle
        itemView.findViewById<TextView>(R.id.tvPost).text = post.body
    }
}