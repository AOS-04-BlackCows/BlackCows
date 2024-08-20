package com.example.blackcows.ui.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat.canScrollVertically
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blackcows.ListItem
import com.example.blackcows.R
import com.example.blackcows.data.model.SearchCategoryDataSource
import com.example.blackcows.data.model.SearchSubCategory
import com.example.blackcows.databinding.FragmentSearchBinding
import com.example.blackcows.ui.adapter.PublicListAdapter
import com.example.blackcows.ui.detail.DetailFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchAdapter by lazy { PublicListAdapter() }
    private var searchKeyword: String = ""

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
        searchRecyclerView.layoutManager = GridLayoutManager(this@SearchFragment.context, 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (searchAdapter.getItemViewType(position)) {
                        searchAdapter.VIEW_TYPE_ITEM -> 1
                        else -> 2
                    }

                }
            }
        }
        searchRecyclerView.adapter = searchAdapter
        searchAdapter.itemClick = object : PublicListAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                searchViewModel.position = position
                searchViewModel.trendingVideos.value?.get(position)
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
            searchAdapter.searchItems = it?.toMutableList() ?: mutableListOf<ListItem.VideoItem>()
        }
        binding.searchBtn.setOnClickListener {
            searchViewModel.danawaCategory = SearchSubCategory(binding.searchEt.text.toString(), "0")
            getSearchVideos(binding.searchEt.text.toString())
        }
        binding.searchRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition() // 화면에 보이는 마지막 아이템의 position
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1 // 어댑터에 등록된 아이템의 총 개수 -1

                // 스크롤이 끝에 도달했는지 확인
                if (!binding.searchRecyclerView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                    addProgress()
                    addNextPage(searchKeyword, nextPageToken)
                    Handler(Looper.getMainLooper()).postDelayed({
                        deleteProgress()
                    },1000)
                }
                Log.d("lastVisibleItemPosition","lastVisibleItemPosition = $lastVisibleItemPosition")
            }
        })
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
                setTextColor(resources.getColor(R.color.white, null)) // 텍스트 색상 설정
                setChipDrawable(ChipDrawable.createFromAttributes(context, null, 0, R.style.CustomChipStyle))
                with(this) {
                    chipStrokeWidth = 0.0f
                    setOnClickListener {
                        searchViewModel.danawaCategory = i
                        binding.searchEt.setText(i.name)
                        chipGroup.removeAllViews()
                        binding.searchCategory.visibility = View.GONE
                        binding.searchChip.visibility = View.GONE
                    }
                }
            })
        }
    }

    private fun addProgress () {
        binding.progressContainer.visibility = View.VISIBLE
    }

    private fun deleteProgress () {
        binding.progressContainer.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}