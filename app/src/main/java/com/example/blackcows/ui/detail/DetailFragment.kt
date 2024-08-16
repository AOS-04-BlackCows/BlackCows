package com.example.blackcows.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.blackcows.R
import com.example.blackcows.databinding.FragmentDetailBinding

class DetailFragment : DialogFragment() {

    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val detailViewModel by viewModels<DetailViewModel> { DetailViewModelFactory() }

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
//        val detailViewModel =
//            ViewModelProvider(this).get(DetailViewModel::class.java)

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        adapter = DetailPagerAdapter(this)
//        binding.viewPager2.adapter = adapter
//
//        // TabLayout과 ViewPager2 연결
//        TabLayoutMediator(binding.tlTabLayout, binding.viewPager2) { tab, position ->
//            tab.text = when (position) {
//                0 -> "상세내용"
//                1 -> "관련 영상"
//                else -> null
//            }
//        }.attach()

//        val textView: TextView = binding.textNotifications
//        detailViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel.getVideoThumbanail()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class DetailPagerAdapter(idolList: ArrayList<Int>) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
        var item = idolList

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

        override fun getItemCount(): Int = item.size

        override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
            holder.idol.setImageResource(item[position])
        }

        inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
            (LayoutInflater.from(parent.context).inflate(R.layout.idol_list_item, parent, false)){

            val idol = itemView.imageView_idol!!
        }
    }
}