package com.example.iykyk_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgetPassword_Activity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    //private lateinit var firebaseDatabase: FirebaseDatabase
    //private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        val backImg: ImageView = findViewById(R.id.backImg)
        backImg.setOnClickListener {
            val intent = Intent(this@ForgetPassword_Activity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val fpemail: EditText = findViewById(R.id.fpEmailTxt)
        val fpSendButton: Button = findViewById(R.id.sendBtn)

        firebaseAuth = FirebaseAuth.getInstance()

        fpSendButton.setOnClickListener(){
            val SendFPEmail = fpemail.text.toString()


            if (SendFPEmail.isNotEmpty()){
                firebaseAuth.sendPasswordResetEmail(SendFPEmail)
                    .addOnSuccessListener {
                        Toast.makeText(this, "We have sent a link to your email", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }

                    .addOnFailureListener{
                        Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                    }
            }

            else{
                Toast.makeText(this@ForgetPassword_Activity, "Fill up the damn email!! *nagdabog*",Toast.LENGTH_SHORT).show()
            }


        }


    }
}