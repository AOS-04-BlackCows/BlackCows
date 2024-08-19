package com.example.blackcows.data.validation

import java.util.regex.Pattern

fun isRegularName(name: String): Boolean {
    val namePattern = "^[0-9a-zA-Zㄱ-ㅎ가-힣ㅏ-ㅣ]{2,10}$"
    val pattern = Pattern.matches(namePattern,name)
    return pattern
}