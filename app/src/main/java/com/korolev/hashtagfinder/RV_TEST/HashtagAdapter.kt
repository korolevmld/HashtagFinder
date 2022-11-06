package com.korolev.hashtagfinder.RV_TEST

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.korolev.hashtagfinder.Activity.CheckingPostActivity
import com.korolev.hashtagfinder.R
import com.korolev.hashtagfinder.databinding.HashtagInfoItemBinding
import com.korolev.hashtagfinder.model.HashtagInfo
import com.korolev.hashtagfinder.HashFinderRepository
import okhttp3.internal.Internal

class HashtagAdapter:RecyclerView.Adapter<HashtagAdapter.HashtagViewHolder>() {

   private val hashFinderRepository = HashFinderRepository.get()

   private var hashtagList: ArrayList<HashtagInfo?> = hashFinderRepository.getAllHashtag() as ArrayList<HashtagInfo?>

   inner class HashtagViewHolder(item: View): RecyclerView.ViewHolder(item) {
       val binding = HashtagInfoItemBinding.bind(item)
       fun bind(hashtagInfo: HashtagInfo?,index: Int)
       {
           binding.tvHashtagName.text = hashtagInfo?.hashtagName
           binding.tvServiceName.text = hashtagInfo?.serviceName

           binding.ivDelete.setOnClickListener {
               deleteItem(index)
           }

           binding.ivCheck.setOnClickListener {
                val intent = Intent(itemView.context,CheckingPostActivity::class.java)
                intent.putExtra("hashtagName",binding.tvHashtagName.text)
                itemView.context.startActivity(intent)

           }

       }


   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HashtagViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.hashtag_info_item,parent,false)
        return HashtagViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: HashtagViewHolder, position: Int) {
        holder.bind(hashtagList[position],position)
    }

    override fun getItemCount(): Int {
        return hashtagList.size
    }

    fun deleteItem(index: Int){

        val hashtagName = hashtagList[index]?.hashtagName.toString()
        val serviceName = hashtagList[index]?.serviceName.toString()


        if (hashtagName != null && serviceName != null) {
            hashtagList.removeAt(index)


            hashFinderRepository.deleteHashtag(hashtagName, serviceName)
        }
        notifyDataSetChanged()
    }




}