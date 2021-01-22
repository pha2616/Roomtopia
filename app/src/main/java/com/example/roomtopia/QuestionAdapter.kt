package com.example.roomtopia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class QuestionAdapter(context: Context, list: List<QuestionList>, res_btn: Button, listener: OnItemClick, progress_text: TextView, progressBar: ProgressBar): BaseAdapter() {
    private lateinit var context: Context
    private lateinit var list: List<QuestionList>
    private lateinit var inflate: LayoutInflater
    private lateinit var result_btn: Button
    private lateinit var mCallback: OnItemClick
    private lateinit var progress_text: TextView
    private lateinit var progressBar: ProgressBar
    private var scores = IntArray(10)

    init{
        this.list = list
        this.context = context
        this.inflate = LayoutInflater.from(context)
        this.result_btn = res_btn
        this.mCallback = listener
        this.progress_text = progress_text
        this.progressBar = progressBar
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
            scores[p0] = 5
            btn1.setBackgroundResource(R.drawable.checked1)
            btn2.setBackgroundResource(R.drawable.btn2)
            btn3.setBackgroundResource(R.drawable.btn3)
            btn4.setBackgroundResource(R.drawable.btn4)
            btn5.setBackgroundResource(R.drawable.btn5)
            checkProgress()
        }
        btn2.setOnClickListener {
            list[p0].checked = true
            list[p0].checked_btn = 2
            scores[p0] = 4
            btn2.setBackgroundResource(R.drawable.checked2)
            btn1.setBackgroundResource(R.drawable.btn1)
            btn3.setBackgroundResource(R.drawable.btn3)
            btn4.setBackgroundResource(R.drawable.btn4)
            btn5.setBackgroundResource(R.drawable.btn5)
            checkProgress()
        }
        btn3.setOnClickListener {
            list[p0].checked = true
            list[p0].checked_btn = 3
            scores[p0] = 3
            btn3.setBackgroundResource(R.drawable.checked3)
            btn2.setBackgroundResource(R.drawable.btn2)
            btn1.setBackgroundResource(R.drawable.btn1)
            btn4.setBackgroundResource(R.drawable.btn4)
            btn5.setBackgroundResource(R.drawable.btn5)
            checkProgress()
        }
        btn4.setOnClickListener {
            list[p0].checked = true
            list[p0].checked_btn = 4
            scores[p0] = 2
            btn4.setBackgroundResource(R.drawable.checked4)
            btn2.setBackgroundResource(R.drawable.btn2)
            btn3.setBackgroundResource(R.drawable.btn3)
            btn1.setBackgroundResource(R.drawable.btn1)
            btn5.setBackgroundResource(R.drawable.btn5)
            checkProgress()
        }
        btn5.setOnClickListener {
            list[p0].checked = true
            list[p0].checked_btn = 5
            scores[p0] = 1
            btn5.setBackgroundResource(R.drawable.checked5)
            btn2.setBackgroundResource(R.drawable.btn2)
            btn3.setBackgroundResource(R.drawable.btn3)
            btn4.setBackgroundResource(R.drawable.btn4)
            btn1.setBackgroundResource(R.drawable.btn1)
            checkProgress()
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

    fun isFinished(): Boolean{
        for(i in 0 until list.size){
            if(!list[i].checked){
                return false
            }
        }
        return true
    }

    fun checkProgress(){
        var count = 0
        for(i in 0 until list.size){
            if(list[i].checked){
                count++
            }
        }
        progress_text.text = (count * 10).toString() + "%"
        progressBar.progress = count * 10

        if(progressBar.progress == 100){
            result_btn.setBackgroundResource(R.drawable.result_after_btn)
            result_btn.isEnabled = true
            mCallback.onClick(scores)
        }
    }
}