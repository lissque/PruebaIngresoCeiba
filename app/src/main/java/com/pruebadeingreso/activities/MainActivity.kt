package com.pruebadeingreso.activities

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebadeingreso.databinding.ActivityMainBinding
import com.pruebadeingreso.API.APIService
import com.pruebadeingreso.API.EndPoints
import com.pruebadeingreso.adapters.RecyclerViewAdapterUsers
import com.pruebadeingreso.entities.User
import com.pruebadeingreso.utilities.LoadingDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapterUsers
    private val users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svBusqueda.setOnQueryTextListener(this)

        getAllUsers(EndPoints.USERS)
    }

    private fun initRecyclerView() {
        adapter = RecyclerViewAdapterUsers(users)
        binding.rvUsuarios.setHasFixedSize(true)
        binding.rvUsuarios.layoutManager = LinearLayoutManager(this)
        binding.rvUsuarios.addItemDecoration(
            DividerItemDecoration(
                binding.rvUsuarios.context,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvUsuarios.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(EndPoints.URLBASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getAllUsers(params: String) {

        val loadingDialog = LoadingDialog(this)
        loadingDialog.startLoading()
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getUsers(params)
            val aux = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    loadingDialog.dismiss()
                    val userss = aux ?: emptyList<User>()
                    users.clear()
                    users.addAll(userss)
                    initRecyclerView()
                } else {
                    showToastErrorMessage()
                }
            }
        }
    }

    private fun showToastErrorMessage() {
        Toast.makeText(this, "Error has been occurred...", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        if (!newText.isNullOrEmpty()) {
            var usersSearched = mutableListOf<User>()
            if (!users.isNullOrEmpty()) {
                for (user in users) {
                    if (user.name.contains(newText, ignoreCase = true)) {
                        usersSearched.add(user)
                    }
                }

                if (usersSearched.isEmpty()) {
                    Toast.makeText(this, "List is empty!", Toast.LENGTH_SHORT).show()
                } else {
                    adapter = RecyclerViewAdapterUsers(usersSearched)
                    binding.rvUsuarios.adapter = adapter
                }
            }
        }
        return true
    }
}

