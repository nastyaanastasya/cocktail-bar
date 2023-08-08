package com.nastyaanastasya.cocktailbar.presentation.fragment.details.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class IngredientAdapter(
    private val list: List<String>
) : RecyclerView.Adapter<IngredientHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        IngredientHolder.create(parent)

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: IngredientHolder, position: Int) =
        holder.bind(list[position])
}
