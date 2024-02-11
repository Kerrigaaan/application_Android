package com.example.application

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class HomeActivity : BaseActivity() {

    val PanierActivity= com.example.application.PanierActivity.newInstance("","")
    val CardFragment= com.example.application.CardFragment.newInstance("","")
    val MapActivity= com.example.application.MapActivity.newInstance("","")
    val MoviesFragment= com.example.application.MoviesFragment.newInstance("","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setHeaderTitle(getString(R.string.txtLogo))


        val nom = intent.getStringExtra("nom")
        val prenom = intent.getStringExtra("prenom")
        val email = intent.getStringExtra("email")
        val adresse = intent.getStringExtra("adresse")
        val codePostal = intent.getStringExtra("codePostal")
        val ville = intent.getStringExtra("ville")

        val userLogoCompte = findViewById<ImageView>(R.id.imageUserLogo)
        userLogoCompte.visibility = View.VISIBLE

        userLogoCompte.setOnClickListener {
            val intent = Intent(this@HomeActivity, DetailsActivity::class.java)
            intent.putExtra("nom", nom)
            intent.putExtra("prenom", prenom)
            intent.putExtra("email", email)
            intent.putExtra("adresse", adresse)
            intent.putExtra("codePostal", codePostal)
            intent.putExtra("ville", ville)


            startActivity(intent)
        }
        val movies= findViewById<TextView>(R.id.textViewTab1)
        showFilms()

        movies.setOnClickListener {
            showFilms()
        }
        val salles= findViewById<TextView>(R.id.textViewTab2)

        salles.setOnClickListener {
            showSalles()
        }

        val card= findViewById<TextView>(R.id.textViewTab3)
        card.setOnClickListener {
            showCard()
        }

        val panier= findViewById<TextView>(R.id.textViewTab4)
        panier.setOnClickListener {
            showPanier()
        }

    }

    fun showFilms() {
        val frManager = supportFragmentManager
        val fragmentTra = frManager.beginTransaction()
        fragmentTra.addToBackStack("Films")
        fragmentTra.replace(R.id.layoutContent, MoviesFragment)
        fragmentTra.commit()
    }

    fun showSalles() {
        val frManager = supportFragmentManager
        val fragmentTra = frManager.beginTransaction()
        fragmentTra.addToBackStack("Salles")
        fragmentTra.replace(R.id.layoutContent, MapActivity)
        fragmentTra.commit()
    }

    fun showCard() {
        val frManager = supportFragmentManager
        val fragmentTra = frManager.beginTransaction()
        fragmentTra.addToBackStack("Card")
        fragmentTra.replace(R.id.layoutContent, CardFragment)
        fragmentTra.commit()
    }

    fun showPanier() {
        val frManager = supportFragmentManager
        val fragmentTra = frManager.beginTransaction()
        fragmentTra.addToBackStack("Panier")
        fragmentTra.replace(R.id.layoutContent, PanierActivity)
        fragmentTra.commit()

    }
}


