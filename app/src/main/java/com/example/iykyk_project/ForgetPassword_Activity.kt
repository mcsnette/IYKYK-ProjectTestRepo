package com.example.iykyk_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class ForgetPassword_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        val backImg: ImageView = findViewById(R.id.backImg)

        backImg.setOnClickListener {
            val intent = Intent(this@ForgetPassword_Activity, MainActivity::class.java)

            startActivity(intent)
        }
    }
}