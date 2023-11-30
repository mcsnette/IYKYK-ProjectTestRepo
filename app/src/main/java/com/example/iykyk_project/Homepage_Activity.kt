package com.example.iykyk_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Homepage_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val addImg: ImageView = findViewById(R.id.addSlam)

        addImg.setOnClickListener {
            val intent = Intent(this@Homepage_Activity, Slam_Activity::class.java)

            startActivity(intent)
        }
    }
}