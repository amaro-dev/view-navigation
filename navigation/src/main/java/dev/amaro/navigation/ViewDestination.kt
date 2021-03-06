package dev.amaro.navigation

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.navigation.NavDestination
import androidx.navigation.Navigator

@NavDestination.ClassType(ViewGroup::class)
class ViewDestination(navigator: Navigator<out NavDestination>) : NavDestination(navigator) {

    @LayoutRes
    var layoutId: Int = 0

    var className: String? = null

    override fun onInflate(context: Context, attrs: AttributeSet) {
        super.onInflate(context, attrs)
        context.resources.obtainAttributes(attrs, R.styleable.ViewNavigator).apply {
            layoutId = getResourceId(R.styleable.ViewNavigator_layoutId, 0)
            className = getString(R.styleable.ViewNavigator_name)
            recycle()
        }
    }
}