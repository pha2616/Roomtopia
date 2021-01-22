package com.example.roomtopia

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
        insert()
        var adapter = QuestionAdapter(view!!.context, data)
        listView = view!!.findViewById(R.id.listview_question)
        listView.adapter = adapter
        return view
    }

    fun insert(){
        for(i in 0 until 10){
            var text = "hi"+ i.toString()
            var questionlist = QuestionList(text)
            data.add(questionlist)
        }
    }
}