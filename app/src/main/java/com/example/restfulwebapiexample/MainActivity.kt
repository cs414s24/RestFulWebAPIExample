package com.example.restfulwebapiexample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import com.koushikdutta.ion.Ion

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun downloadFunnyImage(view: View) {
        // Download the data from the specified URL
        Ion.with(this)
            .load("https://yesno.wtf/api")
            .asString()
            .setCallback { e, result ->
                // Write code to process the result
                // e parameter stores the exception if any
                Log.d(TAG, "The received data : $result")

                // Helper function to parse/process data
                parseFunnyImageData(result)
            }
    }


    private fun parseFunnyImageData(result: String){
        // Extract the information from JSON data

        // Received data will be in JSON format as shown below
        /*        {
                    "answer": "yes",
                    "forced": false,
                    "image": "https://yesno.wtf/assets/yes/13-c3082a998e7758be8e582276f35d1336.gif"
        }*/
        val data = JSONObject(result)
        val img = data.getString("image")
        val yesOrNo = data.getString("answer")
        //Log.d(TAG, "The image url : $img")

        // Display the image using Ion library, alternatively Glide library can be used
        val imageView = findViewById<ImageView>(R.id.image_view)
        Ion.with(imageView).load(img)

        // Display the yes/no text as well
        findViewById<TextView>(R.id.tv_yes_no).text = yesOrNo
    }
}