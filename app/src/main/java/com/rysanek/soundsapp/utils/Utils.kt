package com.rysanek.soundsapp.utils

import android.os.Build
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.rysanek.soundsapp.R

fun AppCompatActivity.setUpSystemWindow() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val navBarColor = ContextCompat.getColor(this.applicationContext, R.color.navigation_bar_color)
        val statusBarColor = ContextCompat.getColor(this.applicationContext, R.color.status_bar_color)
        this.window.setDecorFitsSystemWindows(false)
        this.window.navigationBarColor = navBarColor
        this.window.statusBarColor = statusBarColor
    }
}

fun Fragment.showSnackBar(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
        .setAction("OK") {}
        .show()
}

@RequiresApi(Build.VERSION_CODES.R)
fun Fragment.hideKeyboard() {
    requireActivity().window.insetsController?.hide(WindowInsets.Type.ime())
}