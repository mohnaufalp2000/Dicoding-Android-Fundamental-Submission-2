package com.naufal.aplikasigithubuser2.view.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.naufal.aplikasigithubuser2.R
import com.naufal.aplikasigithubuser2.databinding.ActivityDetailBinding
import com.naufal.aplikasigithubuser2.view.adapter.TabAdapter
import com.naufal.aplikasigithubuser2.view.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater)}
    private val mainViewModel by lazy {
        ViewModelProvider(
                this, ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java) }


    companion object{
        const val USERNAME = "username"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val username = intent.getStringExtra(USERNAME)

        tabConfig()
        setDetail(username)
        getDetail()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(username)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun getDetail() {
        mainViewModel.getViewModelDetailUser().observe(this@DetailActivity, {
            binding.txtDetailName.text = it?.name ?: getString(R.string.user)
            binding.txtDetailUsername.text = it?.login
            Glide.with(this)
                    .load(it?.avatarUrl)
                    .apply(RequestOptions.overrideOf(100))
                    .into(binding.imgDetail)
            binding.txtDetailFollowers.text = it?.followers.toString()
            binding.txtDetailFollowings.text = it?.following.toString()
            binding.txtDetailRepo.text = it?.publicRepos.toString()
            if (it?.location == null){
                binding.imageViewLocation.visibility = View.GONE
                binding.dividerLoc.visibility = View.GONE
                binding.txtDetailLocation.visibility = View.GONE
            } else {
                binding.txtDetailLocation.text = it.location
            }

            if (it?.company == null){
                binding.imageViewCompany.visibility = View.GONE
                binding.dividerComp.visibility = View.GONE
                binding.txtDetailCompany.visibility = View.GONE
            } else {
                binding.txtDetailCompany.text = it.company
            }

            binding.txtDetailRepoLink.text = it?.reposUrl
            progress_bar_detail.visibility = View.GONE

        })

    }

    private fun setDetail(username : String?) {
        progress_bar_detail.visibility = View.VISIBLE
        mainViewModel.detailViewModelUser(username)
    }

    private fun tabConfig() {
        val tabTitle = intArrayOf(
            R.string.followers,
            R.string.followings
        )
        binding.vpDetail.adapter = TabAdapter(this)
        TabLayoutMediator(binding.tabsDetail, binding.vpDetail){
            tab, position -> tab.text = resources.getString(tabTitle[position])
        }.attach()
    }



}