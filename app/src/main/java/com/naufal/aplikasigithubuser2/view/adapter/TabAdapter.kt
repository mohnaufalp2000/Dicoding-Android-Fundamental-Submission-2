package com.naufal.aplikasigithubuser2.view.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.naufal.aplikasigithubuser2.view.view.FollowersFragment
import com.naufal.aplikasigithubuser2.view.view.FollowingsFragment

class TabAdapter(activity : AppCompatActivity) : FragmentStateAdapter(activity) {

    private val fragments : ArrayList<Fragment> = arrayListOf(
        FollowersFragment(),
        FollowingsFragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}