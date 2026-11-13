package com.example.artspaceapp.data

import com.example.artspaceapp.R
import com.example.artspaceapp.model.Artwork

object ArtworkDatasource {
    val artworks = listOf(
        Artwork(R.drawable.art_1, R.string.art_1_title, R.string.art_1_artist, "2019"),
        Artwork(R.drawable.art_2, R.string.art_2_title, R.string.art_2_artist, "2021"),
        Artwork(R.drawable.art_3, R.string.art_3_title, R.string.art_3_artist, "2018"),
        Artwork(R.drawable.art_4, R.string.art_4_title, R.string.art_4_artist, "2022"),
        Artwork(R.drawable.art_5, R.string.art_5_title, R.string.art_5_artist, "2020")
    )
}
