package com.nastyaanastasya.cocktailbar.presentation.fragment.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.nastyaanastasya.cocktailbar.R
import com.nastyaanastasya.cocktailbar.databinding.FragmentDetailsBinding
import com.nastyaanastasya.cocktailbar.domain.model.CocktailDto
import com.nastyaanastasya.cocktailbar.presentation.extension.constants.Constants.COCKTAIL_ID
import com.nastyaanastasya.cocktailbar.presentation.extension.openEditingScreen
import com.nastyaanastasya.cocktailbar.presentation.fragment.details.rv.IngredientAdapter
import com.nastyaanastasya.cocktailbar.presentation.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var ingredientAdapter: IngredientAdapter

    private val viewModel: DetailsViewModel by viewModels()
    private var id: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        initObservers()
        checkArguments()
        initListeners()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cocktail.collect { cocktail ->
                cocktail?.let {
                    id = it.id
                    ingredientAdapter = IngredientAdapter(cocktail.ingredients)
                    updateUi(it)
                }
            }
        }
    }

    private fun initListeners() {
        with(binding) {
            btnEdit.setOnClickListener {
                openEditingScreen(id)
            }
        }
    }

    private fun checkArguments() {
        arguments?.getInt(COCKTAIL_ID)?.let {
            viewModel.getCocktail(it)
        }
    }

    private fun updateUi(cocktail: CocktailDto) {
        with(binding) {
            ivCocktail.load(cocktail.image)
            tvName.text = cocktail.name

            val desc = cocktail.desc.toString()
            val recipe = cocktail.recipe.toString()

            if (desc.isNotBlank()) {
                tvDesc.text = desc
            } else {
                tvDesc.visibility = View.GONE
            }

            if (recipe.isNotBlank()) {
                tvRecipe.text = recipe
            } else {
                tvRecipe.visibility = View.GONE
                tvRecipeTitle.visibility = View.GONE
            }

            initRecycler(cocktail.ingredients)
        }
    }

    private fun initRecycler(list: List<String>) {
        binding.rvIngredients.apply {
            adapter = ingredientAdapter
        }
    }
}
