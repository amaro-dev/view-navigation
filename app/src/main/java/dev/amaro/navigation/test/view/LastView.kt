package dev.amaro.navigation.test.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import dev.amaro.navigation.test.R

class LastView @JvmOverloads constructor(context: Context) : FrameLayout(context, null, -1) {
    init {
        LayoutInflater.from(context).inflate(R.layout.screen_view_last, this)
    }
}