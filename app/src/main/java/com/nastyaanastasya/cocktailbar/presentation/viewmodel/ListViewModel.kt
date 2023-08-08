package com.nastyaanastasya.cocktailbar.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastyaanastasya.cocktailbar.domain.model.CocktailSimpleDto
import com.nastyaanastasya.cocktailbar.domain.usecase.GetCocktailListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getCocktailListUseCase: GetCocktailListUseCase
) : ViewModel() {

    private val _list = MutableStateFlow<List<CocktailSimpleDto>>(emptyList())
    val list: StateFlow<List<CocktailSimpleDto>> = _list

    fun getList() {
        viewModelScope.launch {
            kotlin.runCatching {
                getCocktailListUseCase()
            }.onSuccess {
                _list.value = it
            }
        }
    }
}
