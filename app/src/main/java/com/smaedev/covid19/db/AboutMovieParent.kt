package com.smaedev.covid19.db

import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import com.smaedev.covid19.MainActivity
import com.smaedev.covid19.ui.about.movies.ExpandableMovieRecyclerViewAdapter


data class AboutMovieParent(val name: String) : ExpandableMovieRecyclerViewAdapter.ExpandableGroup<AboutMovieChild>() {

    private var videoView: VideoView? = null

    override fun getExpandingItems(): VideoView {
        val list = ArrayList<AboutMovieChild>(2)
        for (i in 0..2)
            list.add(AboutMovieChild("Child $i"))

        val vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4"

        videoView?.setVideoURI(Uri.parse(vidAddress))
        videoView?.start()
        val vidControl = MediaController(MainActivity.mainContext())
        vidControl.setAnchorView(videoView);
        videoView?.setMediaController(vidControl);

        //return list

        return videoView!!
    }


}