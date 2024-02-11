package com.example.application

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide




private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class PanierActivity: Fragment() {

    private lateinit var sharedPreferences: SharedPreferences


    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return  inflater.inflate(R.layout.activity_panier, container, false)

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("account", Context.MODE_PRIVATE)

        val title = readSharedPref("film_title")
        val description = readSharedPref("film_description")
        val backdropUrl = readSharedPref("film_img")


        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val backdropUrlImageView: ImageView = view.findViewById(R.id.backdropUrlImageView)
        val emptyCartTextView: TextView = view.findViewById(R.id.emptyCartTextView)
        val cartLayout: LinearLayout = view.findViewById(R.id.cartLayout)
        val deleteImageView: ImageView = view.findViewById(R.id.Supprimer)

        titleTextView.text = title
        descriptionTextView.text = description
        Glide.with(backdropUrlImageView.context).load(backdropUrl).into(backdropUrlImageView)


        if (title.isNotEmpty()) {
            titleTextView.text = title
            descriptionTextView.text = description
            Glide.with(backdropUrlImageView.context).load(backdropUrl).into(backdropUrlImageView)
            emptyCartTextView.visibility = View.GONE
            cartLayout.visibility = View.VISIBLE
        } else {
            emptyCartTextView.visibility = View.VISIBLE
            cartLayout.visibility = View.GONE
        }
        deleteImageView.setOnClickListener {

            deleteFromCart()
        }
    }

    private fun deleteFromCart() {

        val editor = sharedPreferences.edit()
        editor.remove("film_title")
        editor.remove("film_description")
        editor.remove("film_img")
        editor.apply()


        val titleTextView: TextView = requireView().findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = requireView().findViewById(R.id.descriptionTextView)
        val backdropUrlImageView: ImageView = requireView().findViewById(R.id.backdropUrlImageView)
        val emptyCartTextView: TextView = requireView().findViewById(R.id.emptyCartTextView)
        val cartLayout: LinearLayout = requireView().findViewById(R.id.cartLayout)

        titleTextView.text = ""
        descriptionTextView.text = ""
        backdropUrlImageView.setImageDrawable(null)
        emptyCartTextView.visibility = View.VISIBLE
        cartLayout.visibility = View.GONE
    }

    private fun readSharedPref(key: String): String {

        return sharedPreferences.getString(key, "") ?: ""
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PanierActivity().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}