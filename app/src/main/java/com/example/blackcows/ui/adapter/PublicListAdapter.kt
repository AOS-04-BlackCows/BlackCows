package com.example.blackcows.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blackcows.ListItem
import com.example.blackcows.R
import com.example.blackcows.databinding.SearchListItemBinding
import com.example.blackcows.databinding.SearchListProgressItemBinding

class PublicListAdapter : ListAdapter<ListItem.VideoItem, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<ListItem.VideoItem>() {
    override fun areItemsTheSame(oldItem: ListItem.VideoItem, newItem: ListItem.VideoItem): Boolean {
        return oldItem.videoId == newItem.videoId
    }

    override fun areContentsTheSame(oldItem: ListItem.VideoItem, newItem: ListItem.VideoItem): Boolean {
        return oldItem == newItem
    }
}) {
    //MypageFragment에서 사용
    var items = mutableListOf<ListItem.VideoItem>()
    var searchItems = mutableListOf<ListItem.VideoItem>()


    val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    // 리스트에 게시물이 들어가는 경우
    inner class SearchViewHolder(private val binding: SearchListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val listThumbnail: ImageView = binding.searchThumb
        val listTitle: TextView = binding.searchTitle
        val listWriter: TextView = binding.searchWriter
    }

    // 리스트에 프로그레스바가 들어가는 경우
    inner class LoadingViewHolder(private val binding: SearchListProgressItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    // 뷰의 타입을 정해주는 곳이다.
    override fun getItemViewType(position: Int): Int {
        // 동영상과 프로그레스바를 구분할 기준
        return when (searchItems[position].title) {
            " " -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SearchListItemBinding.inflate(layoutInflater, parent, false)
                SearchViewHolder(binding)
            }
            else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SearchListProgressItemBinding.inflate(layoutInflater, parent, false)
                LoadingViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is SearchViewHolder){
            Glide.with(holder.listThumbnail.context)
                .load(getItem(position).thumbnail)
                .into(holder.listThumbnail)
            holder.listTitle.text = getItem(position).title
            holder.listWriter.text = getItem(position).channelTitle
            holder.itemView.setOnClickListener {
                itemClick?.onClick(it, position)
            }
        }
    }

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick : ItemClick? = null

}