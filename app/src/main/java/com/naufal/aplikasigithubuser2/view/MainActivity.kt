package com.naufal.aplikasigithubuser2.view

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.naufal.aplikasigithubuser2.R
import com.naufal.aplikasigithubuser2.databinding.ActivityMainBinding
import com.naufal.aplikasigithubuser2.view.adapter.AdapterUser
import com.naufal.aplikasigithubuser2.view.model.ItemsItem
import com.naufal.aplikasigithubuser2.view.model.User
import com.naufal.aplikasigithubuser2.view.network.ConfigNetwork
import com.naufal.aplikasigithubuser2.view.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var mainViewModel: MainViewModel
    private var adapter = AdapterUser(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        showRecyclerview()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchView = menu.findItem(R.id.search_view).actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                ConfigNetwork.getUser().searchDataUser(query).enqueue(object : Callback<User>{
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful){
                            val body = response.body()?.items
                            val listSearch : ArrayList<ItemsItem?> = arrayListOf()

                            if (body != null){
                                for (items in body){
                                    listSearch.add(items)
                                }
                            }
                            binding.rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
                            binding.rvUser.setHasFixedSize(true)
                            adapter = AdapterUser(listSearch)
                            binding.rvUser.adapter = adapter
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {

                    }

                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun showRecyclerview() {
        binding.rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rvUser.setHasFixedSize(true)

        mainViewModel.getViewModelUser().observe(this, {
            if (it != null){
                adapter = AdapterUser(it)
                binding.rvUser.adapter = adapter
            }
        })

    }
}






