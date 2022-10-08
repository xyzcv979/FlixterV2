package com.example.flixterv2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray


private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

/*
 * The class for the only fragment in the app, which contains the
 * recyclerView and performs the network calls to the Movie Database DB.
 */
class MovieFragment : Fragment(), OnListFragmentInteractionListener  {
    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_view, container, false)
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = LinearLayoutManager(context)
        updateAdapter(recyclerView)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(recyclerView: RecyclerView) {
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY

        client["https://api.themoviedb.org/3/trending/movie/week", params, object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                // Access a JSON array response with `json.jsonArray`
                val resultsJSON : JSONArray = json.jsonObject.get("results") as JSONArray
                val moviesRawJSON : String = resultsJSON.toString()
                val gson = Gson()
                val arrayMovieType = object : TypeToken<List<Movie>>() {}.type

                val models : List<Movie> = gson.fromJson(moviesRawJSON, arrayMovieType)
                recyclerView.adapter = MovieRecyclerViewAdapter(models, this@MovieFragment)

                Log.d("MovieFragment", "response successful")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
                // If the error is not null, log it!
                throwable?.message?.let {
                    Log.e("MovieFragment Error", response)
                }
            }
        }]

    }

    override fun onItemClick(movie: Movie) {
        val intent = Intent(context, DetailActivity::class.java)

        intent.putExtra("title", movie.title)
        intent.putExtra("image", movie.poster_path)
        intent.putExtra("overview", movie.overview)
        intent.putExtra("release_date", movie.release_date)
        intent.putExtra("popularity", movie.popularity)
        intent.putExtra("vote_average", movie.vote_average)

        context?.startActivity(intent)
    }
}