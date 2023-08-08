package com.nastyaanastasya.cocktailbar.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nastyaanastasya.cocktailbar.data.database.converter.BitmapConverter
import com.nastyaanastasya.cocktailbar.data.database.converter.DateConverter
import com.nastyaanastasya.cocktailbar.data.database.converter.StringConverter
import com.nastyaanastasya.cocktailbar.data.database.dao.CocktailDao
import com.nastyaanastasya.cocktailbar.data.database.entity.Cocktail

@Database(
    entities = [Cocktail::class],
    version = 1
)
@TypeConverters(
    BitmapConverter::class,
    DateConverter::class,
    StringConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cocktailDao(): CocktailDao

    companion object {
        private const val DB_NAME = "app.db"

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context?) = instance ?: synchronized(LOCK) {
            context?.let {
                buildDatabase(it).apply {
                    instance = this
                }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}
