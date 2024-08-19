package com.example.blackcows.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blackcows.ListItem
import com.example.blackcows.databinding.HomeItemRecyclerviewBinding
import kotlinx.coroutines.NonDisposableHandle.parent


class HomeRecyclerViewAdapter : RecyclerView.Adapter<HomeRecyclerViewAdapter.CategoryViewHolder>() {

    // ListItem.VideoItem 타입의 객체들을 저장함
    // ListItem.VideoItem : 동영상 항목을 나타내는 데이터 클래스
    // 빈 클래스로 설정, submitList 메서드를 통해 새 데이터로 갱신됨
    private var categoryVideoList = listOf<ListItem.VideoItem>()

    // 새로운 동영상 리스트를 받아 adapter의 데이터로 설정함
    fun submitList(videos: List<ListItem.VideoItem>) {
        categoryVideoList = videos
        // 데이터가 변경되었음을 RecyclerView에 알리고, 화면에 표시된 내용을 갱신함
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val binding =
            HomeItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryVideoList[position])
    }

    override fun getItemCount() = categoryVideoList.size

    class CategoryViewHolder(private val binding: HomeItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(video: ListItem.VideoItem) {
            // 텍스트 뷰 설정
            binding.tvCategoryTitle.text = video.title
            // 이미지뷰 로드
            Glide.with(binding.root.context).load(video.thumbnail).into(binding.ivCategory)
        }
    }
}