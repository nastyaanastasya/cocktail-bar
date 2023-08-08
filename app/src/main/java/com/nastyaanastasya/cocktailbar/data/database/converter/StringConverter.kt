package com.nastyaanastasya.cocktailbar.data.database.converter

import androidx.room.TypeConverter
import java.util.stream.Collectors

class StringConverter {

    @TypeConverter
    fun convertToString(list: List<String>): String {
        return list.stream().collect(Collectors.joining(","))
    }

    @TypeConverter
    fun convertFromString(str: String): List<String> {
        return str.split(",")
    }
}
