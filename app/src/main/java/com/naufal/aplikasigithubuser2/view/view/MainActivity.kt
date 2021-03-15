package com.naufal.aplikasigithubuser2.view.view

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.naufal.aplikasigithubuser2.R
import com.naufal.aplikasigithubuser2.databinding.ActivityMainBinding
import com.naufal.aplikasigithubuser2.view.adapter.AdapterUser
import com.naufal.aplikasigithubuser2.view.model.ItemsItem
import com.naufal.aplikasigithubuser2.view.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel by lazy {ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)}
    private var adapter = AdapterUser(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        showRecylerView()
        getSearchList()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchView = menu.findItem(R.id.search_view).actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                binding.apply {
                    progressBar.visibility = View.VISIBLE
                    findImageMain.visibility = View.GONE
                    textViewFindUser.visibility = View.GONE
                    textViewFindDetail.visibility = View.GONE
                }
                mainViewModel.searchViewModelUser(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun showRecylerView() {
        binding.rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rvUser.setHasFixedSize(true)
    }

    private fun getSearchList() {
        mainViewModel.getViewModelSearchUser().observe(this@MainActivity, {
            binding.apply {
                progressBar.visibility = View.GONE
                findImageMain.visibility = View.GONE
                textViewFindUser.visibility = View.GONE
                textViewFindDetail.visibility = View.GONE
            }
            if (it != null){
                adapter = AdapterUser(it)
                binding.rvUser.adapter = adapter
            }
        })
    }






}






