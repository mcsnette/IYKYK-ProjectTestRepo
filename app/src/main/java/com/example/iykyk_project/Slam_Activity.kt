package com.example.iykyk_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.iykyk_project.databinding.ActivitySlamBinding
import com.google.android.play.core.integrity.e
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class Slam_Activity : AppCompatActivity() {

    private lateinit var binding: ActivitySlamBinding
    private lateinit var AnswersDatabaseReference: DatabaseReference
    private lateinit var UsersDatabaseReference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySlamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)

        binding.saveImg.setOnClickListener {
            val nickname = binding.getQ1.text.toString()
            val textQ2 = binding.getQ2.text.toString()
            val textQ3 = binding.getQ3.text.toString()
            val textQ4 = binding.getQ4.text.toString()
            val textQ5 = binding.getQ5.text.toString()
            val textQ6 = binding.getQ6.text.toString()
            val textQ7 = binding.getQ7.text.toString()
            val textQ8 = binding.getQ8.text.toString()
            val textQ9 = binding.getQ9.text.toString()

            if (nickname.isEmpty() || textQ2.isEmpty() || textQ3.isEmpty() ||
                textQ4.isEmpty() || textQ5.isEmpty() || textQ6.isEmpty() ||
                textQ7.isEmpty() || textQ8.isEmpty() || textQ9.isEmpty()
            ) {

                Toast.makeText(this@Slam_Activity, "Please fill in all fields", Toast.LENGTH_SHORT)
                    .show()
            } else {

                firebaseAuth = FirebaseAuth.getInstance()
                AnswersDatabaseReference = FirebaseDatabase.getInstance().reference
                val currentUser = firebaseAuth.currentUser
                if (currentUser != null) {

                    val uid = currentUser.uid
                    Log.d("Firebase", "UID: ${uid}")
                    val answerReference = AnswersDatabaseReference.child("Answers").push()
                    answerReference.child("UID").setValue(uid)
                    answerReference.child("nickname").setValue(nickname)
                    answerReference.child("question2").setValue(textQ2)
                    answerReference.child("question3").setValue(textQ3)
                    answerReference.child("question4").setValue(textQ4)
                    answerReference.child("question5").setValue(textQ5)
                    answerReference.child("question6").setValue(textQ6)
                    answerReference.child("question7").setValue(textQ7)
                    answerReference.child("question8").setValue(textQ8)
                    answerReference.child("question9").setValue(textQ9)



                    answerReference.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(answerssnapshot: DataSnapshot) {
                            val userNickname =
                                answerssnapshot.child("nickname").getValue(String::class.java)
                            val q2 = answerssnapshot.child("question2").getValue(String::class.java)
                            val q3 = answerssnapshot.child("question3").getValue(String::class.java)
                            val q4 = answerssnapshot.child("question4").getValue(String::class.java)
                            val q5 = answerssnapshot.child("question5").getValue(String::class.java)
                            val q6 = answerssnapshot.child("question6").getValue(String::class.java)
                            val q7 = answerssnapshot.child("question7").getValue(String::class.java)
                            val q8 = answerssnapshot.child("question8").getValue(String::class.java)
                            val q9 = answerssnapshot.child("question9").getValue(String::class.java)
                            if (uid != null) {
                                showConfirmationDialog(uid)
                            }
                            Log.d("Firebase", "Nickname: ${userNickname.toString()}")
                            Log.d("Firebase", "Question2: ${q2.toString()}")
                            Log.d("Firebase", "Question3: ${q3.toString()}")
                            Log.d("Firebase", "Question4: ${q4.toString()}")
                            Log.d("Firebase", "Question5: ${q5.toString()}")
                            Log.d("Firebase", "Question6: ${q6.toString()}")
                            Log.d("Firebase", "Question7: ${q7.toString()}")
                            Log.d("Firebase", "Question8: ${q8.toString()}")
                            Log.d("Firebase", "Question9: ${q9.toString()}")

                        }

                        override fun onCancelled(error: DatabaseError) {
                            Log.d("Firebase", "Answer data does not exist")
                        }
                    })
                } else {
                    Log.d("Firebase", "no user signed in")
                }
            }



        }

        val backImg: ImageView = findViewById(R.id.backImg)

        backImg.setOnClickListener {
            showCancelledDialog()

        }


    }

    private fun showCancelledDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Cancel Slam?")
        builder.setMessage("Do you want to cancel your slam? Your work will not be saved :(")

        builder.setPositiveButton("OK") {
            dialog, which ->
            val intent = Intent(this@Slam_Activity, Homepage_Activity::class.java)
            startActivity(intent)
            finish()
        }

        builder.setNegativeButton("Cancel"){
            dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()

    }

    private fun showConfirmationDialog(uid: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Slam Created")
        builder.setMessage("Your slam has been successfully created, Do you want to view it now?")

        builder.setPositiveButton("OK") {
                dialog, which ->
            val slamUserData = SlamBookData()
            val intent = Intent(this@Slam_Activity, ViewSlam_Activity::class.java)
            intent.putExtra("uid", slamUserData.UID)
            finish()
        }

        builder.setNegativeButton("Dismiss"){
                dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()

    }
}


