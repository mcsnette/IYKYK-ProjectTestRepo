package com.example.iykyk_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SlamCollection_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slam_collection)

        val gotoHome: ImageView = findViewById(R.id.backImg)

        gotoHome.setOnClickListener {
            val intent = Intent(this@SlamCollection_Activity, Homepage_Activity::class.java)

            startActivity(intent)
        }
    }
}