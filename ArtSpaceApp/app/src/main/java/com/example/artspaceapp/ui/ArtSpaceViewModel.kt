package com.example.artspaceapp.ui

import androidx.lifecycle.ViewModel
import com.example.artspaceapp.data.ArtworkDatasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ArtSpaceViewModel : ViewModel() {

    private val artworks = ArtworkDatasource.artworks

    private val _uiState = MutableStateFlow(ArtSpaceUiState())
    val uiState: StateFlow<ArtSpaceUiState> = _uiState.asStateFlow()

    fun showNextArtwork() {
        showArtworkAt((_uiState.value.currentIndex + 1) % artworks.size)
    }

    fun showPreviousArtwork() {
        showArtworkAt((_uiState.value.currentIndex - 1 + artworks.size) % artworks.size)
    }

    private fun showArtworkAt(index: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                currentIndex = index,
                currentArtwork = artworks[index]
            )
        }
    }
}
