package com.naufal.aplikasigithubuser2.view.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
    private val mainViewModel by lazy {
        ViewModelProvider(
                this, ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)}
    private var adapter = AdapterUser(arrayListOf())
    private val listUser : ArrayList<ItemsItem?> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getSearchList()
        toolbarSetup()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchView = menu.findItem(R.id.search_view).actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                loadingState(true)
                mainViewModel.searchViewModelUser(query, this@MainActivity)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        menu.findItem(R.id.search_view).setOnActionExpandListener(object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                binding.apply {
                    rvUser.visibility = View.GONE
                    findImageMain.visibility = View.VISIBLE
                    textViewFindUser.visibility = View.VISIBLE
                    textViewFindDetail.visibility = View.VISIBLE
                }
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_change_settings){
            val sIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(sIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            binding.rvUser.visibility = View.GONE
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun toolbarSetup() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setLogo(R.drawable.logo)
        supportActionBar?.setDisplayUseLogoEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }


    private fun getSearchList() {
        mainViewModel.getViewModelSearchUser().observe(this@MainActivity, {
            loadingState(false)
            binding.rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.rvUser.setHasFixedSize(true)
            binding.rvUser.visibility = View.VISIBLE
            listUser.clear()
            if (it != null) {
                listUser.addAll(it)
            }
                adapter = AdapterUser(listUser)
                binding.rvUser.adapter = adapter

        })
    }

    private fun loadingState(loading : Boolean){
        if (loading){
            binding.apply {
                progressBar.visibility = View.VISIBLE
                findImageMain.visibility = View.GONE
                textViewFindUser.visibility = View.GONE
                textViewFindDetail.visibility = View.GONE
            }
        } else {
            binding.apply {
                progressBar.visibility = View.GONE
                findImageMain.visibility = View.GONE
                textViewFindUser.visibility = View.GONE
                textViewFindDetail.visibility = View.GONE
            }
        }
    }






}






