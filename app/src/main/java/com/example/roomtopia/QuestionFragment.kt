package com.example.roomtopia

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment

class QuestionFragment: Fragment() {
    private lateinit var listView: ListView
    var data: MutableList<QuestionList> = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_question,container,false)
        var questionArray = resources.getStringArray(R.array.question_array)
        insert(questionArray)
        var adapter = QuestionAdapter(view!!.context, data)
        listView = view!!.findViewById(R.id.listview_question)
        listView.adapter = adapter
        return view
    }

    fun insert(questionArray: Array<String>){
        for(i in 0 until 10){
            var text = questionArray[i]
            var questionlist = QuestionList(text)
            data.add(questionlist)
        }
    }
}