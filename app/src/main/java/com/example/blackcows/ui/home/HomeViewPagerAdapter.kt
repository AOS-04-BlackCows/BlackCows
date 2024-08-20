package com.example.blackcows.ui.home

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.blackcows.ListItem
import com.example.blackcows.R
import com.example.blackcows.databinding.FragmentHomeBinding
import com.example.blackcows.databinding.HomeItemVideoThumbnailBinding

class HomeViewPagerAdapter : RecyclerView.Adapter<HomeViewPagerAdapter.ViewPagerHolder>() {

    private var popularVideoList = listOf<ListItem.VideoItem>()

    fun submitList(videos: List<ListItem.VideoItem>) {
        popularVideoList = videos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerHolder {
        val binding = HomeItemVideoThumbnailBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewPagerAdapter.ViewPagerHolder, position: Int) {
        val videoItem = popularVideoList[position]
        holder.bind(videoItem)
        holder.itemView.setOnClickListener {
            itemClick?.onClick(videoItem, position)
        }
    }

    override fun getItemCount(): Int = popularVideoList.size


    class ViewPagerHolder(private val binding: HomeItemVideoThumbnailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(videoItems: ListItem.VideoItem) {
            Glide.with(binding.root.context)
                // ListItem -> VideoItem에 thumbnail을 사용해 이미지를 로드한다.
                .load(videoItems.thumbnail)
                .into(binding.ivHomeThumbnail)
        }
    }

    interface ItemClick {
        fun onClick(item: ListItem.VideoItem, position: Int)
    }

    var itemClick: ItemClick? = null

}