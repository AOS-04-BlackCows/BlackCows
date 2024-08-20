package com.example.blackcows.ui.home

import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blackcows.ListItem
import com.example.blackcows.R
import com.example.blackcows.data.remote.SearchVideoRemoteDataSource
import com.example.blackcows.data.repository.YoutubeRepositoryImpl
import com.example.blackcows.databinding.FragmentHomeBinding
import com.example.blackcows.ui.adapter.PublicListAdapter
import com.example.blackcows.ui.search.SearchViewModel
import com.example.blackcows.ui.search.SearchViewModelFactory
import retrofit2.http.POST


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter
    private lateinit var homeRecyclerViewAdapter: HomeRecyclerViewAdapter

    private val searchViewModel by activityViewModels<SearchViewModel> {
        SearchViewModelFactory()

    }

    private val homeViewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory()
    }


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

        // recyclerView 설정
        homeRecyclerViewAdapter = HomeRecyclerViewAdapter()
        binding.homeRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeRecyclerViewAdapter
        }

        // ViewPager 설정
        homeViewPagerAdapter = HomeViewPagerAdapter()
        binding.homeViewpager.apply {
            adapter = homeViewPagerAdapter
        }

        // spinner 설정 -> 카테고리에 맞는 검색 결과를 화면에 띄우기
        val categories = listOf("하드웨어", "소프트웨어", "주변기기", "게임밍용품")
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            categories
        )
        binding.homeSpinner.adapter = spinnerAdapter

        binding.homeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent : AdapterView<*>?, view : View?, position : Int, id : Long) {
                val selectedItem = parent?.getItemAtPosition(position)
                homeViewModel.getHomeVideos(selectedItem.toString() , null )
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        // recyclerView 아이템 뷰 클릭 이벤트
        homeRecyclerViewAdapter.itemClick = object : HomeRecyclerViewAdapter.ItemClick {
            override fun onClick(item : ListItem.VideoItem, position: Int) {
                Log.d("homeFragment","RecyclerView-Category-Click")
                searchViewModel.position = position
                findNavController().apply {
                    navigate(R.id.action_fragment_to_detailFragment)
                }
            }
        }

        // ViewPager 아이템 뷰 클릭 이벤트
        homeViewPagerAdapter.itemClick = object : HomeViewPagerAdapter.ItemClick {
            override fun onClick(item : ListItem.VideoItem, position: Int) {
                Log.d("homeFragment","ViewPager-Popular-Click")
                searchViewModel.position = position
                findNavController().apply {
                    navigate(R.id.action_fragment_to_detailFragment)
                }
            }
        }

        //ViewModel 데이터 관찰
        initViewModel()
    }
    private fun initViewModel() = with(homeViewModel) {
        homeVideos.observe(viewLifecycleOwner) {
            homeRecyclerViewAdapter.submitList(it)
        }

        // 인기 동영상 데이터를 가져와 ViewPagerAdapter에 반영함
        popularVideos.observe(viewLifecycleOwner) {
            homeViewPagerAdapter.submitList(it)
        }

        getPopularVideos("컴퓨터", null)

    }


}