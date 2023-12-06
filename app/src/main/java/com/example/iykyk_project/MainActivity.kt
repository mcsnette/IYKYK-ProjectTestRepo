package com.example.iykyk_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.iykyk_project.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("Users")




        //Register button Listener
        val RegisterBtn = findViewById<Button>(R.id.RegBtn)
        RegisterBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, Register_Activity::class.java)
            startActivity(intent)
            finish()
        }

        //forgot button listener
        val forgotPwTxt: TextView = findViewById(R.id.ForgotPwTxt)
        forgotPwTxt.setOnClickListener {
            val intent = Intent(this@MainActivity, ForgetPassword_Activity::class.java)
            startActivity(intent)
        }

        binding.loginBtn.setOnClickListener(){
            //variables from Register Page
            val LoginUsername = binding.UsernameTxt.text.toString()
            val LoginPassword= binding.PasswordTxt.text.toString()


            if (LoginUsername.isNotEmpty() && LoginPassword.isNotEmpty()) {
                //LoginUser(LoginUsername,LoginPassword)
                firebaseAuth.signInWithEmailAndPassword(LoginUsername, LoginPassword).addOnCompleteListener{
                    if (it.isSuccessful){
                        val intent = Intent(this, Homepage_Activity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "HAHAHAHA MALI CREDENTIALS", Toast.LENGTH_SHORT).show()
                    }
            }



        }
            else {Toast.makeText(this@MainActivity, "fill up the damn text fields! nagdabog",Toast.LENGTH_SHORT).show()
            }
    }


    /*private fun LoginUser (Username: String, Password:String){
        databaseReference.orderByChild("Username").equalTo(Username).addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(datasnapshot: DataSnapshot) {
                if (datasnapshot.exists()){
                    for (userSnapshot in datasnapshot.children){
                        val userData = userSnapshot.getValue(UserData::class.java)

                        if(userData != null && userData.Password != null){
                            Toast.makeText(this@MainActivity,"Login Success! hooray!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@MainActivity, Homepage_Activity::class.java))
                            finish()
                            return
                        }
                    }
                }
                Toast.makeText(this@MainActivity,"Your Credentials are Incorrect", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Database Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }*/
    }
}