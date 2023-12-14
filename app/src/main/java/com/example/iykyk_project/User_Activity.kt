package com.example.iykyk_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
//import com.example.iykyk_project.databinding.ActivityRegisterBinding
import com.example.iykyk_project.databinding.ActivityUserBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class User_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        loadUserDetails()



        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    val intent = Intent(this@User_Activity, Homepage_Activity::class.java)
                    startActivity(intent)
                }
                R.id.create -> {
                    val intent = Intent(this@User_Activity, Slam_Activity::class.java)
                    startActivity(intent)
                }
                R.id.user -> {
                    if (this@User_Activity is User_Activity) {
                        // If the current activity is User_Activity, do nothing
                        false
                    } else {
                        val intent = Intent(this@User_Activity, User_Activity::class.java)
                        startActivity(intent)
                        true
                    }
                }

            }
            true
        }
    }

    private fun loadUserDetails() {

    }
}