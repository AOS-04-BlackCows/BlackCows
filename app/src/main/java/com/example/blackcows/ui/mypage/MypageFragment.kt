package com.example.blackcows.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.blackcows.databinding.FragmentMypageBinding

class MypageFragment : Fragment() {

    private var _binding: FragmentMypageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mypageViewModel by viewModels<MypageViewModel> {
        MypageViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val mypageViewModel =
//            ViewModelProvider(this).get(MypageViewModel::class.java)

        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.ivMypageProfile.clipToOutline =true
        return root

//        val textView: TextView = binding.textNotifications
//        mypageViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}