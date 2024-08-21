package com.example.blackcows.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blackcows.ListItem
import com.example.blackcows.R
import com.example.blackcows.databinding.FragmentCustomBinding
import com.example.blackcows.ui.adapter.PublicListAdapter
import com.example.blackcows.ui.search.SearchViewModel
import com.example.blackcows.ui.search.SearchViewModelFactory

class CustomFragment : Fragment() {
    private var _binding: FragmentCustomBinding? = null
    private val binding get() = _binding!!
    private val searchAdapter by lazy { PublicListAdapter() }

    private val searchViewModel by activityViewModels<SearchViewModel> {
        SearchViewModelFactory()
    }

    private val detailViewModel by activityViewModels<DetailViewModel> {
        DetailViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomBinding.inflate(inflater,container,false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
    }
    private fun initView() = with(binding) {
        Log.d("그리긴했늬???","힝...")
        // 리싸이클러뷰 코드
        customRecyclerView.isNestedScrollingEnabled = false
        customRecyclerView.layoutManager = GridLayoutManager(this@CustomFragment.context, 2)
        customRecyclerView.adapter = searchAdapter
        searchAdapter.itemClick = object : PublicListAdapter.ItemClick {
            override fun onClick(item: ListItem.VideoItem, position: Int) {
                detailViewModel.position = position
                detailViewModel.setDetailVideoData(item)
                findNavController().navigate(R.id.action_fragment_to_detailFragment)
            }
        }
    }
    private fun initViewModel() = with(searchViewModel) {
        trendingVideos.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it)
            searchAdapter.searchItems = it?.toMutableList() ?: mutableListOf<ListItem.VideoItem>()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}