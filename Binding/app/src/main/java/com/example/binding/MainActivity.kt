package com.example.binding

import android.os.Bundle
import android.view.View.inflate
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = MainActivity.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)
        binding.btn1.setOnClickListener {
            Toast.makeText(this,"view binding",Toast.LENGTH_SHORT).show()
        }

    }
}