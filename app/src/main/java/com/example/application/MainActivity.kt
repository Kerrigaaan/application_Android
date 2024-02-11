package com.example.application

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonForm = findViewById<Button>(R.id.buttonRemplir)
        buttonForm.setOnClickListener {
            val newIntent = Intent(application, FormActivity::class.java)
            startActivity(newIntent)
        }

        val buttonQr = findViewById<Button>(R.id.buttonQr)
        buttonQr.setOnClickListener {

            val integrator = IntentIntegrator(this@MainActivity)
            integrator.setPrompt("Scan")
            integrator.setBeepEnabled(true)
            integrator.setOrientationLocked(true)
            integrator.initiateScan()
        }
    }

    private lateinit var scannedContent: String

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IntentIntegrator.REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {

                    scannedContent = "Scan faild"
                } else {

                    scannedContent = result.contents

                    val intent = Intent(this@MainActivity, FormActivity::class.java)
                    intent.putExtra("Data", scannedContent)
                    startActivity(intent)
                }
            }
        }
    }
}

