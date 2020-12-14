package dev.amaro.navigation.test.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import dev.amaro.navigation.navigateTo
import dev.amaro.navigation.test.R
import kotlinx.android.synthetic.main.screen_view_second.view.*

class SecondView @JvmOverloads constructor(context: Context) : FrameLayout(context, null, -1) {
    init {
        LayoutInflater.from(context).inflate(R.layout.screen_view_second, this)
        btn_third_screen.setOnClickListener {
            navigateTo(R.id.action_second_screen_view_to_screen_view_third)
        }
    }
}