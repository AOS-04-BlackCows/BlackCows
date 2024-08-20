package com.example.blackcows.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.blackcows.ListItem
import com.example.blackcows.R
import com.example.blackcows.data.repository.FavoriteRepository
import com.example.blackcows.databinding.FragmentDetailBinding
import com.example.blackcows.ui.search.SearchViewModel
import com.example.blackcows.ui.search.SearchViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class DetailFragment : DialogFragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel by activityViewModels<DetailViewModel> { DetailViewModelFactory() }

    private val videoData = detailViewModel.detailVideos.value?.get(detailViewModel.position)
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewPager = binding.pager
        viewPager.apply {
            adapter = ScreenSlidePagerAdapter(requireActivity())
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        // 뷰페이저와 탭레이아웃을 붙임
        TabLayoutMediator(binding.tlTabLayout, viewPager) { tab, position ->
            tab.text = arrayListOf("상세 정보", "관련 영상")[position]
        }.attach()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
//        detailViewModel.getVideoThumbanail()
    }

    private fun initView() {
        binding.tvToolbarTitle.text = videoData?.title
        binding.tvSubTitle.text = videoData?.channelTitle
        lifecycle.addObserver(binding.vvVideo)
        val videoId = videoData?.videoId ?: "3YSuUuNCocY"
        val danawaCode = if (detailViewModel.danawaCategory.category == "0") {
            "https://search.danawa.com/dsearch.php?query=" + detailViewModel.danawaCategory.name
        } else {
            "https://prod.danawa.com/list/?cate=" + detailViewModel.danawaCategory.category
        }

        binding.vvVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)

                Log.d(
                    "영상아이디 내놔!!!",
                    "videoId:${videoId} and ${videoData.toString()}"
                )
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })


        val video = ListItem.VideoItem(
            videoData?.channelTitle ?: "체널명 없음",
            videoData?.title ?: "영상 제목 없음",
            videoData?.thumbnail?: "썸네일 없음",
            videoData?.description?: "영상설명 없음",
            videoData?.videoId?: "영상 없음"
        )

        binding.btnFavrite.setOnClickListener {

            val favoriteRepository = FavoriteRepository(requireContext())

            favoriteRepository.addFavoriteItem(video) //이 코드는 버튼 처음 눌렀을 때(좋아요 시) 추가
//            favoriteRepository.removeFavoriteItem(video) // 이 코드는 버튼 두 번 눌렀을 때(좋아요 취소 시) 추가

            Toast.makeText(this@DetailFragment.context, "좋아요를 누르셨습니다", Toast.LENGTH_SHORT).show()
        }

        binding.btnYoutube.setOnClickListener {
            //https://www.youtube.com/watch?v="id"
            val youtubeUri = Uri.parse("https://www.youtube.com/watch?v=${videoId}")
            startActivity(Intent(Intent.ACTION_VIEW, youtubeUri))
        }
        binding.btnDanawa.setOnClickListener {
            val danawaUri = Uri.parse(danawaCode)
            val intent = Intent(Intent.ACTION_VIEW, danawaUri)
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> InfoFragment()
                else -> CustomFragment()
            }
        }
    }
}