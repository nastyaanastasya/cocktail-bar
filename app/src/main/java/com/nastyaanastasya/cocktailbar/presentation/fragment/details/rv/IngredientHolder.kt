package com.nastyaanastasya.cocktailbar.presentation.fragment.details.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nastyaanastasya.cocktailbar.databinding.ItemIngredientBinding

class IngredientHolder(
    private val binding: ItemIngredientBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.tvIngredient.text = item
    }

    companion object {
        fun create(
            parent: ViewGroup
        ) = IngredientHolder(
            ItemIngredientBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
