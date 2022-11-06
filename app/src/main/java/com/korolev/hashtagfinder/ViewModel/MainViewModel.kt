package com.korolev.hashtagfinder.ViewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.korolev.hashtagfinder.HashFinderRepository
import org.json.JSONObject


const val VK_API_KEY = "da628999da628999da62899943da1eef8edda62da628999b8086ce1ee1a319457d67969"



class MainViewModel:ViewModel() {


    private val hashFinderRepository: HashFinderRepository = HashFinderRepository.get()



    fun getVkData(hashtagName: String,context: Context,count:Int) {

        val url = "https://api.vk.com/method/newsfeed.search?" +
                "q=%23" +
                hashtagName +
                "&v=5.131" +
                "&extended=0" +
                "&post_type=post" +
                "&count=" +
                count +
                "&access_token=" +
                VK_API_KEY
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {

                result -> parseHashtagData(hashtagName, result)//Log.d("ApiLog","Result: ${JSONObject(result).get("response")}")//parseHashtagData(hashtagName, result)
            },
            {
                error -> Log.d("ApiLog","Error: $error")
            }
        )

        queue.add(request)



    }


    fun parseHashtagData (hashtagName: String,result:String){
        val mainObject = JSONObject(result)
        val response = mainObject.getJSONObject("response")
        val arrayList = response.getJSONArray("items")

        var i = 0
        while (i <= arrayList.length()-1) {
            val item = arrayList.getJSONObject(i)

            if(item.has("attachments")) {
                val attachmentsArray = item.getJSONArray("attachments")
                val attachment = attachmentsArray.getJSONObject(0)
                val infoAboutAttachment = attachment.getString("type")

                if (infoAboutAttachment == "photo") {
                    val photo = attachment.getJSONObject("photo")
                    val sizeArray = photo.getJSONArray("sizes")
                    val img = sizeArray.getJSONObject(0)
                    val url = img.getString("url")
                    Log.d("url", "$url")
                    hashFinderRepository.insertPost(hashtagName, item.getString("text"), url)
                } else {
                    hashFinderRepository.insertPost(hashtagName, item.getString("text"), "0")
                }

            }
            else
            {
                hashFinderRepository.insertPost(hashtagName, item.getString("text"), "0")
            }

            i++
        }

        }
}