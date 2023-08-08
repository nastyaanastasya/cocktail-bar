package com.nastyaanastasya.cocktailbar.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastyaanastasya.cocktailbar.domain.model.CocktailDto
import com.nastyaanastasya.cocktailbar.domain.model.CocktailSimpleDto
import com.nastyaanastasya.cocktailbar.domain.usecase.GetCocktailByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getCocktailByIdUseCase: GetCocktailByIdUseCase
): ViewModel() {

    private val _cocktail = MutableStateFlow<CocktailDto?>(null)
    val cocktail: StateFlow<CocktailDto?> = _cocktail

    fun getCocktail(id: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                getCocktailByIdUseCase(id)
            }.onSuccess {
                _cocktail.value = it
            }
        }
    }
}
