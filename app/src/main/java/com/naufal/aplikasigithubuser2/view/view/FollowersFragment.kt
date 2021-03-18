package com.naufal.aplikasigithubuser2.view.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.naufal.aplikasigithubuser2.databinding.FragmentFollowersBinding
import com.naufal.aplikasigithubuser2.view.adapter.AdapterFollower
import com.naufal.aplikasigithubuser2.view.viewmodel.FragmentViewModel

class FollowersFragment : Fragment() {

    private var binding : FragmentFollowersBinding? = null
    private val fragmentViewModel by lazy {
        ViewModelProvider(
                this, ViewModelProvider.NewInstanceFactory()
        ).get(FragmentViewModel::class.java)}

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = activity?.intent?.getStringExtra(DetailActivity.USERNAME)

        setFollowers(username)
        getFollowers()

    }

    private fun getFollowers() {
        fragmentViewModel.getViewModelFollowers().observe(viewLifecycleOwner, {
            val adapter = AdapterFollower(it)
            binding?.apply {
                rvFollowers.setHasFixedSize(true)
                rvFollowers.layoutManager = LinearLayoutManager(context)
                rvFollowers.isNestedScrollingEnabled = true
                rvFollowers.adapter = adapter
            }

            adapter.notifyDataSetChanged()
        })
    }

    private fun setFollowers(username: String?) {
        fragmentViewModel.followersViewModelUser(username)
    }




    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}