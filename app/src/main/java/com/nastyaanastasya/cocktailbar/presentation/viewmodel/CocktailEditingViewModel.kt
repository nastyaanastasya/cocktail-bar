package com.nastyaanastasya.cocktailbar.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastyaanastasya.cocktailbar.domain.model.CocktailDto
import com.nastyaanastasya.cocktailbar.domain.usecase.RemoveCocktailUseCase
import com.nastyaanastasya.cocktailbar.domain.usecase.EditCocktailUseCase
import com.nastyaanastasya.cocktailbar.domain.usecase.SaveCocktailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CocktailEditingViewModel @Inject constructor(
    private val removeCocktailUseCase: RemoveCocktailUseCase,
    private val editCocktailUseCase: EditCocktailUseCase,
    private val saveCocktailUseCase: SaveCocktailUseCase
): ViewModel() {

    fun save(cocktailDto: CocktailDto) {
        viewModelScope.launch {
            kotlin.runCatching {
                saveCocktailUseCase(cocktailDto)
            }
        }
    }

    fun edit(cocktailDto: CocktailDto) {
        viewModelScope.launch {
            kotlin.runCatching {
                editCocktailUseCase(cocktailDto)
            }
        }
    }

    fun remove(cocktailDto: CocktailDto) {
        viewModelScope.launch {
            kotlin.runCatching {
                removeCocktailUseCase(cocktailDto)
            }
        }
    }
}
