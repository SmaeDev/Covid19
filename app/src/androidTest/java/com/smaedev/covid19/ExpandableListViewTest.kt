package com.smaedev.covid19

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.smaedev.covid19.TestUtils.atChildPosition
import com.smaedev.covid19.TestUtils.atPosition
import com.smaedev.covid19.TestUtils.clickActionOnChildRecyclerView
import com.smaedev.covid19.ui.about.articles.AboutArticleAdapter
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExpandableListViewTest {

    @Rule
    @JvmField
    val mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    /**
     * Test click on expandable listing.
     * Assertion: List is expanded or not.
     * Passing Criteria: state can be either expandable or expanded.
     */
    @Test
    fun testExpandableListClick() {
        val randomIndex = Random.nextInt(10)
        onView(withId(R.id.articleRv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AboutArticleAdapter.PViewHolder>(
                randomIndex,
                click()
            )
        )

    }

    /**
     * Test click on expanded listing
     * Assertion: List is expandable not expanded.
     * Passing Criteria: List must be not be in expanded state.
     */
    @Test
    fun testExpandedListClick() {
        val randomIndex = Random.nextInt(10)


        onView(withId(R.id.articleRv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AboutArticleAdapter.PViewHolder>(
                randomIndex,
                click()
            )
        )

        onView(withId(R.id.articleRv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AboutArticleAdapter.PViewHolder>(
                randomIndex,
                clickActionOnChildRecyclerView(randomIndex)
            )
        )

    }

    /**
     * Test text on expandable listing
     * Assertion: List is expanded or not.
     * Passing Criteria: state can be either expandable or expanded.
     */
    @Test
    fun testExpandableText() {
        val randomIndex = Random.nextInt(10)
        onView(withId(R.id.articleRv))
            .check(matches(atPosition(randomIndex, hasDescendant(withText("Parent $randomIndex")))))
    }

    /**
     * Test text on expanded listing
     * Assertion: List is expanded.
     * Passing Criteria: List must be in expanded state.
     * This only works when simultaneous execution of this whole test class takes place.
     */
    @Test
    fun testExpandedText() {
        val randomIndex = Random.nextInt(10)
        onView(withId(R.id.articleRv))
            .check(matches(atChildPosition(randomIndex, withText("Child $randomIndex"))))
    }


}
