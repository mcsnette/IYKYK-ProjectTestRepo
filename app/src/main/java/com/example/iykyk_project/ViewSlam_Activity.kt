package com.example.iykyk_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.iykyk_project.databinding.ActivityViewSlamBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ViewSlam_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityViewSlamBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var usernamerecyclerView: RecyclerView
    private lateinit var answersDatabaseReference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewSlamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnBack: ImageView = findViewById(R.id.backImg)
        val btnDelete: ImageView = findViewById(R.id.deleteImg)

        val intent = intent.getStringExtra("uid")
        Log.d("Firebase", "Intent UID Passed in ViewSlam: $intent")
        if (intent != null) {
            readData(intent)
        }
        btnBack.setOnClickListener {
            val intent = Intent(this@ViewSlam_Activity, Homepage_Activity::class.java)
            startActivity(intent)
            finish()
        }

//        binding.deleteImg.setOnClickListener {
//            if (intent != null) {
//                val slamBookData = SlamBookData(UID = intent, q1 = "")  // Create SlamBookData with the UID
//                showDeleteConfirmationDialog(slamBookData)
//            }
//        }

        binding.deleteImg.setOnClickListener {
            val nickname = binding.GetAnswer1.text.toString()
            if (nickname.isNotEmpty()) {
                if (intent != null) {
                    deleteData(intent)
                }
            } else {
                Toast.makeText(this, "Can't find it", Toast.LENGTH_SHORT).show()
            }

        }





    }

    private fun readData(userID: String) {
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser
        val childID = currentUser?.uid
        Log.d("Firebase", "parent id: $childID")
        Log.d("Firebase", "child id: $userID")
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDatabase.getReference("Answers").child(userID)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("Firebase", "snapshot: ${snapshot}")
                    if (snapshot.exists()) {
                        Log.d("Firebase", "datasnapshot: ${snapshot}")
                        val nickname = snapshot.child("nickname").getValue(String::class.java)
                        val q2 = snapshot.child("question2").getValue(String::class.java)
                        val q3 = snapshot.child("question3").getValue(String::class.java)
                        val q4 = snapshot.child("question4").getValue(String::class.java)
                        val q5 = snapshot.child("question5").getValue(String::class.java)
                        val q6 = snapshot.child("question6").getValue(String::class.java)
                        val q7 = snapshot.child("question7").getValue(String::class.java)
                        val q8 = snapshot.child("question8").getValue(String::class.java)
                        val q9 = snapshot.child("question9").getValue(String::class.java)
                        Log.e("Firebase", "Results Found: $nickname")
                        binding.GetAnswer1.setText(nickname)
                        binding.getQ2.setText(q2)
                        binding.getQ3.setText(q3)
                        binding.getQ4.setText(q4)
                        binding.getQ5.setText(q5)
                        binding.getQ6.setText(q6)
                        binding.getQ7.setText(q7)
                        binding.getQ8.setText(q8)
                        binding.getQ9.setText(q9)
                    } else {
                        Log.d("Firebase", "Doesn't Exist")
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }

    private fun deleteData(userID: String) {
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser
        val childID = currentUser?.uid
        Log.d("Firebase", "Deleting UID: $userID")
        firebaseDatabase.getReference("Answers").child(userID).removeValue()
            .addOnSuccessListener {
                binding.GetAnswer1.text.clear()
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Unable to delete: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.e("Firebase", "Delete Error: ${e.message}", e)
            }

    }

}