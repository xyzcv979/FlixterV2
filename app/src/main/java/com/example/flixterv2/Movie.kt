package com.example.flixterv2

import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single movie from the Movie Database API
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */
class Movie {
    @JvmField
    @SerializedName("title")
    var title: String? = null

    @JvmField
    @SerializedName("overview")
    var overview: String? = null

    @JvmField
    @SerializedName("poster_path")
    var poster_path: String? = null

    @JvmField
    @SerializedName("release_date")
    var release_date: String? = null

    @JvmField
    @SerializedName("popularity")
    var popularity: String? = null

    @JvmField
    @SerializedName("vote_average")
    var vote_average: String? = null
}