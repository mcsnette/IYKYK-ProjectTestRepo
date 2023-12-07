package com.example.iykyk_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
//import com.example.iykyk_project.databinding.ActivityRegisterBinding
import com.example.iykyk_project.databinding.ActivityUserBinding
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



        val LogoutBtn = findViewById<Button>(R.id.logoutBtn)
        LogoutBtn.setOnClickListener {
            val intent = Intent(this@User_Activity, MainActivity::class.java)
            startActivity(intent)
        }

        //navbar redirection
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
    }

    private fun loadUserDetails() {

    }
}