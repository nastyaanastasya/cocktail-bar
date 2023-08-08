package com.nastyaanastasya.cocktailbar.di.modules

import android.content.Context
import com.nastyaanastasya.cocktailbar.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.invoke(context) as AppDatabase

    @Provides
    fun provideCocktailDao(database: AppDatabase) = database.cocktailDao()
}
