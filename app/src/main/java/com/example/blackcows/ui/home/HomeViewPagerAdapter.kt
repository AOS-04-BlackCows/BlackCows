package com.example.blackcows.ui.home

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blackcows.R

// 화면 됐을 때 재생, 넘어가면 정지 -> 하지말라고 하심
// my page에서 데이터 가져오기
class HomeViewPagerAdapter (private val items : Array<Int>) : RecyclerView.Adapter<HomeViewPagerAdapter.PagerViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = PagerViewHolder((parent))

    override fun onBindViewHolder(holder: HomeViewPagerAdapter.PagerViewHolder, position: Int) {
        // holder.itemsView.adapter =
    }

    override fun getItemCount() = items.size

    class PagerViewHolder(parent : ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.home_item_recyclerview, parent, false)){

             val itemsView : RecyclerView = itemView.findViewById(R.id.home_viewPager)
        }

}