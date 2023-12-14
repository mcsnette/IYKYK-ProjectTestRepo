package com.example.iykyk_project

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.VideoView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class Homepage_Activity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val videoView = findViewById<VideoView>(R.id.videoView)
        val path = "android.resource://" + packageName + "/" + R.raw.vid1
        videoView.setVideoURI(Uri.parse(path))
        videoView.start()

        val slamCollect: ImageView = findViewById(R.id.slamCollect)

        slamCollect.setOnClickListener {
            val intent = Intent(this@Homepage_Activity, SlamCollection_Activity::class.java)

            startActivity(intent)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    if (this@Homepage_Activity is Homepage_Activity) {
                        // If the current activity is User_Activity, do nothing
                        false
                    } else {
                        val intent = Intent(this@Homepage_Activity, Homepage_Activity::class.java)
                        startActivity(intent)
                        true
                    }
                }

                R.id.create -> {
                    val intent = Intent(this@Homepage_Activity, Slam_Activity::class.java)
                    startActivity(intent)
                }
                R.id.user -> {
                    val intent = Intent(this@Homepage_Activity, User_Activity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }



}