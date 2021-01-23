package com.example.roomtopia

import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Parcel
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class ResultFragment: Fragment() {
    private val IP_ADDRESS = "13.59.190.39:5000"
    private lateinit var mJsonInt: String
    private lateinit var title: ImageView
    private lateinit var detail: ImageView
    private lateinit var character: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_result,container,false)
        title = view!!.findViewById<ImageView>(R.id.result_title)
        detail = view!!.findViewById<ImageView>(R.id.result_explain)
        character = view!!.findViewById<ImageView>(R.id.result_character)

//        showResult(3)

        var task: ResultFragment.GetData = GetData()
        task.execute("http://" + IP_ADDRESS, "")

        return view
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    inner private class GetData() : AsyncTask<String?, Void, String?>() {
        //lateinit var progressDialog: ProgressDialog
        var errorMessage: String = "Failed!"

        constructor(parcel: Parcel) : this() {
            errorMessage = parcel.readString().toString()
        }

        protected override fun onPreExecute(){
            super.onPreExecute()
        }

        protected override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            if(result == null){

            }
            else{
                if(result!=""){
                    mJsonInt = result
                    showResult(mJsonInt.toInt())
                }
            }
        }

        @RequiresApi(Build.VERSION_CODES.KITKAT)
        override fun doInBackground(vararg p0: String?): String? {
            val jScore = JSONArray(arguments!!.getIntArray("scores"))
            val score = JSONObject()
            score.put("num", jScore)
            var serverURL: String? = p0[0]

            try{
                val url: URL = URL(serverURL)
                val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

                httpURLConnection.readTimeout = 5000
                httpURLConnection.connectTimeout = 5000
                httpURLConnection.requestMethod = "POST"
                httpURLConnection.doInput = true
                httpURLConnection.setRequestProperty("Content-Type","application/json")
                httpURLConnection.connect()

                val outputStream = DataOutputStream(httpURLConnection.outputStream)
                outputStream.writeBytes(score.toString())
                outputStream.flush()
                outputStream.close()

                var responseStatusCode: Int = httpURLConnection.responseCode
                Log.d("Test", "response code - " + responseStatusCode)

                val inputStream: InputStream
                if(responseStatusCode == HttpURLConnection.HTTP_OK){
                    inputStream = httpURLConnection.inputStream
                }
                else{
                    inputStream = httpURLConnection.errorStream
                }

                val inputStreamReader: InputStreamReader = InputStreamReader(inputStream,"UTF-8")
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)

                val sb = StringBuilder()
                var line: String? = null

                line = bufferedReader.readLine()
                while(line != null){
                    sb.append(line)
                    line = bufferedReader.readLine()
                }

                bufferedReader.close()

                return sb.toString().trim()
            } catch(e: Exception){
                Log.d("Test","GetData : Error ",e)
                errorMessage = e.toString()
                return null
            }
        }

        fun showResult(result: Int){
            if(result == 1){
                title.setImageResource(R.drawable.res_title1)
                detail.setImageResource(R.drawable.res1)
                character.setImageResource(R.drawable.char1)
            }
            else if(result == 2){
                title.setImageResource(R.drawable.res_title2)
                detail.setImageResource(R.drawable.res2)
                character.setImageResource(R.drawable.char2)
            }
            else if(result == 3){
                title.setImageResource(R.drawable.res_title3)
                detail.setImageResource(R.drawable.res3)
                character.setImageResource(R.drawable.char3)
            }
            else if(result == 4){
                title.setImageResource(R.drawable.res_title4)
                detail.setImageResource(R.drawable.res4)
                character.setImageResource(R.drawable.char4)
            }
            else if(result == 5){
                title.setImageResource(R.drawable.res_title5)
                detail.setImageResource(R.drawable.res5)
                character.setImageResource(R.drawable.char5)
            }
            else if(result == 6){
                title.setImageResource(R.drawable.res_title6)
                detail.setImageResource(R.drawable.res6)
                character.setImageResource(R.drawable.char6)
            }
            else if(result == 7){
                title.setImageResource(R.drawable.res_title7)
                detail.setImageResource(R.drawable.res7)
                character.setImageResource(R.drawable.char7)
            }
            else{
                title.setImageResource(R.drawable.res_title8)
                detail.setImageResource(R.drawable.res8)
                character.setImageResource(R.drawable.char8)
            }
        }

    }

}