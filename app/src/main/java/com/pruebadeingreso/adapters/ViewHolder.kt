package com.pruebadeingreso.adapters

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebadeingreso.R
import com.pruebadeingreso.entities.User


class ViewHolder (view: View): RecyclerView.ViewHolder(view){

    fun bind(user: User){
        itemView.findViewById<TextView>(R.id.tvName).text = user.name
        itemView.findViewById<TextView>(R.id.tvEmail).text = user.email
        itemView.findViewById<TextView>(R.id.tvPhone).text = user.phone
        itemView.findViewById<Button>(R.id.btnPosts).setOnClickListener(View.OnClickListener {
            Log.i("Click", "User:" + user.name)
        })

    }
}