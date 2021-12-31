package com.example.labterminal

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_services.*
import kotlinx.android.synthetic.main.layout_2.*

class MainActivity : AppCompatActivity() {

    var num :String = "03121234"
    var txt :String="hello"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Intent(this, MyServices::class.java).also {
            startService(it)
            Toast.makeText(this,"Services is running ",Toast.LENGTH_LONG).show()
        }


        reccyle.layoutManager=LinearLayoutManager(this)
        reccyle.adapter= CustomAdapter()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) !=
            PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS),
                111
            )
        }
        else{
            reciveMsg()
        }

        btn2.setOnClickListener{
            var sms = SmsManager.getDefault()
            sms.sendTextMessage(num,"ME",txt,null,null)
        }
        btnser.setOnClickListener{
            val intent = Intent(this,Services::class.java)
            startActivity(intent)
        }


    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 111&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            reciveMsg()
        }
    }

    private fun reciveMsg() {
        val br = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    for (sms in Telephony.Sms.Intents.getMessagesFromIntent(intent)){
                        Toast.makeText(applicationContext,sms.displayMessageBody,Toast.LENGTH_LONG).show()

                    }
                }
            }

        }
        registerReceiver(br, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))
    }
}