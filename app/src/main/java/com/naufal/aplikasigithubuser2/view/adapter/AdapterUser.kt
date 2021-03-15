package com.naufal.aplikasigithubuser2.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.naufal.aplikasigithubuser2.databinding.ListUserBinding
import com.naufal.aplikasigithubuser2.view.model.ItemsItem
import com.naufal.aplikasigithubuser2.view.view.DetailActivity

class AdapterUser(private val list : ArrayList<ItemsItem?>?) : RecyclerView.Adapter<AdapterUser.ViewHolder>() {

    class ViewHolder(val binding : ListUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
                ListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtName.text = list?.get(position)?.login

        Glide.with(holder.itemView.context)
                .load(list?.get(position)?.avatarUrl)
                .into(holder.binding.imgAvatar)

        holder.itemView.setOnClickListener {

            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.USERNAME, list?.get(position)?.login)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = list?.size ?: 0


}