package com.example.blackcows

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.example.blackcows.data.validation.isRegularName
import com.example.blackcows.databinding.DialogMypageBinding

class MypageDialog(private val context: Context) {
    private lateinit var binding: DialogMypageBinding
    private val dialog = Dialog(context)

    fun show(type: String, text: String, hint: String) {
        when (type) {
            "name" -> {
                var isName = true

                dialog.show()

                binding = DialogMypageBinding.inflate(dialog.layoutInflater)
                dialog.setContentView(binding.root)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setCancelable(false)
                dialog.window!!.setLayout(900, 600)

                binding.apply {
                    tvEditName.text = text
                    etEditName.hint = hint

                    etEditName.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {

                        }

                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                        }

                        override fun onTextChanged(
                            s: CharSequence?,
                            start: Int,
                            before: Int,
                            count: Int
                        ) {
                            if (isRegularName(etEditName.text.toString().trim())) {
                                tvWrong.visibility = View.INVISIBLE
                                isName = true
                            }
                            else if(etEditName.text.length<=2 || etEditName.text.length>10){
                                tvWrong.visibility = View.VISIBLE
                                tvWrong.text = "2글자 이상 10글자 이하로 입력해주세요"
                                isName = false
                            }
                            else{
                                tvWrong.visibility = View.VISIBLE
                                tvWrong.text = "특수문자는 입력할 수 없습니다."
                                isName = false
                            }

                        }

                    })

                    btnEditSave.setOnClickListener {
                        if(isName) {
                            onClickedListener?.onClicked(etEditName.text.toString())
                            dialog.dismiss()
                        }
                        else{
                            binding.tvWrong.text = "유효하지 않은 입력입니다."
                        }
                    }
                    btnEditCancel.setOnClickListener {
                        dialog.cancel()
                    }
                }
            }

            "introduction" -> {
                dialog.show()

                binding = DialogMypageBinding.inflate(dialog.layoutInflater)
                dialog.setContentView(binding.root)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setCancelable(false)
                dialog.window!!.setLayout(900, 500)

                binding.apply {
                    tvEditName.text = text
                    etEditName.hint = hint

                    btnEditSave.setOnClickListener {
                        onClickedListener?.onClicked(etEditName.text.toString())
                        dialog.dismiss()
                    }
                    btnEditCancel.setOnClickListener {
                        dialog.cancel()
                    }
                }
            }
        }
    }

    interface ButtonClickListener {
        fun onClicked(content: String)
    }

    private var onClickedListener: ButtonClickListener? = null

    fun setOnClickedListener(listener: ButtonClickListener) {
        onClickedListener = listener
    }
}