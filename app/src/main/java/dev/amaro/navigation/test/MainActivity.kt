package dev.amaro.navigation.test

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dev.amaro.navigation.Router
import dev.amaro.navigation.ViewDestination
import dev.amaro.navigation.test.view.FirstView
import dev.amaro.navigation.test.view.LastView
import dev.amaro.navigation.test.view.SecondView
import dev.amaro.navigation.test.view.ThirdView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private val router = object : Router {
        override fun routeTo(destination: ViewDestination): View? = routeTo(destination.layoutId)

        override fun routeTo(destinationId: Int): View? =
            when (destinationId) {
                R.layout.screen_view_first -> FirstView(main_navigation_host.context)
                R.layout.screen_view_second -> SecondView(main_navigation_host.context)
                R.layout.screen_view_third -> ThirdView(main_navigation_host.context)
                R.layout.screen_view_last -> LastView(main_navigation_host.context)
                else -> null
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_navigation_host.router = if (intent.getBooleanExtra("NO_ROUTER", false))
            Router.Empty else router
        navController = Navigation.findNavController(main_navigation_host)
        Navigation.setViewNavController(main_navigation_host, navController)
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()

    override fun onBackPressed() {
        if (!navController.popBackStack()) {
            super.onBackPressed()
        }
    }
}