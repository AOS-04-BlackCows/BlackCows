package com.example.blackcows.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blackcows.databinding.FragmentSearchBinding
import com.example.blackcows.ui.adapter.PublicListAdapter


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchAdapter by lazy { PublicListAdapter() }

    private val searchViewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
    }

    private fun initView() = with(binding) {
        searchRecyclerView.isNestedScrollingEnabled = false
        searchRecyclerView.layoutManager = GridLayoutManager(this@SearchFragment.context, 2)
        searchRecyclerView.adapter = searchAdapter
        searchAdapter.itemClick = object : PublicListAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val clickItem = searchViewModel.trendingVideos.value!!.get(position)
                Toast.makeText(this@SearchFragment.context, "클릭이 되어버렸다", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initViewModel() = with(searchViewModel) {
        trendingVideos.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it)
        }
        fetchTrendingVideos("KR")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}