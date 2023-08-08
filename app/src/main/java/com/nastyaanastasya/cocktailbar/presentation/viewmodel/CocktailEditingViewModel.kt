package com.nastyaanastasya.cocktailbar.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastyaanastasya.cocktailbar.domain.model.CocktailDto
import com.nastyaanastasya.cocktailbar.domain.model.CocktailSimpleDto
import com.nastyaanastasya.cocktailbar.domain.usecase.RemoveCocktailUseCase
import com.nastyaanastasya.cocktailbar.domain.usecase.EditCocktailUseCase
import com.nastyaanastasya.cocktailbar.domain.usecase.SaveCocktailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CocktailEditingViewModel @Inject constructor(
    private val removeCocktailUseCase: RemoveCocktailUseCase,
    private val editCocktailUseCase: EditCocktailUseCase,
    private val saveCocktailUseCase: SaveCocktailUseCase
): ViewModel() {

    private val _save = MutableStateFlow(false)
    val save: StateFlow<Boolean> = _save

    private val _edit = MutableStateFlow(false)
    val edit: StateFlow<Boolean> = _edit

    private val _remove = MutableStateFlow(false)
    val remove: StateFlow<Boolean> = _remove

    fun save(cocktailDto: CocktailDto) {
        viewModelScope.launch {
            kotlin.runCatching {
                saveCocktailUseCase(cocktailDto)
            }.onSuccess {
                _save.value = true
            }
        }
    }

    fun edit(cocktailDto: CocktailDto) {
        viewModelScope.launch {
            kotlin.runCatching {
                editCocktailUseCase(cocktailDto)
            }.onSuccess {
                _edit.value = true
            }
        }
    }

    fun remove(cocktailDto: CocktailDto) {
        viewModelScope.launch {
            kotlin.runCatching {
                removeCocktailUseCase(cocktailDto)
            }.onSuccess {
                _remove.value = true
            }
        }
    }
}
