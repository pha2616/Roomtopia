package com.example.roomtopia

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class QuestionFragment: Fragment(), OnItemClick {
    private lateinit var listView: ListView
    private lateinit var result_btn: Button
    var data: MutableList<QuestionList> = mutableListOf()
    var bundle: Bundle = Bundle()
    private var scores = IntArray(10)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_question,container,false)
        var questionArray = resources.getStringArray(R.array.question_array)
        result_btn = view!!.findViewById(R.id.result_btn)
        insert(questionArray)
        var adapter = QuestionAdapter(view!!.context, data, result_btn, this)
        listView = view!!.findViewById(R.id.listview_question)
        listView.adapter = adapter
        setListViewHeightBasedOnChildren(listView)

        result_btn.setOnClickListener {
            bundle.putIntArray("scores", scores)

        }

        return view
    }

    fun insert(questionArray: Array<String>){
        for(i in 0 until 10){
            var text = questionArray[i]
            var questionlist = QuestionList(text)
            data.add(questionlist)
        }
    }
    
    fun setListViewHeightBasedOnChildren(listview: ListView){
        var listAdapter = listview.adapter
        if(listAdapter == null) return
        var totalHeight = 0

        for(i in 0 until listAdapter.count){
            var listItem: View = listAdapter.getView(i, null, listview)
            var px = 500*(listview.resources.displayMetrics.density)
            listItem.measure(View.MeasureSpec.makeMeasureSpec(px.toInt(), View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
            totalHeight += listItem.measuredHeight
        }

        var totalDividersHeight = listview.dividerHeight*(listAdapter.count - 1)
        var totalPadding = listview.paddingTop + listview.paddingBottom
        var params: ViewGroup.LayoutParams = listview.layoutParams
        params.height = totalHeight + totalDividersHeight + totalPadding
        listview.layoutParams = params
        listview.requestLayout()
    }

    fun replaceFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.main_content, fragment)
        fragmentTransaction.addToBackStack(null)
        fragment.arguments = bundle
        fragmentTransaction.commit()
    }

    override fun onClick(scores: IntArray) {
        this.scores = scores
    }
}