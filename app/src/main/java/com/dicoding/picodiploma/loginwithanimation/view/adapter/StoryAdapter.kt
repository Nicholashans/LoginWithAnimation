package com.dicoding.picodiploma.loginwithanimation.view.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.loginwithanimation.data.api.ListStoryItem
import com.dicoding.picodiploma.loginwithanimation.databinding.ItemStoryBinding
import com.dicoding.picodiploma.loginwithanimation.view.detail.DetailActivity

class StoryAdapter : ListAdapter<ListStoryItem, StoryAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(val binding: ItemStoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(story : ListStoryItem) {
            binding.tvItem.text = story.name
            binding.tvDescription.text = story.description
            Glide.with(binding.root)
                .load(story.photoUrl)
                .into(binding.imgItemPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val imgPhoto: ImageView = holder.itemView.findViewById(com.dicoding.picodiploma.loginwithanimation.R.id.imgItemPhoto)
        val tvName: TextView = holder.itemView.findViewById<TextView>(com.dicoding.picodiploma.loginwithanimation.R.id.tvItem)
        val tvDescription: TextView = holder.itemView.findViewById(com.dicoding.picodiploma.loginwithanimation.R.id.tvDescription)

        val story = getItem(position)
        holder.bind(story)

        holder.itemView.setOnClickListener {
            val title = story.name
            val photo = story.photoUrl
            val description = story.description

            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("title", title)
            intentDetail.putExtra("photo", photo)
            intentDetail.putExtra("description", description)

            val optionsCompat: ActivityOptionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    holder.itemView.context as Activity,
                    Pair(imgPhoto, "profile"),
                    Pair(tvName, "name"),
                    Pair(tvDescription, "description")
                )
            holder.itemView.context.startActivity(intentDetail,optionsCompat.toBundle())
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListStoryItem>() {
            override fun areItemsTheSame(
                oldItem: ListStoryItem,
                newItem: ListStoryItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ListStoryItem,
                newItem: ListStoryItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
