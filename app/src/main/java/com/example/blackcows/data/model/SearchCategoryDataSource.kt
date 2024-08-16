package com.example.blackcows.data.model

import com.example.blackcows.ui.search.SearchCategory


data class SearchSubCategory(
    val name : String
)

object SearchCategoryDataSource {
    fun getSearchSubCategory(searchCategoryType: SearchCategory) : List<SearchSubCategory> {
        return when(searchCategoryType) {
            SearchCategory.HARD_WARE -> {
                listOf(
                    SearchSubCategory("CPU"),
                    SearchSubCategory("메인보드"),
                    SearchSubCategory("메모리"),
                    SearchSubCategory("저장장치"),
                    SearchSubCategory("그래픽카드"),
                    SearchSubCategory("파워 서플라이"),
                    SearchSubCategory("케이스"),
                    SearchSubCategory("쿨러"),
                    SearchSubCategory("네트워크 카드"),
                    SearchSubCategory("사운드 카드"),
                    SearchSubCategory("입출력 포트"),
                    SearchSubCategory("외장 드라이브"),
                    SearchSubCategory("모니터"),
                    SearchSubCategory("키보드"),
                    SearchSubCategory("마우스"),
                    SearchSubCategory("프린터"),
                    SearchSubCategory("스캐너"),
                    SearchSubCategory("웹캠"),
                    SearchSubCategory("게임패드"),
                    SearchSubCategory("스피커")
                )
            }
            SearchCategory.SOFT_WARE -> {
                listOf(
                    SearchSubCategory("운영체제"),
                    SearchSubCategory("응용 프로그램"),
                    SearchSubCategory("보안"),
                    SearchSubCategory("개발 도구"),
                    SearchSubCategory("데이터베이스 관리"),
                    SearchSubCategory("웹 브라우저"),
                    SearchSubCategory("그래픽 디자인"),
                    SearchSubCategory("비디오 편집"),
                    SearchSubCategory("음악 제작"),
                    SearchSubCategory("게임"),
                    SearchSubCategory("시스템 유틸리티")
                )
            }
            SearchCategory.PERIHERAL_DEVICE -> {
                listOf(
                    SearchSubCategory("입력장치"),
                    SearchSubCategory("출력장치"),
                    SearchSubCategory("저장장치"),
                    SearchSubCategory("네트워킹 장치"),
                    SearchSubCategory("오디오 장치"),
                    SearchSubCategory("비디오 장치"),
                    SearchSubCategory("게임 장치"),
                    SearchSubCategory("스캐너"),
                    SearchSubCategory("웹캠"),
                    SearchSubCategory("프린터"),
                    SearchSubCategory("모니터"),
                    SearchSubCategory("키보드"),
                    SearchSubCategory("마우스"),
                    SearchSubCategory("게임패드"),
                    SearchSubCategory("스피커"),
                    SearchSubCategory("헤드셋"),
                    SearchSubCategory("마이크"),
                    SearchSubCategory("외장 하드 드라이브"),
                    SearchSubCategory("USB 허브")
                )
            }
            SearchCategory.GAMING_DEVICE -> {
                listOf(
                    SearchSubCategory("게이밍 마우스"),
                    SearchSubCategory("게이밍 키보드"),
                    SearchSubCategory("헤드셋"),
                    SearchSubCategory("모니터"),
                    SearchSubCategory("게이밍 의자"),
                    SearchSubCategory("게임패드"),
                    SearchSubCategory("마우스패드"),
                    SearchSubCategory("스탠드"),
                    SearchSubCategory("웹캠"),
                    SearchSubCategory("서라운드 사운드 시스템"),
                    SearchSubCategory("VR 헤드셋"),
                    SearchSubCategory("조이스틱"),
                    SearchSubCategory("외장 하드 드라이브"),
                    SearchSubCategory("게임 콘솔"),
                    SearchSubCategory("캡처 카드"),
                    SearchSubCategory("게임 액세서리"),
                    SearchSubCategory("냉각 패드"),
                    SearchSubCategory("스피커"),
                    SearchSubCategory("RGB 조명 장비")
                )
            }
        }
    }
}

// Depth1
// 하드웨어, 소프트웨어, 주변기기, 게이밍용품
//val depth1List = mapOf("하드웨어" to "hardwareList", "소프트웨어" to "softwareList", "주변기기" to "peripheralDeviceList", "게이밍용품" to "gamingDeviceList")
//val depth2List = mapOf(
//    "hardwareList" to listOf("CPU", "메인보드", "메모리", "저장장치", "그래픽카드", "파워 서플라이", "케이스", "쿨러", "네트워크 카드", "사운드 카드", "입출력 포트", "외장 드라이브", "모니터", "키보드", "마우스", "프린터", "스캐너", "웹캠", "게임패드", "스피커"),
//    "softwareList" to listOf("운영체제", "응용 프로그램", "보안", "개발 도구", "데이터베이스 관리", "웹 브라우저", "그래픽 디자인", "비디오 편집", "음악 제작", "게임", "시스템 유틸리티"),
//    "peripheralDeviceList" to listOf("입력장치", "출력장치", "저장장치", "네트워킹 장치", "오디오 장치", "비디오 장치", "게임 장치", "스캐너", "웹캠", "프린터", "모니터", "키보드", "마우스", "게임패드", "스피커", "헤드셋", "마이크", "외장 하드 드라이브", "USB 허브"),
//    "gamingDeviceList" to listOf("게이밍 마우스", "게이밍 키보드", "헤드셋", "모니터", "게이밍 의자", "게임패드", "마우스패드", "스탠드", "웹캠", "서라운드 사운드 시스템", "VR 헤드셋", "조이스틱", "외장 하드 드라이브", "게임 콘솔", "캡처 카드", "게임 액세서리", "냉각 패드", "스피커", "RGB 조명 장비")
//)



