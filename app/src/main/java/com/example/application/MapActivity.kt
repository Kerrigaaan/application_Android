package com.example.application

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONObject


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MapActivity : Fragment() {
    lateinit var googleMap: GoogleMap

    val salles = "{\"salles\":[" +
            "{\"name\":\"Odéon (côté St Michel)\",\"latitude\":44.847807,\"longitude\":-0.579472}," +
            "{\"name\":\"Odéon (côté St Germain)\",\"latitude\":43.293295,\"longitude\":-0.36357}," +
            "{\"name\":\"Beaubourg\",\"latitude\":47.215585,\"longitude\":-1.554908}," +
            "{\"name\":\"Quai de Loire\",\"latitude\":48.8842,\"longitude\":2.3721}," +
            "{\"name\":\"Quai de Seine\",\"latitude\":50.608719,\"longitude\":3.063295}," +
            "{\"name\":\"Parnasse\",\"latitude\":43.293551,\"longitude\":5.377397}," +
            "{\"name\":\"Gambetta\",\"latitude\":45.759132,\"longitude\":4.834604}," +
            "{\"name\":\"Bastille (côté Beaumarchais)\",\"latitude\":43.58612,\"longitude\":3.896094}," +
            "{\"name\":\"Bastille (côté Fg St Antoine)\",\"latitude\":43.533513,\"longitude\":1.411209}," +
            "{\"name\":\"Bibliothèque (entrée BnF)\",\"latitude\":48.389353,\"longitude\":-4.488616}," +
            "{\"name\":\"Bibliothèque\",\"latitude\":45.838771,\"longitude\":1.262108}," +
            "{\"name\":\"Nation\",\"latitude\":45.780535,\"longitude\":3.093242}" +
            "]}";

    private val callback = OnMapReadyCallback { googleMap ->


        //48.856614


        val jsonCities = JSONObject(salles)
        val items = jsonCities.getJSONArray("salles")

        for (i in 0..items.length() - 1) {
            val jsonCity = items.getJSONObject(i)
            val city = MarkerOptions()
            val cityLatLng = LatLng(jsonCity.optDouble("latitude", 0.0), jsonCity.optDouble("longitude", 0.0))
            city.title(jsonCity.optString("name"))
            city.position(cityLatLng)
            googleMap.addMarker(city)
        }

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(48.854885, 2.338646), 5f))

        googleMap.setOnInfoWindowClickListener {
            Toast.makeText(activity,it.title.toString(),Toast.LENGTH_LONG).show()
        }
        this.googleMap = googleMap

    }

    // TODO: Rename and change types of parameters
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapActivity().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}