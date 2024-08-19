package com.example.blackcows.ui.adapter

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

class PublicListAdapter : ListAdapter<ListItem.VideoItem, PublicListAdapter.SearchViewHolder>(object : DiffUtil.ItemCallback<ListItem.VideoItem>() {
    override fun areItemsTheSame(oldItem: ListItem.VideoItem, newItem: ListItem.VideoItem): Boolean {
        return oldItem.thumbnail == newItem.thumbnail && oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ListItem.VideoItem, newItem: ListItem.VideoItem): Boolean {
        return oldItem == newItem
    }
}) {
    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_list_item, parent, false)
        return SearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        Glide.with(holder.listThumbnail.context)
            .load(getItem(position).thumbnail)
            .into(holder.listThumbnail)
        holder.listTitle.text = getItem(position).title
        holder.listWriter.text = getItem(position).channelTitle
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
    }

    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val listThumbnail: ImageView = view.findViewById(R.id.search_thumb)
        val listTitle: TextView = view.findViewById(R.id.search_title)
        val listWriter: TextView = view.findViewById(R.id.search_writer)
    }


}