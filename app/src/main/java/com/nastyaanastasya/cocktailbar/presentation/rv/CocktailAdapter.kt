package com.nastyaanastasya.cocktailbar.presentation.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nastyaanastasya.cocktailbar.domain.model.CocktailSimpleDto

class CocktailAdapter(
    private val action: (Int) -> Unit
) : ListAdapter<CocktailSimpleDto, CocktailHolder>(CocktailDiffUtilsCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CocktailHolder.create(parent, action)

    override fun onBindViewHolder(holder: CocktailHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: MutableList<CocktailSimpleDto>?) {
        super.submitList(
            if (list == null) null
            else ArrayList(list)
        )
    }
}

class CocktailDiffUtilsCallback : DiffUtil.ItemCallback<CocktailSimpleDto>() {

    override fun areItemsTheSame(
        oldItem: CocktailSimpleDto,
        newItem: CocktailSimpleDto
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: CocktailSimpleDto,
        newItem: CocktailSimpleDto
    ): Boolean = oldItem == newItem
}
