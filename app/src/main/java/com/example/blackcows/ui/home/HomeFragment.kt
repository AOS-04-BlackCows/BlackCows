package com.example.blackcows.ui.home

import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blackcows.R
import com.example.blackcows.data.remote.SearchVideoRemoteDataSource
import com.example.blackcows.data.repository.YoutubeRepositoryImpl
import com.example.blackcows.databinding.FragmentHomeBinding
import retrofit2.http.POST


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels { HomeViewModelFactory() }
    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter
    private lateinit var homeRecyclerViewAdapter: HomeRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // viewPager 설정
        homeViewPagerAdapter = HomeViewPagerAdapter(emptyList())
        binding.homeViewpager.adapter = homeViewPagerAdapter

        // RecyclerView 설정
        homeRecyclerViewAdapter = HomeRecyclerViewAdapter()
        binding.homeRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.homeRecyclerview.adapter = homeRecyclerViewAdapter

        // Spinner 설정
        val homeCategories = listOf(
            HomeCategoryDataClass("하드웨어", "28"),
            HomeCategoryDataClass("소프트웨어", "29"),
            HomeCategoryDataClass("주변기기","30"),
            HomeCategoryDataClass("게이밍용품","31")
        )

        val spinnerAdapter =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                homeCategories.map { it.name }
            )
        binding.homeSpinner.adapter = spinnerAdapter

        // Spinner의 아이템 선택 이벤트 처리
        binding.homeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCategory = homeCategories[position]
                homeViewModel.getCategoryVideos(selectedCategory.id)
                // 선택된 카테고리 쿼리로 비디오 요청

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // 아무것도 선택되지 않은 경우 처리할 로직이 있을 경우 작성
            }
        }

        homeViewModel.categoryVideos.observe(viewLifecycleOwner, Observer { videos ->
            homeRecyclerViewAdapter.submitList(videos)
        })

    }
}
