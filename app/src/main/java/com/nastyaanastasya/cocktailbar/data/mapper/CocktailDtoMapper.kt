package com.nastyaanastasya.cocktailbar.data.mapper

import com.nastyaanastasya.cocktailbar.data.database.entity.Cocktail
import com.nastyaanastasya.cocktailbar.domain.mapper.ModelMapper
import com.nastyaanastasya.cocktailbar.domain.model.CocktailDto
import javax.inject.Inject

class CocktailDtoMapper @Inject constructor() : ModelMapper<Cocktail, CocktailDto> {

    override fun map(source: Cocktail) = CocktailDto(
        id = source.id,
        image = source.image,
        name = source.name,
        desc = source.desc,
        recipe = source.recipe,
        ingredients = source.ingredients
    )
}
