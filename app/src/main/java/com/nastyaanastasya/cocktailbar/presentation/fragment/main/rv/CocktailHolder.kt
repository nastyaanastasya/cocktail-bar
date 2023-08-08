package com.nastyaanastasya.cocktailbar.presentation.fragment.main.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nastyaanastasya.cocktailbar.databinding.ItemCocktailBinding
import com.nastyaanastasya.cocktailbar.domain.model.CocktailSimpleDto

class CocktailHolder(
    private val binding: ItemCocktailBinding,
    private val action: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CocktailSimpleDto) {
        with(binding) {
            tvName.text = item.name
            ivCocktail.load(item.image)
        }
        itemView.setOnClickListener {
            action(item.id)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            action: (Int) -> Unit
        ) = CocktailHolder(
            ItemCocktailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            action
        )
    }

}
