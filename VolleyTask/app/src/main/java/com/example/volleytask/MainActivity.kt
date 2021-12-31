package com.example.volleytask

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.Volley.*
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request

import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    var hasnet = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context =this
        var txt = findViewById<TextView>(R.id.txt1)

        //volley String get
        val queue = newRequestQueue(this)

        btn2.setOnClickListener{
            val url = "https://run.mocky.io/v3/b8402fc5-ae31-4d98-bced-b5b3fede6d06"
            val string =StringRequest(
                com.android.volley.Request.Method.GET,url,{
                        response ->
                    txt1.text =response
                },
                Response.ErrorListener {
                        error ->
                    txt1.text= error.message
                })
            queue.add(string)
        }

    }
    internal inner class Async : AsyncTask<Void,Void,String>() {
        lateinit var progres: ProgressDialog
        override fun onPreExecute() {
            super.onPreExecute()
            progres = ProgressDialog(context)
            progres.setMessage("Downlaoding")
            progres.setCancelable(false)
            progres.show()
        }

        override fun doInBackground(vararg params: Void?): String {
            if(isNetworkAvailable()){
                hasnet = true
                val client = OkHttpClient()
                val url = "https://run.mocky.io/v3/2cf15d61-982f-4843-9607-7947e1904668"
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()
                return response.body()?.string().toString()


            }
            else{
                return ""
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            progres.dismiss()
            if (hasnet){
                txt1.text = result

            }
            else{
                txt1.text="No Internet"
            }
        }
    }
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }




}