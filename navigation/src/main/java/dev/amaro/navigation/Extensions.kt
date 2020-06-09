package dev.amaro.navigation

import android.view.View
import androidx.annotation.IdRes
import androidx.navigation.findNavController


fun View.navigateTo(@IdRes idRes: Int) = findNavController().navigate(idRes)