package com.naufal.aplikasigithubuser2.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.naufal.aplikasigithubuser2.databinding.ListUserBinding
import com.naufal.aplikasigithubuser2.view.model.Follower

class AdapterFollower(private val list : ArrayList<Follower>?) : RecyclerView.Adapter<AdapterFollower.ViewHolder>() {

    class ViewHolder(val binding : ListUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            ListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtName.text = list?.get(position)?.login

        Glide.with(holder.itemView.context)
                .load(list?.get(position)?.avatarUrl)
                .into(holder.binding.imgAvatar)

    }

    override fun getItemCount(): Int = list?.size ?: 0

}