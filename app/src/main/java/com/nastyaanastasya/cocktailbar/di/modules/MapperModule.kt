package com.nastyaanastasya.cocktailbar.di.modules

import com.nastyaanastasya.cocktailbar.data.database.entity.Cocktail
import com.nastyaanastasya.cocktailbar.data.mapper.CocktailDtoMapper
import com.nastyaanastasya.cocktailbar.data.mapper.CocktailSimpleDtoMapper
import com.nastyaanastasya.cocktailbar.domain.mapper.ModelMapper
import com.nastyaanastasya.cocktailbar.domain.model.CocktailDto
import com.nastyaanastasya.cocktailbar.domain.model.CocktailSimpleDto
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MapperModule {

    @Binds
    fun bindsCocktailDtoMapper(
        impl: CocktailDtoMapper
    ): ModelMapper<Cocktail, CocktailDto>

    @Binds
    fun bindsCocktailSimpleDtoMapper(
        impl: CocktailSimpleDtoMapper
    ): ModelMapper<Cocktail, CocktailSimpleDto>
}
