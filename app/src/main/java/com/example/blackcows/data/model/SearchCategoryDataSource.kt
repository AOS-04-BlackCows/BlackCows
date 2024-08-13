package com.example.blackcows.data.model

import com.example.blackcows.ui.search.SearchCategory

object SearchCategoryDataSource {
    fun getSearchSubCategory(searchCategoryType : SearchCategory) : List<SearchSubCategory> {
        return when(searchCategoryType) {
            SearchCategory.HARD_WARE -> {
               listOf(
                   SearchSubCategory("CPU"),
                   SearchSubCategory("메인보드")
               )
            }
            SearchCategory.SOFT_WARE -> {
                listOf(
                    SearchSubCategory("CPU"),
                    SearchSubCategory("메인보드")
                )
            }
            SearchCategory.PERIHERAL_DEVICE -> {
                listOf(
                    SearchSubCategory("CPU"),
                    SearchSubCategory("메인보드")
                )
            }
            SearchCategory.GAMING_DEVICE -> {
                listOf(
                    SearchSubCategory("CPU"),
                    SearchSubCategory("메인보드")
                )
            }
        }
    }
}

data class SearchSubCategory(
    val name : String
)

// Todo Category init

// Depth1
// 하드웨어, 소프트웨어, 주변기기, 게이밍용품
val depth1List = mapOf("하드웨어" to "hardwareList", "소프트웨어" to "softwareList", "주변기기" to "peripheralDeviceList", "게이밍용품" to "gamingDeviceList")
val depth2List = mapOf(
    "hardwareList" to listOf("CPU", "메인보드", "메모리", "저장장치", "그래픽카드", "파워 서플라이", "케이스", "쿨러", "네트워크 카드", "사운드 카드", "입출력 포트", "외장 드라이브", "모니터", "키보드", "마우스", "프린터", "스캐너", "웹캠", "게임패드", "스피커"),
    "softwareList" to listOf("운영체제", "응용 프로그램", "보안", "개발 도구", "데이터베이스 관리", "웹 브라우저", "그래픽 디자인", "비디오 편집", "음악 제작", "게임", "시스템 유틸리티"),
    "peripheralDeviceList" to listOf("입력장치", "출력장치", "저장장치", "네트워킹 장치", "오디오 장치", "비디오 장치", "게임 장치", "스캐너", "웹캠", "프린터", "모니터", "키보드", "마우스", "게임패드", "스피커", "헤드셋", "마이크", "외장 하드 드라이브", "USB 허브"),
    "gamingDeviceList" to listOf("게이밍 마우스", "게이밍 키보드", "헤드셋", "모니터", "게이밍 의자", "게임패드", "마우스패드", "스탠드", "웹캠", "서라운드 사운드 시스템", "VR 헤드셋", "조이스틱", "외장 하드 드라이브", "게임 콘솔", "캡처 카드", "게임 액세서리", "냉각 패드", "스피커", "RGB 조명 장비")
)



