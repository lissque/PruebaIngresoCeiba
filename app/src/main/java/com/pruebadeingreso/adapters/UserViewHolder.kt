package com.pruebadeingreso.adapters


import android.content.Context
import android.content.Intent
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebadeingreso.R
import com.pruebadeingreso.activities.PostsActivity
import com.pruebadeingreso.entities.User


class UserViewHolder (view: View): RecyclerView.ViewHolder(view){

    val context:Context = view.context

    fun bind(user: User){
        itemView.findViewById<TextView>(R.id.tvName).text = user.name
        itemView.findViewById<TextView>(R.id.tvEmail).text = user.email
        itemView.findViewById<TextView>(R.id.tvPhone).text = user.phone
        itemView.findViewById<Button>(R.id.btnPosts).setOnClickListener(View.OnClickListener {
            startPostsActivity(user.id)
        })

    }

    fun startPostsActivity(userid:Int){
        val intent = Intent(context, PostsActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, userid)
        }
        context.startActivity(intent)
    }

}