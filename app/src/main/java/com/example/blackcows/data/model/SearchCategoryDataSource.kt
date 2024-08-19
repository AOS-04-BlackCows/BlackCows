package com.example.blackcows.data.model

import com.example.blackcows.ui.search.SearchCategory


data class SearchSubCategory(
    val name : String,
    val category : String
)

object SearchCategoryDataSource {
    fun getSearchSubCategory(searchCategoryType: SearchCategory) : List<SearchSubCategory> {
        return when(searchCategoryType) {
            SearchCategory.HARD_WARE -> {
                listOf(
                    SearchSubCategory("CPU","112747"),
                    SearchSubCategory("RAM","112752"),
                    SearchSubCategory("메인보드","112751"),
                    SearchSubCategory("그래픽카드(VGA)","112753"),
                    SearchSubCategory("SSD","112760"),
                    SearchSubCategory("HDD","112763"),
                    SearchSubCategory("케이스","112775"),
                    SearchSubCategory("파워","112777"),
                    SearchSubCategory("키보드","112782"),
                    SearchSubCategory("마우스","112787"),
                    SearchSubCategory("ODD","112772")
                )
            }
            SearchCategory.SOFT_WARE -> {
                listOf(
                    SearchSubCategory("운영체제","1136152"),
                    SearchSubCategory("사무/문서","1136168"),
                    SearchSubCategory("그래픽/CAD/웹","1136192"),
                    SearchSubCategory("개발툴/유틸리티","1136236"),
                    SearchSubCategory("영상/음향","1136237"),
                    SearchSubCategory("백신/보안/백업","1136220")
                )
            }
            SearchCategory.PERIHERAL_DEVICE -> {
                listOf(
                    SearchSubCategory("쿨러/튜닝","112798"),
                    SearchSubCategory("USB허브","11239702"),
                    SearchSubCategory("케이블/젠더","112799"),
                    SearchSubCategory("컨트롤러/동글","112797"),
                    SearchSubCategory("공유기","112804"),
                    SearchSubCategory("랜카드","11230206"),
                    SearchSubCategory("스위치허브","11230207"),
                    SearchSubCategory("네트워크 주변기기","1131668"),
                    SearchSubCategory("영상/TV/PC캠","112810"),
                    SearchSubCategory("웹캠","1131912"),
                    SearchSubCategory("인터넷 방송 장비","11212809"),
                    SearchSubCategory("PC헤드셋","11252451"),
                    SearchSubCategory("PC스피커","1131892"),
                    SearchSubCategory("사운드카드","11312814"),
                    SearchSubCategory("PC마이크","11312813")
                )
            }
            SearchCategory.GAMING_DEVICE -> {
                listOf(
                    SearchSubCategory("플레이스테이션5/4","11238042"),
                    SearchSubCategory("엑스박스 시리즈/원","11238044"),
                    SearchSubCategory("닌텐도 스위치","11238041")
                )
            }
        }
    }
}



