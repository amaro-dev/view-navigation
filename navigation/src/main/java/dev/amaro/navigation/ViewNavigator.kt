package dev.amaro.navigation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import java.util.*

@Navigator.Name("screen_view")
class ViewNavigator(
    private val container: NavigationHostView,
    private val router: Router
) : Navigator<ViewDestination>() {

    private val viewStack: Deque<ViewDestination> = LinkedList()

    override fun navigate(
        destination: ViewDestination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Extras?
    ) = destination.apply {
        val screen = router.routeTo(destination)
        screen?.run {
            viewStack.push(destination)
        }
        replaceView(screen)
    }

    private fun replaceView(view: View?) {
        container.removeAllViews()
        (view ?: TextView(container.context).apply { text = "No route found" }).run {
            container.addView(this)
        }
    }

    override fun createDestination(): ViewDestination = ViewDestination(this)

    override fun popBackStack(): Boolean = when {
        viewStack.isNotEmpty() -> {
            viewStack.pop()
            viewStack.peekLast()?.let {
                replaceView(router.routeTo(it))
            }
            true
        }
        else -> false
    }
}