package com.example.artspaceapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Artwork(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val artist: Int,
    val year: String
)
