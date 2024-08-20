package com.example.blackcows.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blackcows.ListItem
import com.example.blackcows.R
import com.example.blackcows.data.model.SearchCategoryDataSource
import com.example.blackcows.data.model.SearchSubCategory
import com.example.blackcows.databinding.FragmentSearchBinding
import com.example.blackcows.ui.adapter.PublicListAdapter
import com.example.blackcows.ui.detail.DetailFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchAdapter by lazy { PublicListAdapter() }

    private val searchViewModel by activityViewModels<SearchViewModel> {
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
        // 리싸이클러뷰 코드
        searchRecyclerView.isNestedScrollingEnabled = false
        searchRecyclerView.layoutManager = GridLayoutManager(this@SearchFragment.context, 2)
        searchRecyclerView.adapter = searchAdapter
        searchAdapter.itemClick = object : PublicListAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                searchViewModel.position = position
                findNavController().navigate(R.id.action_fragment_to_detailFragment)
            }
        }
        // 카테고리 클릭 시 변경
        val categoryArr = arrayOf(searchCategory1, searchCategory2, searchCategory3, searchCategory4)
        for (i in categoryArr) {
            i.setOnClickListener{
                clickedCategory(i)
            }
        }
        // 검색창 변경 감지 효과
        searchEt.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val str = s.toString()
                if (str.isBlank()) {
                    searchEtClear.alpha = 0.0f
                    binding.searchCategory.visibility = View.VISIBLE
                    binding.searchChip.visibility = View.VISIBLE
                } else {
                    searchEtClear.alpha = 1.0f
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        searchEtClear.setOnClickListener {
            clearSearchEt()
        }
    }
    private fun initViewModel() = with(searchViewModel) {
        trendingVideos.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it)
        }
        binding.searchBtn.setOnClickListener {
            searchViewModel.danawaCategory = SearchSubCategory(binding.searchEt.text.toString(), "0")
            getSearchVideos(binding.searchEt.text.toString())
        }
        binding.searchMore.setOnClickListener {
            addNextPage(searchKeyword, nextPageToken)
        }
    }
    private fun clearSearchEt () {
        binding.searchEt.text = null
    }

    private fun clickedCategory (view: View) {
        val selectStatus: SearchCategory = when (view) {
            binding.searchCategory1 -> SearchCategory.HARD_WARE
            binding.searchCategory2 -> SearchCategory.SOFT_WARE
            binding.searchCategory3 -> SearchCategory.PERIHERAL_DEVICE
            binding.searchCategory4 -> SearchCategory.GAMING_DEVICE
            else -> SearchCategory.HARD_WARE
        }
        val categoryDetail = SearchCategoryDataSource.getSearchSubCategory(selectStatus)
        val chipGroup = binding.searchCategoryDetail
        chipGroup.removeAllViews()
        for (i in categoryDetail) {
            chipGroup.addView(Chip(this.context).apply {
                text = i.name // text 세팅
                this.setOnClickListener {
                    searchViewModel.danawaCategory = i
                    binding.searchEt.setText(i.name)
                    chipGroup.removeAllViews()
                    binding.searchCategory.visibility = View.GONE
                    binding.searchChip.visibility = View.GONE
                }
            })
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}