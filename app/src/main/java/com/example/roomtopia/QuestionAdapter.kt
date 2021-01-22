package com.example.roomtopia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class QuestionAdapter(context: Context, list: List<QuestionList>): BaseAdapter() {
    private lateinit var context: Context
    private lateinit var list: List<QuestionList>
    private lateinit var inflate: LayoutInflater

    init{
        this.list = list
        this.context = context
        this.inflate = LayoutInflater.from(context)
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View? {
        var convertView: View? = inflate.inflate(R.layout.question_list, null)
        var text = convertView!!.findViewById<TextView>(R.id.question_text)
        var image = convertView!!.findViewById<ImageView>(R.id.explain_image)
        var btn1 = convertView!!.findViewById<Button>(R.id.btn1)
        var btn2 = convertView!!.findViewById<Button>(R.id.btn2)
        var btn3 = convertView!!.findViewById<Button>(R.id.btn3)
        var btn4 = convertView!!.findViewById<Button>(R.id.btn4)
        var btn5 = convertView!!.findViewById<Button>(R.id.btn5)

        if(list[p0].checked){
            if(list[p0].checked_btn == 1){
                btn1.setBackgroundResource(R.drawable.checked1)
            }
            else if(list[p0].checked_btn == 2){
                btn2.setBackgroundResource(R.drawable.checked2)
            }
            else if(list[p0].checked_btn == 3){
                btn3.setBackgroundResource(R.drawable.checked3)
            }
            else if(list[p0].checked_btn == 4){
                btn4.setBackgroundResource(R.drawable.checked4)
            }
            else{
                btn5.setBackgroundResource(R.drawable.checked5)
            }
        }

        btn1.setOnClickListener {
            list[p0].checked = true
            list[p0].checked_btn = 1
            btn1.setBackgroundResource(R.drawable.checked1)
            btn2.setBackgroundResource(R.drawable.btn2)
            btn3.setBackgroundResource(R.drawable.btn3)
            btn4.setBackgroundResource(R.drawable.btn4)
            btn5.setBackgroundResource(R.drawable.btn5)
        }
        btn2.setOnClickListener {
            list[p0].checked = true
            list[p0].checked_btn = 2
            btn2.setBackgroundResource(R.drawable.checked2)
            btn1.setBackgroundResource(R.drawable.btn1)
            btn3.setBackgroundResource(R.drawable.btn3)
            btn4.setBackgroundResource(R.drawable.btn4)
            btn5.setBackgroundResource(R.drawable.btn5)
        }
        btn3.setOnClickListener {
            list[p0].checked = true
            list[p0].checked_btn = 3
            btn3.setBackgroundResource(R.drawable.checked3)
            btn2.setBackgroundResource(R.drawable.btn2)
            btn1.setBackgroundResource(R.drawable.btn1)
            btn4.setBackgroundResource(R.drawable.btn4)
            btn5.setBackgroundResource(R.drawable.btn5)
        }
        btn4.setOnClickListener {
            list[p0].checked = true
            list[p0].checked_btn = 4
            btn4.setBackgroundResource(R.drawable.checked4)
            btn2.setBackgroundResource(R.drawable.btn2)
            btn3.setBackgroundResource(R.drawable.btn3)
            btn1.setBackgroundResource(R.drawable.btn1)
            btn5.setBackgroundResource(R.drawable.btn5)
        }
        btn5.setOnClickListener {
            list[p0].checked = true
            list[p0].checked_btn = 5
            btn5.setBackgroundResource(R.drawable.checked5)
            btn2.setBackgroundResource(R.drawable.btn2)
            btn3.setBackgroundResource(R.drawable.btn3)
            btn4.setBackgroundResource(R.drawable.btn4)
            btn1.setBackgroundResource(R.drawable.btn1)
        }

        if(p0 == 0){
            image.setImageResource(R.drawable.test_explain)
        }
        text.text = list[p0].question
        return convertView
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }
}