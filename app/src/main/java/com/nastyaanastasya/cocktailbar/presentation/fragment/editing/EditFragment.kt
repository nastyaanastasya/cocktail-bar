package com.nastyaanastasya.cocktailbar.presentation.fragment.editing

import android.os.Bundle
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.nastyaanastasya.cocktailbar.R
import com.nastyaanastasya.cocktailbar.databinding.FragmentEditBinding
import com.nastyaanastasya.cocktailbar.domain.model.CocktailDto
import com.nastyaanastasya.cocktailbar.presentation.extension.constants.Constants.COCKTAIL_ID
import com.nastyaanastasya.cocktailbar.presentation.extension.navigateBack
import com.nastyaanastasya.cocktailbar.presentation.viewmodel.CocktailEditingViewModel
import com.nastyaanastasya.cocktailbar.presentation.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditFragment : Fragment(R.layout.fragment_edit) {

    private lateinit var binding: FragmentEditBinding

    private val editViewModel: CocktailEditingViewModel by viewModels()
    private val detailsViewModel: DetailsViewModel by viewModels()

    private var id: Int? = null
    private var cocktail: CocktailDto? = null

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                binding.ivCocktail.setImageURI(it)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditBinding.bind(view)

        initObservers()
        checkArguments()
        initListeners()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            detailsViewModel.cocktail.collect { cocktail ->
                cocktail?.let {
                    id = it.id
                    updateUi(it)
                }
            }
        }
    }

    private fun initListeners() {
        with(binding) {
            btnCancel.setOnClickListener {
                navigateBack()
            }
            btnSave.setOnClickListener {
                getInfo()
                cocktail?.let { cocktail ->
                    id?.let {
                        editViewModel.edit(cocktail)
                    } ?: editViewModel.save(cocktail)
                }
            }
            btnDelete.setOnClickListener {
                cocktail?.let {
                    editViewModel.remove(it)
                }
            }
            ivCocktail.setOnClickListener {
                pickImageFromGallery()
            }
        }
    }

    private fun pickImageFromGallery() {
        permissionLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun getInfo() {
        if (checkTitleText()) {
            cocktail = CocktailDto(
                id = 0,
                image = binding.ivCocktail.drawable.toBitmap(),
                name = binding.tietTitle.text.toString(),
                desc = binding.tietDesc.text.toString(),
                recipe = binding.tietRecipe.text.toString(),
                ingredients = emptyList()
            )
        }
    }

    private fun checkTitleText() = binding.tietTitle.text.toString().isNotBlank()

    private fun updateUi(cocktail: CocktailDto) {
        with(binding) {
            ivCocktail.load(cocktail.image)
            tietTitle.setText(cocktail.name)
            tietDesc.setText(cocktail.desc)
            tietRecipe.setText(cocktail.recipe)
            btnDelete.visibility = View.VISIBLE
        }
    }

    private fun checkArguments() {
        arguments?.getInt(COCKTAIL_ID)?.let {
            detailsViewModel.getCocktail(it)
        }
    }
}
