package com.smaedev.covid19.ui.about

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.forEach
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smaedev.covid19.ui.about.articles.AboutArticleAdapter
import com.smaedev.covid19.ui.about.articles.AboutArticleFragment
import com.smaedev.covid19.ui.about.articles.urlWebView
import com.smaedev.covid19.webClient.MyWebViewClient_Article1
import kotlinx.android.extensions.LayoutContainer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * ExpandableGroup Adapter for recycler view needs a child type and parent type and a parent list in constructor
 * to create an expandable listing view UI
 * @param ExpandableType Parent Type (This is the class providing concrete implementation of the ExpandableGroup Interface)
 * @param ExpandedType Child Type (This can be a subtype of Any object)
 * @author Mobin Munir
 */
abstract class ExpandableRecyclerViewAdapter<ExpandedType : Any, ExpandableType : ExpandableRecyclerViewAdapter.ExpandableGroup<ExpandedType>, PVH : ExpandableRecyclerViewAdapter.ExpandableViewHolder, CVH : ExpandableRecyclerViewAdapter.ExpandedViewHolder>

/**
 *  Initializes the adapter with a list of expandable groups and a direction of inflation.
 *  @param mExpandableList The list of expandable groups.
 *  @param expandingDirection An enum for direction.
 */
    (
    private val mExpandableList: ArrayList<ExpandableType>,
    private val expandingDirection: ExpandingDirection
) : RecyclerView.Adapter<PVH>() {



    //lateinit var urlWebView: String
    /**
     * A bit to maintain expansion state over entire listing.
     * If list is fully-expanded it's set to true.
     */
    private var expanded = false

    /**
     * An integer to maintain position for singular expansion over entire listing.
     */
    private var lastExpandedPosition = -1

    /**
     * A bit to maintain adapter attachment status to a recycler view.
     * If this adapter is attached to a recycler view this bit is set to true.
     */
    private var adapterAttached = false

    /**
     * A reference to the recycler view that this adapter is currently attached to.
     */
    private var mParentRecyclerView: RecyclerView? = null

    /**
     * A tag for logging.
     */
    private val mTAG = "ExpandableGroupAdapter"

    /**
     * An enum class holds constant for expansion directions.
     */
    enum class ExpandingDirection {
        HORIZONTAL,
        VERTICAL
    }

    private fun initializeChildWebView(childWebView: WebView?) {

        if (childWebView != null) {

            val webSettings: WebSettings = childWebView.settings
            webSettings.javaScriptEnabled = true

            val webViewClient = MyWebViewClient_Article1(Activity())
            childWebView.webViewClient = webViewClient

            childWebView.loadUrl(urlWebView)
            childWebView.webViewClient = object : WebViewClient() {
                override fun onLoadResource(view: WebView, url: String) {
                    try {
                        view.loadUrl(
                            "javascript:(window.onload = function() { " +  /* Magazineregard
                                    "document.getElementsByClassName('top-masthead')[0].style.display='none'; "+
                                    "document.getElementsByClassName('masthead-banner data-bg')[0].style.display='none'; "+*/
                                    "document.getElementsByClassName('mbBgndGallery')[0].style.display='none'; " +
                                    "document.getElementsByClassName('zooming-slider dark fullheight top-slider bgndgalleryslider text-center')[0].style.display='none'; " +
                                    "document.getElementsByClassName('')[0].style.display='none'; " +
                                    "})()"
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return mExpandableList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PVH {
        return onCreateParentView(parent, viewType)
    }


    private fun onCreateParentView(parent: ViewGroup, viewType: Int): PVH {
        val pvh = onCreateParentViewHolder(parent, viewType)

        initializeChildWebView(pvh.containerView.getWebView())

        /*when (pvh.adapterPosition) {
            0 -> {
                urlWebView = "https://www.indilmalek.com"

            }
            1 -> {
                urlWebView =
                    "https://android.jlelse.eu/expandable-recycler-view-in-android-8d8927811ba7"
            }
            2 -> {
                urlWebView =
                    "https://android.jlelse.eu/expandable-recycler-view-in-android-8d8927811ba7"
            }
        }*/
        pvh.containerView.setOnClickListener {
            val position = pvh.adapterPosition
            val expandable = mExpandableList[position]

            if (isSingleExpanded())
                handleSingleExpansion(position)
            else handleExpansion(expandable, position)

            handleLastPositionScroll(position)

            onExpandableClick(pvh, expandable)

            Log.d(mTAG, "Clicked @ $position")
        }
        return pvh
    }

    private fun collapseAllGroups() {
        setExpanded(false)
    }

    private fun reverseExpandableState(expandableGroup: ExpandableType) {
        expandableGroup.isExpanded = !expandableGroup.isExpanded
    }

    private fun collapseAllExcept(position: Int) {
        val expandableGroup = mExpandableList[position]
        reverseExpandableState(expandableGroup)
        notifyItemChanged(position)
        if (lastExpandedPosition > -1 && lastExpandedPosition != position) {
            val previousExpandableGroup = mExpandableList[lastExpandedPosition]
            if (previousExpandableGroup.isExpanded) {
                previousExpandableGroup.isExpanded = false
                notifyItemChanged(lastExpandedPosition)
            }
        }

        lastExpandedPosition = position

    }

    private fun handleSingleExpansion(position: Int) {
        if (expanded) {
            collapseAllGroups()
        } else {
            collapseAllExcept(position)
        }

    }

    private fun handleExpansion(expandableGroup: ExpandableType, position: Int) {
        reverseExpandableState(expandableGroup)
        notifyItemChanged(position)


    }

    private fun handleLastPositionScroll(position: Int) {
        if (position == mExpandableList.lastIndex)
            mParentRecyclerView?.smoothScrollToPosition(position)
    }


    override fun onBindViewHolder(holder: PVH, position: Int) {
        setupChildRecyclerView(holder, position)

    }

    private fun setupChildRecyclerView(holder: PVH, position: Int) {
        val expandableGroup = mExpandableList[position]

        clickEvent(expandableGroup, holder.containerView)


        onBindParentViewHolder(holder, expandableGroup, position)
    }

    private fun clickEvent(expandableGroup: ExpandableType, containerView: View) {
        val childWebView = containerView.getWebView()

        childWebView?.visibility = if (expandableGroup.isExpanded)
            View.VISIBLE
        else View.GONE

    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        adapterAttached = true

        mParentRecyclerView = recyclerView

        mParentRecyclerView?.layoutManager = LinearLayoutManager(recyclerView.context)

        Log.d(mTAG, "Attached: $adapterAttached")
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        adapterAttached = false
        this.mParentRecyclerView = null
    }

    /**
     * Specifies if you want to show all items expanded in UI.
     * @param expanded A bit to enable/disable full expansion.
     * Note: If any group is clicked overall expansion is instantly discarded.
     */
    fun setExpanded(expanded: Boolean) {
        this.expanded = expanded
        mExpandableList.applyExpansionState(expanded)
    }

    /**
     * A swift method to add a new group to the list.
     * @param expandableGroup The new group.
     * @param expanded An optional state for expansion to apply (false by default).
     * @param position An optional current position at which to insert the new group in the adapter. (not applicable by default).
     */
    fun addGroup(expandableGroup: ExpandableType, expanded: Boolean = false, position: Int = -1) {


        var atPosition = itemCount

        if (position > atPosition) {
            Log.e(mTAG, "Position to add group exceeds the total group count of $atPosition")
            return
        }


        expandableGroup.isExpanded = expanded



        if (position == -1 || position == atPosition)
            mExpandableList.add(expandableGroup)
        else if (position > -1) {
            mExpandableList.add(position, expandableGroup)
            atPosition = position
        }

        if (adapterAttached)
            notifyItemInserted(atPosition)

        Log.d(mTAG, "Group added at $atPosition")


    }

    /**
     * A swift method to remove a group from the list.
     * @param position The current position of the group the adapter.
     */
    fun removeGroup(position: Int) {

        if (position < 0 || position > itemCount) {
            Log.e(mTAG, "Group can't be removed at position $position")
            return
        }

        mExpandableList.removeAt(position)

        if (adapterAttached)
            notifyItemRemoved(position)

        Log.d(mTAG, "Group removed at $position")

    }

    /**
     * Asynchronously applies the expansion state to all the list in a background thread swiftly
     * and notifies the adapter in an efficient manner to dispatch updates.
     * @param expansionState The expansion state to apply to all list.
     * This method can be made public to work on subset of @see ExpandableGroup Class by declaring it outside this class
     */
    private fun List<ExpandableType>.applyExpansionState(expansionState: Boolean) {

        GlobalScope.launch(Dispatchers.IO) {
            forEach {
                it.isExpanded = expansionState


            }

            launch(Dispatchers.Main) {
                if (adapterAttached)
                    notifyItemRangeChanged(0, itemCount)
            }
        }

    }

    /**
     * Searches View hierarchy for an instance of RecyclerView
     * @return RecyclerView or null if not found
     */
    /*private fun View.getRecyclerView(): RecyclerView? {
        if (this is ViewGroup && childCount > 0) {
            forEach {
                if (it is RecyclerView) {
                    return it
                }

            }
        }
        Log.e(mTAG, "Recycler View for expanded items not found in parent layout.")
        return null
    }*/

    private fun View.getWebView(): WebView? {
        if (this is ViewGroup && childCount > 0) {
            forEach {
                if (it is WebView) {
                    return it
                }

            }
        }
        Log.e(mTAG, "Recycler View for expanded items not found in parent layout.")
        return null
    }

    abstract class ExpandableViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer

    abstract class ExpandedViewHolder(override val containerView: View) : WebViewClient(), LayoutContainer


    abstract class ExpandableGroup<out E> {
        /**
         * returns a list of provided type to be used for expansion.
         */
        abstract fun getExpandingItems(): WebView

        /**
         *   Specifies if you want to show the UI in expanded form.
         */
        var isExpanded = false
    }


    abstract fun onCreateParentViewHolder(parent: ViewGroup, viewType: Int): PVH

    abstract fun onBindParentViewHolder(
        parentViewHolder: PVH,
        expandableType: ExpandableType,
        position: Int
    )

    abstract fun onCreateChildViewHolder(child: ViewGroup, viewType: Int): CVH

    abstract fun onBindChildViewHolder(
        childViewHolder: CVH,
        expandedType: ExpandedType,
        expandableType: ExpandableType,
        position: Int
    )

    /**
     * A method to delegation for click event on expandable view.
     * @param expandableViewHolder The view holder for expandable Item.
     * @param expandableType The expandable item.
     */
    abstract fun onExpandableClick(
        expandableViewHolder: PVH,
        expandableType: ExpandableType
    )


    /**
     * Specifies if you want to show one item expanded in UI at most.
     * @return true to enable one child expansion at a time.
     * returns false by default.
     */
    protected open fun isSingleExpanded() = false

}

