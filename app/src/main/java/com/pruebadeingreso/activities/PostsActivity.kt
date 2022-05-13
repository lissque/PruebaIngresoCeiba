package com.pruebadeingreso.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebadeingreso.databinding.ActivityPostsBinding
import com.pruebadeingreso.API.APIService
import com.pruebadeingreso.API.EndPoints
import com.pruebadeingreso.adapters.RecyclerViewAdapterPosts
import com.pruebadeingreso.entities.Post
import com.pruebadeingreso.utilities.LoadingDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostsBinding
    private lateinit var adapter: RecyclerViewAdapterPosts
    private val posts = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPostsOfUser()
    }

    fun getPostsOfUser() {
        val userId = intent.getIntExtra(EXTRA_MESSAGE, 1)
        Log.i("EndPointposts", EndPoints.URLBASE+EndPoints.POSTS+userId);
        getPostsOfUser(EndPoints.POSTS+userId)
    }

    private fun initRecyclerView() {

        adapter = RecyclerViewAdapterPosts(posts)
        binding.rvPosts.setHasFixedSize(true)
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
        binding.rvPosts.addItemDecoration(
            DividerItemDecoration(
                binding.rvPosts.context,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvPosts.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(EndPoints.URLBASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getPostsOfUser(params:String){

        val loadingDialog = LoadingDialog(this)
        loadingDialog.startLoading()
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getPostsOfUser(params)
            val aux = call.body()
            runOnUiThread{
                if (call.isSuccessful){
                    loadingDialog.dismiss()
                    val postss = aux ?: emptyList<Post>()
                    posts.clear()
                    posts.addAll(postss)
                    initRecyclerView()
                } else {
                    showToastErrorMessage()
                }
            }
        }
    }

    private fun showToastErrorMessage(){
        Toast.makeText(this, "Error has been occurred...", Toast.LENGTH_SHORT).show()
    }
}