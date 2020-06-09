package dev.amaro.navigation

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.amaro.navigation.test.MainActivity
import dev.amaro.navigation.test.R
import dev.amaro.navigation.test.view.FirstView
import dev.amaro.navigation.test.view.SecondView
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.instanceOf
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NavigatorTests {

    @Test
    fun `Place the initial screen`() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity {
            val screen =
                it.findViewById<NavigationHostView>(R.id.main_navigation_host).getChildAt(0)
            Assert.assertThat(screen, instanceOf(FirstView::class.java))
        }
    }

    @Test
    fun `Place the second screen`() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity {
            onView(withText("Second Screen")).perform(click())
            val screen =
                it.findViewById<NavigationHostView>(R.id.main_navigation_host).getChildAt(0)
            Assert.assertThat(screen, instanceOf(SecondView::class.java))
        }
    }

    @Test
    fun `Back to previous screen`() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity {
            onView(withText("Second Screen")).perform(click())
            Espresso.pressBack()
            val screen =
                it.findViewById<NavigationHostView>(R.id.main_navigation_host).getChildAt(0)
            Assert.assertThat(screen, instanceOf(FirstView::class.java))
        }
    }

    @Test
    fun `Quit app when back from a new top screen`() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity {
            onView(withText("Second Screen")).perform(click())
            onView(withText("Third Screen")).perform(click())
            Espresso.pressBack()
            Assert.assertThat(it.isFinishing, `is`(true))
        }
    }

    @Test
    fun `Quit app when back from last screen`() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity {
            onView(withText("Second Screen")).perform(click())
            Espresso.pressBack()
            Espresso.pressBack()
            Assert.assertThat(it.isFinishing, `is`(true))
        }
    }

    @Test
    fun `If dev forget to put a router show No Router message`() {
        val scenario =
            ActivityScenario.launch<MainActivity>(Intent().apply { putExtra("NO_ROUTER", true) })
        scenario.onActivity {
            onView(withText("No route found")).check(matches(isDisplayed()))
        }
    }

    @Test
    fun `If there are no screens on the back stack, pressing back closes the app`() {
        val scenario =
            ActivityScenario.launch<MainActivity>(Intent().apply { putExtra("NO_ROUTER", true) })
        scenario.onActivity {
            Espresso.pressBack()
            Assert.assertThat(it.isFinishing, `is`(true))
        }
    }
}

