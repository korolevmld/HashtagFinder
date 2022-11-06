package com.korolev.hashtagfinder.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.korolev.hashtagfinder.RV_TEST.HashtagAdapter
import com.korolev.hashtagfinder.RV_TEST.PostAdapter
import com.korolev.hashtagfinder.databinding.ActivityCheckingPostBinding

class CheckingPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckingPostBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        Log.d("in",intent.getStringExtra("hashtagName").toString())

        val adapter = PostAdapter(intent)

        binding = ActivityCheckingPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            rvPost.layoutManager = LinearLayoutManager(this@CheckingPostActivity)
            rvPost.adapter = adapter
        }


    }
}