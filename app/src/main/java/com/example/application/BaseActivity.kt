package com.example.application


import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Epsi","########## onCreate ##########"+this.javaClass.simpleName)
    }

    override fun onStart() {
        super.onStart()
        Log.d("Epsi","########## onStart ##########"+this.javaClass.simpleName)
    }

    override fun onResume() {
        super.onResume()
        Log.d("Epsi","########## onResume ##########"+this.javaClass.simpleName)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Epsi","########## onRestart ##########"+this.javaClass.simpleName)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Epsi","########## onDestroy ##########"+this.javaClass.simpleName)
    }

    override fun onStop() {
        super.onStop()
        Log.d("Epsi","########## onStop ##########"+this.javaClass.simpleName)
    }

    override fun onPause() {
        super.onPause()
        Log.d("Epsi","########## onPause ##########"+this.javaClass.simpleName)
    }
    override fun finish() {
        super.finish()
        Log.d("Epsi","########## finish ##########"+this.javaClass.simpleName)
    }
    fun showBack() {
        val imageViewBack = findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.visibility = View.VISIBLE
        imageViewBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    fun setHeaderTitle(title:String?){
        if (title != null) {
            val textView = findViewById<TextView>(R.id.textViewTitle)
            textView.text = title
        }
    }

}