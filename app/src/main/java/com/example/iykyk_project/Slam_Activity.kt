package com.example.iykyk_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Slam_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slam)

        val backImg: ImageView = findViewById(R.id.backImg)

        backImg.setOnClickListener {
            val intent = Intent(this@Slam_Activity, Homepage_Activity::class.java)

            startActivity(intent)
        }
    }
}