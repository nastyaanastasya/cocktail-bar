package com.nastyaanastasya.cocktailbar.presentation.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nastyaanastasya.cocktailbar.domain.model.CocktailDto

class CocktailAdapter(
    private val action: (Int) -> Unit
) : ListAdapter<CocktailDto, CocktailHolder>(CocktailDiffUtilsCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CocktailHolder.create(parent, action)

    override fun onBindViewHolder(holder: CocktailHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: MutableList<CocktailDto>?) {
        super.submitList(
            if (list == null) null
            else ArrayList(list)
        )
    }
}

class CocktailDiffUtilsCallback : DiffUtil.ItemCallback<CocktailDto>() {

    override fun areItemsTheSame(
        oldItem: CocktailDto,
        newItem: CocktailDto
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: CocktailDto,
        newItem: CocktailDto
    ): Boolean = oldItem == newItem
}
