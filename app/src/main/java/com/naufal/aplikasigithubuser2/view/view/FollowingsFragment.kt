package com.naufal.aplikasigithubuser2.view.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.naufal.aplikasigithubuser2.databinding.FragmentFollowingsBinding
import com.naufal.aplikasigithubuser2.view.adapter.AdapterFollowing
import com.naufal.aplikasigithubuser2.view.viewmodel.FragmentViewModel

class FollowingsFragment : Fragment() {

    private var binding : FragmentFollowingsBinding? = null
    private val fragmentViewModel by lazy {
        ViewModelProvider(
                this, ViewModelProvider.NewInstanceFactory()
        ).get(FragmentViewModel::class.java)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFollowingsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = activity?.intent?.getStringExtra(DetailActivity.USERNAME)
        setFollowings(username)
        getFollowings()

    }

    private fun getFollowings() {
        fragmentViewModel.getViewModelFollowings().observe(viewLifecycleOwner,{
            val adapter = AdapterFollowing(it)
            binding?.apply {
                progressBarFollowings.visibility = View.GONE
                rvFollowings.setHasFixedSize(true)
                rvFollowings.layoutManager = LinearLayoutManager(context)
                rvFollowings.isNestedScrollingEnabled = true
                rvFollowings.adapter = adapter
            }
            adapter.notifyDataSetChanged()
        })
    }

    private fun setFollowings(username: String?) {
        binding?.progressBarFollowings?.visibility = View.VISIBLE
        fragmentViewModel.followingsViewModelUser(username)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}