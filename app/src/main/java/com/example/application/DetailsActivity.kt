package com.example.application

import android.content.SharedPreferences
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class DetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        showBack()
        setHeaderTitle(getString(R.string.txtCompte))

        val editNom = findViewById<EditText>(R.id.editTextNom)
        val editPrenom = findViewById<EditText>(R.id.editTextPrenom)
        val editEmail = findViewById<EditText>(R.id.editTextEmail)
        val editAdresse = findViewById<EditText>(R.id.editTextAdresse)
        val editCodePostal = findViewById<EditText>(R.id.editTextCodePostal)
        val editVille = findViewById<EditText>(R.id.editTextVille)
        val buttonEnregistrer = findViewById<Button>(R.id.buttonEnregistrer)


        editNom.setText(readSharedPref("nom"))
        editPrenom.setText(readSharedPref("prenom"))
        editEmail.setText(readSharedPref("email"))
        editAdresse.setText(readSharedPref("adresse"))
        editCodePostal.setText(readSharedPref("codePostal"))
        editVille.setText(readSharedPref("ville"))

        buttonEnregistrer.setOnClickListener {
            // Enregistrer les nouvelles valeurs saisies
            writeSharedPref("nom", editNom.text.toString())
            writeSharedPref("prenom", editPrenom.text.toString())
            writeSharedPref("email", editEmail.text.toString())
            writeSharedPref("adresse", editAdresse.text.toString())
            writeSharedPref("codePostal", editCodePostal.text.toString())
            writeSharedPref("ville", editVille.text.toString())

            Toast.makeText(this, "Données enregistrées avec succès", Toast.LENGTH_SHORT).show()
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
        return sharedPreferences.getString(key, "") ?: ""
    }


}