package com.nastyaanastasya.cocktailbar.data.repository

import com.nastyaanastasya.cocktailbar.data.database.dao.CocktailDao
import com.nastyaanastasya.cocktailbar.data.database.entity.Cocktail
import com.nastyaanastasya.cocktailbar.domain.mapper.ModelMapper
import com.nastyaanastasya.cocktailbar.domain.model.CocktailDto
import com.nastyaanastasya.cocktailbar.domain.model.CocktailSimpleDto
import com.nastyaanastasya.cocktailbar.domain.repository.CocktailRepository
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val cocktailDao: CocktailDao,
    private val cocktailMapper: ModelMapper<Cocktail, CocktailDto>,
    private val cocktailSimpleMapper: ModelMapper<Cocktail, CocktailSimpleDto>,
) : CocktailRepository {

    override suspend fun getAll(): List<CocktailSimpleDto> {
        val cocktails = mutableListOf<CocktailSimpleDto>()
        cocktailDao.getAll()?.forEach {
            cocktails.add(cocktailSimpleMapper.map(it))
        }
        return cocktails
    }

    override suspend fun getById(id: Int) = cocktailMapper.map(getCocktailById(id))

    override suspend fun save(cocktailDto: CocktailDto) = cocktailDao.insert(
        Cocktail(
            id = cocktailDto.id,
            image = cocktailDto.image,
            name = cocktailDto.name,
            desc = cocktailDto.desc,
            recipe = cocktailDto.recipe,
            ingredients = cocktailDto.ingredients,
            dateOfEditing = Calendar.getInstance().time
        )
    )

    override suspend fun edit(cocktailDto: CocktailDto) {
        val cocktail = cocktailDao.getById(cocktailDto.id)
        with(cocktailDto) {
            cocktail.desc = desc
            cocktail.image = image
            cocktail.name = name
            cocktail.ingredients = ingredients
            cocktail.recipe = recipe
            cocktail.dateOfEditing = Calendar.getInstance().time
        }
        cocktailDao.update(cocktail)
    }

    override suspend fun delete(cocktailDto: CocktailDto) =
        cocktailDao.delete(getCocktailById(cocktailDto.id))

    private suspend fun getCocktailById(id: Int) = cocktailDao.getById(id)
}
