package com.nastyaanastasya.cocktailbar.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nastyaanastasya.cocktailbar.data.database.entity.Cocktail

@Dao
interface CocktailDao {

    @Query("SELECT * FROM cocktail ORDER BY date")
    suspend fun getAll(): List<Cocktail>?

    @Query("SELECT * FROM cocktail WHERE id = :id")
    suspend fun getById(id: Int): Cocktail

    @Insert
    suspend fun insert(cocktail: Cocktail)

    @Update
    suspend fun update(cocktail: Cocktail)

    @Delete
    suspend fun delete(cocktail: Cocktail)
}
