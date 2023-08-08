package com.nastyaanastasya.cocktailbar.data.database.entity

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "cocktail")
data class Cocktail(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var image: Bitmap?,
    var name: String,
    var desc: String?,
    var recipe: String?,
    var ingredients: List<String>,
    @ColumnInfo(name = "date") var dateOfEditing: Date
)
