package com.nastyaanastasya.cocktailbar.domain.mapper

interface ModelMapper<S, D> {
    fun map(source: S): D
}
