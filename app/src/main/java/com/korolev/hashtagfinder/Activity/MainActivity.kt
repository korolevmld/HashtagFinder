package com.korolev.hashtagfinder.Activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.korolev.hashtagfinder.HashFinderRepository
import com.korolev.hashtagfinder.RV_TEST.HashtagAdapter
import com.korolev.hashtagfinder.ViewModel.MainViewModel
import com.korolev.hashtagfinder.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private val hashFinderRepository: HashFinderRepository = HashFinderRepository.get()
    private lateinit var binding: ActivityMainBinding
    private val adapter = HashtagAdapter()

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = Intent(this,CreateActivity::class.java)

        binding.apply {
            rvHashtag.layoutManager=LinearLayoutManager(this@MainActivity)
            rvHashtag.adapter = adapter


            btItem.setOnClickListener{


                startActivity(intent)

            }


            btGetData.setOnClickListener {
                val hashtagInfo = hashFinderRepository.getAllHashtag()

                hashFinderRepository.deleteAllPost()

                binding.progressBar.visibility = View.VISIBLE

                val count = hashtagInfo.size-1


                for (i in hashtagInfo.indices)
                {
                mainViewModel.getVkData(hashtagInfo[i]?.hashtagName.toString(),this@MainActivity,3)

                    if(i==count)
                    {
                        delayProgress()
                    }

                }

                }


            }



        }




    private fun delayProgress() {

        val handler = Handler()
        var seconds = 1
        handler.post(
            object : Runnable {
                override fun run() {

                    seconds++

                    if(seconds==3) {
                        binding.progressBar.visibility = View.INVISIBLE
                    }

                    handler.postDelayed(this,1000)
                }
            }
        )

    }

    }



    //Переопределене кнопки назад (Если нужно будет)
    /*
    override fun onBackPressed() {
        val intent = Intent();
        intent.action = Intent.ACTION_MAIN;
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);

    }
    */

