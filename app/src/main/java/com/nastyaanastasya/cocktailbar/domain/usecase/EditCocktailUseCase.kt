package com.nastyaanastasya.cocktailbar.domain.usecase

import com.nastyaanastasya.cocktailbar.domain.model.CocktailDto
import com.nastyaanastasya.cocktailbar.domain.repository.CocktailRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class EditCocktailUseCase @Inject constructor(
    private val repository: CocktailRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(cocktailDto: CocktailDto) {
        return withContext(dispatcher) {
            repository.edit(cocktailDto)
        }
    }
}
