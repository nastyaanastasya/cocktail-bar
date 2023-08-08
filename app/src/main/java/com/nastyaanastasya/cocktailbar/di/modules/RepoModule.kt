package com.nastyaanastasya.cocktailbar.di.modules

import com.nastyaanastasya.cocktailbar.data.repository.CocktailRepositoryImpl
import com.nastyaanastasya.cocktailbar.domain.repository.CocktailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    fun bindsCocktailRepo(
        impl: CocktailRepositoryImpl
    ): CocktailRepository
}
