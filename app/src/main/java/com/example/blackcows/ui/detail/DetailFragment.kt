package com.example.blackcows.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.blackcows.R
import com.example.blackcows.databinding.FragmentDetailBinding
import com.example.blackcows.ui.search.SearchViewModel
import com.example.blackcows.ui.search.SearchViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class DetailFragment : DialogFragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel by viewModels<DetailViewModel> { DetailViewModelFactory() }
    private val searchViewModel by activityViewModels<SearchViewModel> {
        SearchViewModelFactory()
    }

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_TITLE, R.style.dialog_fullscreen)
        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = true
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
        binding.tvToolbarTitle.text =
            searchViewModel.trendingVideos.value?.get(searchViewModel.position)?.title
        binding.tvSubTitle.text =
            searchViewModel.trendingVideos.value?.get(searchViewModel.position)?.channelTitle
        lifecycle.addObserver(binding.vvVideo)
        val videoId = searchViewModel.trendingVideos.value?.get(searchViewModel.position)?.videoId
            ?: "3YSuUuNCocY"
        val danawaCode = if (searchViewModel.danawaCategory.category == "0") {
            "https://search.danawa.com/dsearch.php?query=" + searchViewModel.danawaCategory.name
        } else {
            "https://prod.danawa.com/list/?cate=" + searchViewModel.danawaCategory.category
        }

        binding.vvVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)


                Log.d(
                    "영상아이디 내놔!!!",
                    "videoId:${videoId} and ${
                        searchViewModel.trendingVideos.value?.get(searchViewModel.position)
                            .toString()
                    }"
                )
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })

        binding.btnFavrite
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