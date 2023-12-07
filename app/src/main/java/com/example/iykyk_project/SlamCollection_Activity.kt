package com.example.iykyk_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SlamCollection_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slam_collection)

        val addImg: ImageView = findViewById(R.id.navCreate)

        addImg.setOnClickListener {
            val intent = Intent(this@SlamCollection_Activity, Slam_Activity::class.java)

            startActivity(intent)
        }


        val gotoUser: ImageView = findViewById(R.id.navUser)

        gotoUser.setOnClickListener {
            val intent = Intent(this@SlamCollection_Activity, User_Activity::class.java)

            startActivity(intent)
        }

        val gotoHome: ImageView = findViewById(R.id.navHome)

        gotoHome.setOnClickListener {
            val intent = Intent(this@SlamCollection_Activity, Homepage_Activity::class.java)

            startActivity(intent)
        }

    }
}