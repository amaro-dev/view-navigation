package dev.amaro.navigation

import android.content.Context
import android.view.View

interface Router {
    fun routeTo(destination: ViewDestination): View?

    companion object {
        val Empty = object : Router {
            override fun routeTo(destination: ViewDestination): View? = null
        }
    }

    class Direct(private val context: Context) : Router {
        override fun routeTo(destination: ViewDestination): View? {
            if (destination.className == null) return null
            return Class.forName(destination.className!!)
                .getConstructor(Context::class.java)
                .newInstance(context) as View?
        }
    }
}

