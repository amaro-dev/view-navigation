package dev.amaro.navigation

import android.view.View

interface Router {
    fun routeTo(destination: ViewDestination): View?
    fun routeTo(destinationId: Int): View?

    companion object {
        val Empty = object : Router {
            override fun routeTo(destination: ViewDestination): View? = null
            override fun routeTo(destinationId: Int): View? = null
        }
    }
}

