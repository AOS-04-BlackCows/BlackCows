package com.example.blackcows.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.blackcows.R
import com.example.blackcows.databinding.FragmentDetailBinding
import com.example.blackcows.databinding.FragmentInfoBinding
import com.example.blackcows.ui.search.SearchViewModel
import com.example.blackcows.ui.search.SearchViewModelFactory


class InfoFragment : Fragment() {
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel by activityViewModels<SearchViewModel> {
        SearchViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
//        detailViewModel.getVideoThumbanail()
    }
    private fun initView() {
        binding.tvDescription.text = searchViewModel.trendingVideos.value?.get(searchViewModel.position)?.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}