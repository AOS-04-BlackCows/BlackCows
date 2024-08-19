package com.example.blackcows.ui.home

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.blackcows.ListItem
import com.example.blackcows.R

// 디테일 페이지에서 데이터 가져오기
class HomeViewPagerAdapter (private var videos: List<ListItem.VideoItem>) : PagerAdapter(){
    override fun getCount() = videos.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
        container.removeView(`object` as View)
    }

    fun ubdateVideos(newVideos : List<ListItem.VideoItem>) {
        videos = newVideos
        notifyDataSetChanged()
    }

}