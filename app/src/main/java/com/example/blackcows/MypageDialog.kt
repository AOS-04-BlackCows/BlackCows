package com.example.blackcows

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.blackcows.databinding.DialogMypageBinding

class MypageDialog(private val context: Context) {
    private lateinit var binding : DialogMypageBinding
    private val dialog = Dialog(context)

    fun show(type:String, text:String, hint : String){
        when(type){
            "name" -> {
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