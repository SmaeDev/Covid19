package com.smaedev.covid19.ui.about.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.smaedev.covid19.R
import com.smaedev.covid19.db.AboutMovieChild
import com.smaedev.covid19.db.AboutMovieParent
import kotlinx.android.synthetic.main.parent_row.*
import kotlinx.android.synthetic.main.parent_row_movie.*

class AboutMovieAdapter(parents: ArrayList<AboutMovieParent>) :
    ExpandableMovieRecyclerViewAdapter<AboutMovieChild, AboutMovieParent, AboutMovieAdapter.PViewHolder, AboutMovieAdapter.CViewHolder>(
        parents, ExpandingDirection.VERTICAL
    ) {

    override fun onCreateParentViewHolder(parent: ViewGroup, viewType: Int): PViewHolder {

        return PViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.parent_row_movie,
                parent,
                false
            )
        )
    }

    override fun onCreateChildViewHolder(child: ViewGroup, viewType: Int): CViewHolder {
        return CViewHolder(
            LayoutInflater.from(child.context).inflate(
                R.layout.child_row_movie,
                child,
                false
            )
        )
    }

    override fun onBindParentViewHolder(
        parentViewHolder: PViewHolder,
        expandableType: AboutMovieParent,
        position: Int
    ) {
        parentViewHolder.tvPMovie.text = expandableType.name

    }

    override fun onBindChildViewHolder(
        childViewHolder: CViewHolder,
        expandedType: AboutMovieChild,
        expandableType: AboutMovieParent,
        position: Int
    ) {
        //childViewHolder.tvC.text = expandedType.name
    }

    /*override fun onExpandedClick(
        expandableViewHolder: PViewHolder,
        expandedViewHolder: CViewHolder,
        expandedType: AboutMovieChild,
        expandableType: AboutMovieParent
    ) {
        Toast.makeText(
            expandableViewHolder.containerView.context,
            expandableType.name + " " + expandedType.name + " Position: " + expandedViewHolder.adapterPosition,
            Toast.LENGTH_SHORT
        ).show()
    }*/

    override fun onExpandableClick(
        expandableViewHolder: PViewHolder,
        expandableType: AboutMovieParent
    ) {
        Toast.makeText(
            expandableViewHolder.containerView.context,
            expandableType.name + " Position: " + expandableViewHolder.adapterPosition,
            Toast.LENGTH_SHORT
        ).show()
    }

    class PViewHolder(v: View) : ExpandableMovieRecyclerViewAdapter.ExpandableViewHolder(v)

    class CViewHolder(v: View) : ExpandableMovieRecyclerViewAdapter.ExpandedViewHolder(v)

}