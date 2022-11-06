package com.korolev.hashtagfinder.RV_TEST


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.korolev.hashtagfinder.HashFinderRepository
import com.korolev.hashtagfinder.R
import com.korolev.hashtagfinder.databinding.PostItemBinding


class PostAdapter(intent: Intent): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private val hashFinderRepository = HashFinderRepository.get()


    private val hashtagName: String = intent.getStringExtra("hashtagName").toString()

    private var postList: List<com.korolev.hashtagfinder.data.Post?> = hashFinderRepository.getPostFromHashtagName(hashtagName)

    inner class PostViewHolder(item:View): RecyclerView.ViewHolder(item) {
        val binding = PostItemBinding.bind(item)
        fun bind(post: com.korolev.hashtagfinder.data.Post?, index: Int)
        {
            if(post?.url != "0") {
                Glide.with(itemView).load(post?.url).into(binding.ivPost)
            }
            else
            {
                binding.ivPost.visibility = View.GONE
            }
            binding.tvHashtagName.text = post?.text

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.post_item,parent,false)
        return PostViewHolder(inflater)

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        holder.bind(postList[position],position)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

}