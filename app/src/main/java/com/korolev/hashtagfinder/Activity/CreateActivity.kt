package com.korolev.hashtagfinder.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.korolev.hashtagfinder.HashFinderRepository
import com.korolev.hashtagfinder.ViewModel.CreateViewModel
import com.korolev.hashtagfinder.databinding.ActivityCreateBinding

class CreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBinding

    private val createViewModel: CreateViewModel by lazy {
        ViewModelProviders.of(this).get(CreateViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btAddHashtag.setOnClickListener {
            createViewModel.insertHashtag(binding.etHashtag.text.toString(),binding.spinnerService.selectedItem.toString())
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }



    }
}