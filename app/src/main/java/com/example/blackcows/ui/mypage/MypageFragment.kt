package com.example.blackcows.ui.mypage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blackcows.ListItem
import com.example.blackcows.MypageDialog
import com.example.blackcows.R
import com.example.blackcows.databinding.FragmentMypageBinding
import com.example.blackcows.ui.adapter.MypageListAdapter
import com.example.blackcows.ui.adapter.PublicListAdapter

class MypageFragment : Fragment() {

    //context, viewModel
    private lateinit var mContext : Context
    private val mypageViewModel : MypageViewModel by activityViewModels()

    //binding
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!

    //Adapter
    private lateinit var mypageAdapter : MypageListAdapter

    //좋아요 한 Item
    private var likedItems: List<ListItem.VideoItem> = listOf()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.apply {

            //name textview 클릭 시 dialog 호출
            tvMypageName.setOnClickListener {
                val dialog = MypageDialog(requireContext())
                dialog.setOnClickedListener(object : MypageDialog.ButtonClickListener {
                    override fun onClicked(content: String) {
                        binding.tvMypageName.text = content
                    }
                })
                dialog.show("name","이름","이름 편집")
            }

            //introduction textview 클릭 시 dialog 호출
            tvMypageIntroduction.setOnClickListener {
                val dialog = MypageDialog(requireContext())
                dialog.setOnClickedListener(object : MypageDialog.ButtonClickListener {
                    override fun onClicked(content: String) {
                        binding.tvMypageIntroduction.text = content
                    }
                })
                dialog.show("introduction","인삿말","인삿말 편집")
            }
        }

        //ViewModel 좋아요 한 items 가져옴
        mypageViewModel.getlikeItems(mContext)


        mypageAdapter = MypageListAdapter(mContext).apply {
            items = likedItems.toMutableList()
        }

        //데이터 수정 여부에 따라 업데이트 되도록 notify 이용
        mypageViewModel.likeItems.observe(viewLifecycleOwner){like ->
//            mypageAdapter.items = like.toMutableList()
//            mypageAdapter.notifyDataSetChanged()
            mypageAdapter.submitList(like)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //recyclerView 설정, gridLayout 형식
        //profile Image 모서리를 둥글게 하기 위해 clipToOutline 작성
        binding.apply {
            mypageRecyclerView.layoutManager = GridLayoutManager(this@MypageFragment.context,2)
            mypageRecyclerView.adapter = mypageAdapter
            mypageAdapter.itemClick = object : MypageListAdapter.ItemClick {
                override fun onClick(view: View, position: Int) {

                    Toast.makeText(this@MypageFragment.context, "클릭이 되어버렸다", Toast.LENGTH_SHORT).show()

                    findNavController().navigate(R.id.action_fragment_to_detailFragment)
                }
            }
            ivMypageProfile.clipToOutline = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}