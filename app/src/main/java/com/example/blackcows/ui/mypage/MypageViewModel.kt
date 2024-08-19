package com.example.blackcows.ui.mypage

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blackcows.ListItem
import com.example.blackcows.data.repository.FavoriteRepository

class MypageViewModel : ViewModel() {

    private val _likeItems = MutableLiveData<List<ListItem.VideoItem>>()
    val likeItems : LiveData<List<ListItem.VideoItem>> get() = _likeItems

    // FavoriteRepository에서 favoriteItems로 초기화한 메서드
    // favoriteItems = _favoriteItems를 List형으로 가져옴
    fun getlikeItems(context: Context){
        _likeItems.value = FavoriteRepository(context).favoriteItems
    }
}