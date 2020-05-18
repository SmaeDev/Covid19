package com.smaedev.covid19.ui.about.articles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.smaedev.covid19.R
import kotlinx.android.synthetic.main.child_row.*
import kotlinx.android.synthetic.main.parent_row.*
import com.smaedev.covid19.ui.about.ExpandableRecyclerViewAdapter
import com.smaedev.covid19.db.AboutArticleChild
import com.smaedev.covid19.db.AboutArticleParent

class AboutArticleAdapter(parents: ArrayList<AboutArticleParent>) :
    ExpandableRecyclerViewAdapter<AboutArticleChild, AboutArticleParent, AboutArticleAdapter.PViewHolder, AboutArticleAdapter.CViewHolder>(
        parents, ExpandingDirection.VERTICAL
    ) {

    override fun onCreateParentViewHolder(parent: ViewGroup, viewType: Int): PViewHolder {

        return PViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.parent_row,
                parent,
                false
            )
        )
    }

    override fun onCreateChildViewHolder(child: ViewGroup, viewType: Int): CViewHolder {
        return CViewHolder(
            LayoutInflater.from(child.context).inflate(
                R.layout.child_row,
                child,
                false
            )
        )
    }

    override fun onBindParentViewHolder(
        parentViewHolder: PViewHolder,
        expandableType: AboutArticleParent,
        position: Int
    ) {
        parentViewHolder.tvP.text = expandableType.name

    }

    override fun onBindChildViewHolder(
        childViewHolder: CViewHolder,
        expandedType: AboutArticleChild,
        expandableType: AboutArticleParent,
        position: Int
    ) {
        //childViewHolder.tvC = expandedType.name
        //childViewHolder.tvC.text = expandedType.
    }

    /*override fun onExpandedClick(
        expandableViewHolder: PViewHolder,
        expandedViewHolder: CViewHolder,
        expandedType: AboutArticleChild,
        expandableType: AboutArticleParent
    ) {
        /*Toast.makeText(
            expandableViewHolder.containerView.context,
            expandableType.name + " " + expandedType.name + " Position: " + expandedViewHolder.adapterPosition,
            Toast.LENGTH_SHORT
        ).show()*/
    }*/

    override fun onExpandableClick(
        expandableViewHolder: PViewHolder,
        expandableType: AboutArticleParent
    ) {
        Toast.makeText(
            expandableViewHolder.containerView.context,
            expandableType.name + " Position: " + expandableViewHolder.adapterPosition,
            Toast.LENGTH_SHORT
        ).show()

    }

    class PViewHolder(v: View) : ExpandableRecyclerViewAdapter.ExpandableViewHolder(v)

    class CViewHolder(v: View) : ExpandableRecyclerViewAdapter.ExpandedViewHolder(v)
}
