package com.naufal.aplikasigithubuser2.view.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.naufal.aplikasigithubuser2.R
import com.naufal.aplikasigithubuser2.databinding.ActivityDetailBinding
import com.naufal.aplikasigithubuser2.view.adapter.TabAdapter
import com.naufal.aplikasigithubuser2.view.model.ItemsItem
import com.naufal.aplikasigithubuser2.view.viewmodel.MainViewModel

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater)}
    private val mainViewModel by lazy { ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java) }

    companion object{
        const val USERNAME = "username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        tabConfig()
        setDetail()
        getDetail()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
            } else {
                binding.txtDetailLocation.text = it.location
            }

            if (it?.company == null){
                binding.imageViewCompany.visibility = View.GONE
                binding.dividerComp.visibility = View.GONE
            } else {
                binding.txtDetailCompany.text = it.company
            }

            binding.txtDetailRepoLink.text = it?.reposUrl
        })

    }

    private fun setDetail() {
        val username = intent.getStringExtra(USERNAME)

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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}