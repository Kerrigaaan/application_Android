package com.example.application

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import org.json.JSONObject


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CardFragment : Fragment() {

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
        return inflater.inflate(R.layout.activity_card_fragment, container, false)
    }

    private lateinit var sharedPreferences: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        sharedPreferences = requireContext().getSharedPreferences("account", Context.MODE_PRIVATE)


        val nom = readSharedPref("nom")
        val prenom = readSharedPref("prenom")
        val email = readSharedPref("email")
        val adresse = readSharedPref("adresse")
        val codePostal = readSharedPref("codePostal")
        val ville = readSharedPref("ville")
        val cateDeFidelite = readSharedPref("cateDeFidelite")

        val textViewNom = view.findViewById<TextView>(R.id.editTextNom)
        val textViewPrenom = view.findViewById<TextView>(R.id.editTextPrenom)

        textViewNom.text = nom
        textViewPrenom.text = prenom


        val jsonObject = JSONObject().apply {
            put("nom", nom)
            put("prenom", prenom)
            put("email", email)
            put("adresse", adresse)
            put("codePostal", codePostal)
            put("ville", ville)
            put("cateDeFidelite", cateDeFidelite)
        }


        val bitmap = generateQRCode(jsonObject.toString())


        val imageViewQRCode = view.findViewById<ImageView>(R.id.imageViewQRCode)


        imageViewQRCode.setImageBitmap(bitmap)
    }

    private fun readSharedPref(key: String): String {

        return sharedPreferences.getString(key, "") ?: ""
    }
    private fun generateQRCode(data: String): Bitmap? {
        try {
            val bitMatrix: BitMatrix = MultiFormatWriter().encode(
                data,
                BarcodeFormat.QR_CODE,
                300,
                300
            )
            val width: Int = bitMatrix.width
            val height: Int = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x, y, if (bitMatrix[x, y]) 0xFF000000.toInt() else 0xFFFFFFFF.toInt())
                }
            }
            return bmp
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        return null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}