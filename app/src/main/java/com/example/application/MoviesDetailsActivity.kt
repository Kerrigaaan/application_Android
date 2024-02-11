package com.example.application

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class MoviesDetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_movies)
        showBack()
        val title = intent.getStringExtra("film_title")
        setHeaderTitle(title)
        val description = intent.getStringExtra("film_description")
        val backdropUrl = intent.getStringExtra("film_img")
        val runtime = intent.getStringExtra("film_time")


        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)
        val runTimTextView: TextView = findViewById(R.id.runTimeTextView)
        val backdropUrlImageView: ImageView = findViewById(R.id.backdropUrlImageView)

        descriptionTextView.text = description
        runTimTextView.text = runtime
        Glide.with(backdropUrlImageView.context).load(backdropUrl).into(backdropUrlImageView)

        writeSharedPref("film_title", title ?: "")
        writeSharedPref("film_description", description ?: "")
        writeSharedPref("film_img", backdropUrl ?: "")
        writeSharedPref("film_time", runtime ?: "")

        findViewById<Button>(R.id.buttonAjouter).setOnClickListener {
            val intent = Intent(this@MoviesDetailsActivity, HomeActivity::class.java)
            intent.putExtra("film_title", title)
            intent.putExtra("film_description", description)
            intent.putExtra("film_img", backdropUrl)



            startActivity(intent)
        }
    }


    private fun writeSharedPref(key: String, value: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }
}