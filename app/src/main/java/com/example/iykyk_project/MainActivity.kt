package com.example.iykyk_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val RegisterBtn = findViewById<Button>(R.id.RegBtn)

        RegisterBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, Register_Activity::class.java)
            startActivity(intent)
        }

        val forgotPwTxt: TextView = findViewById(R.id.ForgotPwTxt)

        forgotPwTxt.setOnClickListener {
            val intent = Intent(this@MainActivity, ForgetPassword_Activity::class.java)
            startActivity(intent)
        }

        val LoginBtn = findViewById<Button>(R.id.loginBtn)

        LoginBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, Homepage_Activity::class.java)
            startActivity(intent)
        }
    }
}