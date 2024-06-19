package com.example.tedmobproject.helpers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tedmobproject.Post
import com.example.tedmobproject.databinding.ItemPostBinding

class PostsAdapter(
    private val onItemClick: (Post) -> Unit,
    private val onShowMoreClick: (Post) -> Unit
) : ListAdapter<Post, PostsAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
        holder.itemView.setOnClickListener { onItemClick(post) }
    }

    inner class PostViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.postTitle.text = post.title
            binding.postBody.text = post.body
            binding.showMore.setOnClickListener { onShowMoreClick(post) }
        }
    }

    companion object {
        private const val TAG = "PostsAdapter"
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem
}

