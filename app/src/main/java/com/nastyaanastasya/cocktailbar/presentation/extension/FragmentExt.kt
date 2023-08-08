package com.nastyaanastasya.cocktailbar.presentation.extension

import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.nastyaanastasya.cocktailbar.R
import com.nastyaanastasya.cocktailbar.presentation.MainActivity

fun Fragment.showLoading(){
    (activity as MainActivity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
}

fun Fragment.hideLoading(){
    (activity as MainActivity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
}
