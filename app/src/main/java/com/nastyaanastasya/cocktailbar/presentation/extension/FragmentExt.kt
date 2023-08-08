package com.nastyaanastasya.cocktailbar.presentation.extension

import android.view.View
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nastyaanastasya.cocktailbar.R
import com.nastyaanastasya.cocktailbar.presentation.MainActivity
import com.nastyaanastasya.cocktailbar.presentation.extension.constants.Constants.COCKTAIL_ID

fun Fragment.showLoading() {
    (activity as MainActivity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
}

fun Fragment.hideLoading() {
    (activity as MainActivity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
}

fun Fragment.openEditingScreen(id: Int?) {
    findNavController().navigate(
        R.id.action_cocktailsFragment_to_editFragment
    )
}

fun Fragment.openDetailedScreen(id: Int) {
    findNavController().navigate(
        R.id.action_cocktailsFragment_to_detailsFragment,
        bundleOf(COCKTAIL_ID to id)
    )
}

fun Fragment.navigateBack() {
    findNavController().navigateUp()
}
