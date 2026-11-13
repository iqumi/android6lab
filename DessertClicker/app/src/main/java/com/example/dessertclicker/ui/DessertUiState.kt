package com.example.dessertclicker.ui

import com.example.dessertclicker.data.Datasource

data class DessertUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertPrice: Int = Datasource.dessertList.first().price,
    val currentDessertImageId: Int = Datasource.dessertList.first().imageId
)
