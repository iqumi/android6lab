package com.example.artspaceapp.ui

import com.example.artspaceapp.data.ArtworkDatasource
import com.example.artspaceapp.model.Artwork

data class ArtSpaceUiState(
    val currentIndex: Int = 0,
    val currentArtwork: Artwork = ArtworkDatasource.artworks.first()
)
