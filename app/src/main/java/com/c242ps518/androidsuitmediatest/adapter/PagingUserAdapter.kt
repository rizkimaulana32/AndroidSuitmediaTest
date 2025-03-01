package com.c242ps518.androidsuitmediatest.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.c242ps518.androidsuitmediatest.R
import com.c242ps518.androidsuitmediatest.data.response.DataItem
import com.c242ps518.androidsuitmediatest.databinding.ItemRowUserBinding
import com.c242ps518.androidsuitmediatest.ui.SecondScreenActivity

class PagingUserAdapter: PagingDataAdapter<DataItem, PagingUserAdapter.PagingUserViewHolder> (DIFF_CALLBACK){
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    class PagingUserViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: DataItem) {
            binding.tvName.text = "${data.firstName} ${data.lastName}"
            binding.tvEmail.text = data.email
            Glide.with(binding.ivProfile.context)
                .load(data.avatar)
                .placeholder(R.drawable.user)
                .into(binding.ivProfile)

            binding.clickableView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, SecondScreenActivity::class.java)
                intent.putExtra("username", "${data.firstName} ${data.lastName}")
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: PagingUserViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingUserViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagingUserViewHolder(binding)
    }
}