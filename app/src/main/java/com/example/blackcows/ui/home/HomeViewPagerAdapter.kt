package com.example.blackcows.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blackcows.R

// 화면 됐을 때 재생, 넘어가면 정지
class HomeViewPagerAdapter (private val items : Array<Int>) : RecyclerView.Adapter<HomeViewPagerAdapter.PagerViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = PagerViewHolder((parent))

    override fun onBindViewHolder(holder: HomeViewPagerAdapter.PagerViewHolder, position: Int) {
        // holder.itemRecyclerView
    }

    override fun getItemCount() = items.size

    class PagerViewHolder(parent : ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.home_item_recyclerview, parent, false)){

            // val itemRecyclerView = itemView.home_recyclerView
        }

}