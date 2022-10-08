package com.example.flixterv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    private lateinit var detail_image: ImageView
    private lateinit var detail_title: TextView
    private lateinit var detail_overview: TextView
    private lateinit var detail_release_date: TextView
    private lateinit var detail_popularity: TextView
    private lateinit var detail_vote_average: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        detail_image = findViewById(R.id.detail_image)
        detail_title = findViewById(R.id.detail_title)
        detail_overview = findViewById(R.id.detail_overview)
        detail_release_date  = findViewById(R.id.detail_release_date)
        detail_popularity = findViewById(R.id.detail_popularity)
        detail_vote_average = findViewById(R.id.detail_vote_average)


        var movie_path = getIntent().getStringExtra("image")
        detail_title.text = getIntent().getStringExtra("title")
        detail_overview.text = getIntent().getStringExtra("overview")
        detail_release_date.text = "Release date: " + getIntent().getStringExtra("release_date")
        detail_popularity.text = "Popularity: " + getIntent().getStringExtra("popularity")
        detail_vote_average.text = "Vote Average: " + getIntent().getStringExtra("vote_average")


        //Load the media image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + movie_path)
            .centerInside()
            .into(detail_image)
    }
}