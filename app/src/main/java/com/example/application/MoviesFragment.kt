package com.example.application


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.CacheControl
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MoviesFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val films= arrayListOf<Movies>()
        val recyclerViewStudents= view.findViewById<RecyclerView>(R.id.recyclerViewStudents)
        recyclerViewStudents.layoutManager = LinearLayoutManager(requireContext())
        val FilmsAdapter= MoviesAdapter(films)
        recyclerViewStudents.adapter=FilmsAdapter
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestUrl= "https://ugarit-online.000webhostapp.com/epsi/films/movies.json"
        val request= Request.Builder().url(mRequestUrl).get().cacheControl(CacheControl.FORCE_NETWORK).build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("FilmsFragment", "Error fetching data: ${e.message}")
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val data = response.body?.string()?.trim()
                    if (data != null) {
                        val jsFilms = JSONObject(data)
                        if (jsFilms.has("movies")) {
                            val jsArrayFilms = jsFilms.getJSONArray("movies")
                            for (i in 0 until jsArrayFilms.length()) {
                                val js = jsArrayFilms.getJSONObject(i)
                                val student = Movies(
                                    js.optString("title", ""),
                                    js.optString("description", ""),
                                    js.optString("runTime", ""),
                                    js.optString("graphicUrl", ""),
                                    js.optString("backdropUrl", "")
                                )
                                films.add(student)
                            }
                            requireActivity().runOnUiThread {
                                FilmsAdapter.notifyDataSetChanged()
                            }
                        } else {
                            Log.e("FilmsFragment", "No 'movies' key found in JSON response")
                        }
                    }
                } else {
                    Log.e("FilmsFragment", "Unsuccessful response: ${response.code}")
                }
            }


        })

        Log.d("Films","   films.count() :"+films.count())

    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}