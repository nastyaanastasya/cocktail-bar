package com.nastyaanastasya.cocktailbar.domain.usecase

import com.nastyaanastasya.cocktailbar.domain.model.CocktailSimpleDto
import com.nastyaanastasya.cocktailbar.domain.repository.CocktailRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetCocktailListUseCase @Inject constructor(
    private val repository: CocktailRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): List<CocktailSimpleDto> {
        return withContext(dispatcher) {
            repository.getAll()
        }
    }
}
