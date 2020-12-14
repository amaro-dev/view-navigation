package dev.amaro.navigation.test.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import dev.amaro.navigation.navigateTo
import dev.amaro.navigation.test.R
import kotlinx.android.synthetic.main.screen_view_first.view.*

class FirstView @JvmOverloads constructor(context: Context) : FrameLayout(context, null, -1) {

    init {
        LayoutInflater.from(context).inflate(R.layout.screen_view_first, this)
        btn_second_screen.setOnClickListener {
            navigateTo(R.id.action_first_screen_view_to_second_screen_view)
        }
        btn_last_screen.setOnClickListener {
            navigateTo(R.id.action_first_screen_view_to_last_screen_view)
        }
    }
}