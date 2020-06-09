package dev.amaro.navigation

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.Navigation

class NavigationHostView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = -1
) : FrameLayout(context, attrs, defStyleAttr), NavHost {

    private val navigationController = NavController(context)
    private val navigationGraph: Int

    init {
        Navigation.setViewNavController(this, navigationController)
        context.resources.obtainAttributes(attrs, R.styleable.NavigationHostView).apply {
            navigationGraph = getResourceId(R.styleable.NavigationHostView_mapId, 0)
            recycle()
        }
    }

    var router: Router = Router.Empty
        set(value) {
            val customViewNavigator = ViewNavigator(this, value)
            navigationController.navigatorProvider.addNavigator(customViewNavigator)
            navigationController.setGraph(navigationGraph)
        }

    override fun getNavController() = navigationController

}