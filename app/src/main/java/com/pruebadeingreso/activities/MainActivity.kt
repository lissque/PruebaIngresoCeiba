package com.pruebadeingreso.activities

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebadeingreso.databinding.ActivityMainBinding
import com.pruebadeingreso.API.APIService
import com.pruebadeingreso.adapters.ListViewAdapterUsers
import com.pruebadeingreso.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ListViewAdapterUsers
    private val users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svBusqueda.setOnQueryTextListener(this)

        getAllUsers("users")
    }

    private fun initRecyclerView(){
        adapter = ListViewAdapterUsers(users)
        binding.rvUsuarios.setHasFixedSize(true)
        binding.rvUsuarios.layoutManager = LinearLayoutManager(this)
        binding.rvUsuarios.addItemDecoration(DividerItemDecoration(binding.rvUsuarios.context, DividerItemDecoration.VERTICAL))
        binding.rvUsuarios.adapter = adapter
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getAllUsers(params:String){

        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getUsers(params)
            val aux = call.body()
            runOnUiThread{
                if (call.isSuccessful){
                    val userss = aux ?: emptyList<User>()
                    users.clear()
                    users.addAll(userss)
                    initRecyclerView()
                } else {
                    showToastMessage()
                }
            }
        }
    }

    private fun showToastMessage(){
        Toast.makeText(this, "Error...", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (!newText.isNullOrEmpty()){
        }
        return true
    }
}

