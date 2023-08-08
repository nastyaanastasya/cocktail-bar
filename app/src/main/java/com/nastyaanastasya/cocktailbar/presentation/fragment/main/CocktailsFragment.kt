package com.nastyaanastasya.cocktailbar.presentation.fragment.main

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nastyaanastasya.cocktailbar.R
import com.nastyaanastasya.cocktailbar.databinding.FragmentCocktailsBinding
import com.nastyaanastasya.cocktailbar.domain.model.CocktailSimpleDto
import com.nastyaanastasya.cocktailbar.presentation.extension.hideLoading
import com.nastyaanastasya.cocktailbar.presentation.extension.openDetailedScreen
import com.nastyaanastasya.cocktailbar.presentation.extension.openEditingScreen
import com.nastyaanastasya.cocktailbar.presentation.extension.showLoading
import com.nastyaanastasya.cocktailbar.presentation.fragment.main.rv.itemdecorator.SpaceItemDecorator
import com.nastyaanastasya.cocktailbar.presentation.fragment.main.rv.CocktailAdapter
import com.nastyaanastasya.cocktailbar.presentation.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CocktailsFragment : Fragment(R.layout.fragment_cocktails) {

    private lateinit var binding: FragmentCocktailsBinding
    private lateinit var cocktailAdapter: CocktailAdapter

    private val viewModel: ListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCocktailsBinding.bind(view)
        cocktailAdapter = CocktailAdapter { openDetailedScreen(it) }

        showLoading()

        initObservers()
        initListeners()
        initRecycler()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.list.collect { list ->
                updateUi(list)
            }
        }
    }

    private fun initListeners() {
        binding.fabAdd.setOnClickListener {
            openEditingScreen(null)
        }
    }

    private fun updateUi(list: List<CocktailSimpleDto>) {
        when (list.isEmpty()) {
            true -> showList(false)
            false -> {
                showList(true)
                setAdapter(list)
            }
        }
        hideLoading()
    }

    private fun initRecycler() {
        binding.rvCocktails.apply{
            adapter = cocktailAdapter
            addItemDecoration(
                SpaceItemDecorator(requireContext())
            )
        }
    }

    private fun setAdapter(list: List<CocktailSimpleDto>) {
        cocktailAdapter.submitList(list.toMutableList())
    }

    private fun showList(isVisible: Boolean) {
        with(binding) {
            groupNotEmptyList.isVisible = isVisible
            groupEmptyList.isVisible = !isVisible
        }
    }
}
