package com.nastyaanastasya.cocktailbar.data.mapper

import com.nastyaanastasya.cocktailbar.data.database.entity.Cocktail
import com.nastyaanastasya.cocktailbar.domain.mapper.ModelMapper
import com.nastyaanastasya.cocktailbar.domain.model.CocktailSimpleDto
import javax.inject.Inject

class CocktailSimpleDtoMapper @Inject constructor() : ModelMapper<Cocktail, CocktailSimpleDto> {

    override fun map(source: Cocktail) = CocktailSimpleDto(
        id = source.id,
        name = source.name,
        image = source.image
    )
}
