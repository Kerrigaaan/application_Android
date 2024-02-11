package com.example.application

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Context
import android.content.SharedPreferences
import org.json.JSONObject

class FormActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        showBack()

        val editNom=findViewById<EditText>(R.id.editTextNom)
        val editPrenom=findViewById<EditText>(R.id.editTextPrenom)
        val editEmail=findViewById<EditText>(R.id.editTextEmail)
        val editAdresse=findViewById<EditText>(R.id.editTextAdresse)
        val editCodePostal=findViewById<EditText>(R.id.editTextCodePostal)
        val editVille=findViewById<EditText>(R.id.editTextVille)
        val editCateDeFidelite=findViewById<EditText>(R.id.editTextCarteDeFidelite)
        val buttonCreate= findViewById<Button>(R.id.buttonCreer)

        editNom.setText(readSharedPref("nom"))
        editPrenom.setText(readSharedPref("prenom"))
        editEmail.setText(readSharedPref("email"))
        editAdresse.setText(readSharedPref("adresse"))
        editCodePostal.setText(readSharedPref("codePostal"))
        editCateDeFidelite.setText(readSharedPref("cateDeFidelite"))
        editVille.setText(readSharedPref("ville"))

        buttonCreate.setOnClickListener {

            writeSharedPref("nom", editNom.text.toString())
            writeSharedPref("prenom", editPrenom.text.toString())
            writeSharedPref("email", editEmail.text.toString())
            writeSharedPref("adresse", editAdresse.text.toString())
            writeSharedPref("codePostal", editCodePostal.text.toString())
            writeSharedPref("ville", editVille.text.toString())
            writeSharedPref("cateDeFidelite", editCateDeFidelite.text.toString())

            val intent = Intent(this@FormActivity, HomeActivity::class.java)
            intent.putExtra("nom", editNom.text.toString())
            intent.putExtra("prenom", editPrenom.text.toString())
            intent.putExtra("email", editEmail.text.toString())
            intent.putExtra("adresse", editAdresse.text.toString())
            intent.putExtra("codePostal", editCodePostal.text.toString())
            intent.putExtra("ville", editVille.text.toString())
            startActivity(intent)
        }
    }


    private fun writeSharedPref(key: String, value: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun readSharedPref(key: String): String {
        val sharedPreferences: SharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "").toString()
    }
}

