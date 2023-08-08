package com.nastyaanastasya.cocktailbar.domain.model

import android.graphics.Bitmap

data class CocktailSimpleDto(
    val id: Int,
    var image: Bitmap?,
    var name: String
)
