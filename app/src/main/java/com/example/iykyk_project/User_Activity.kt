package com.example.iykyk_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
//import com.example.iykyk_project.databinding.ActivityRegisterBinding
import com.example.iykyk_project.databinding.ActivityUserBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class User_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var Userid : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        Userid = firebaseAuth.currentUser?.uid.toString()
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        loadUserDetails()

        //button listeners
        binding.editprofileBtn.setOnClickListener(){
            val intent = Intent(this@User_Activity, EditUser_Activity::class.java)
            startActivity(intent)
            finish()
        }

        binding.logoutBtn.setOnClickListener(){
            firebaseAuth.signOut() // to make sure no UID will clash when logging in again
            val intent = Intent(this@User_Activity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // navigation bar on the bottom
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
        //db ref to fetch user info
        val userRef = databaseReference.child(Userid)

        userRef.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                Log.d("FirebaseData", "Snapshot: $snapshot")

                val email = snapshot.child("email").getValue(String::class.java).toString()
                val username = snapshot.child("username").value.toString()
                val name = snapshot.child("fullname").value.toString()

                Log.d("FirebaseData", "Email: $email, Username: $username")

                binding.getName.text = "Edit your name!"

                if (name.isNotEmpty()){
                    binding.getName.text = name
                }

                binding.passUsername.text = username

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Error: $error")
            }
        })
    }
}