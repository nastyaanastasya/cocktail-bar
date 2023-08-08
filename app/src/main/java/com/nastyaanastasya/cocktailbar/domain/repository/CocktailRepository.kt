package com.nastyaanastasya.cocktailbar.domain.repository

import com.nastyaanastasya.cocktailbar.domain.model.CocktailDto
import com.nastyaanastasya.cocktailbar.domain.model.CocktailSimpleDto

interface CocktailRepository {

    suspend fun getAll(): List<CocktailSimpleDto>
    suspend fun getById(id: Int): CocktailDto
    suspend fun save(cocktailDto: CocktailDto)
    suspend fun edit(cocktailDto: CocktailDto)
    suspend fun delete(cocktailDto: CocktailDto)
}
