package com.example.iykyk_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class User_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val addImg: ImageView = findViewById(R.id.navCreate)

        addImg.setOnClickListener {
            val intent = Intent(this@User_Activity, Slam_Activity::class.java)

            startActivity(intent)
        }


        val gotoUser: ImageView = findViewById(R.id.navUser)

        gotoUser.setOnClickListener {
            val intent = Intent(this@User_Activity, User_Activity::class.java)

            startActivity(intent)
        }

        val gotoHome: ImageView = findViewById(R.id.navHome)

        gotoHome.setOnClickListener {
            val intent = Intent(this@User_Activity, Homepage_Activity::class.java)

            startActivity(intent)
        }

        val LogoutBtn = findViewById<Button>(R.id.logoutBtn)

        LogoutBtn.setOnClickListener {
            val intent = Intent(this@User_Activity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}