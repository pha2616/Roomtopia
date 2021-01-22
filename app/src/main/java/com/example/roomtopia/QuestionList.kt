package com.example.roomtopia

data class QuestionList(var question: String){
    var checked: Boolean = false
    var checked_btn: Int = 1

    fun isChecked(): Boolean{
        return checked
    }
}