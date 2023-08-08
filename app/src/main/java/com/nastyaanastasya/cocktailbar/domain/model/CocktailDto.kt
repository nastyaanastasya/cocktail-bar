package com.nastyaanastasya.cocktailbar.domain.model

import android.graphics.Bitmap

data class CocktailDto(
    val id: Int,
    var image: Bitmap?,
    var name: String,
    var desc: String?,
    var recipe: String?,
    val ingredients: List<String>
)
