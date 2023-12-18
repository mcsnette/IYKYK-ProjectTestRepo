package com.example.iykyk_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.iykyk_project.databinding.ActivityViewSlamBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ViewSlam_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityViewSlamBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var answersDatabaseReference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewSlamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnBack: ImageView = findViewById(R.id.backImg)
        val btnEdit: ImageView = findViewById(R.id.editImg)
        val btnDelete: ImageView = findViewById(R.id.deleteImg)

        val intent = intent.getStringExtra("key")
        Log.d("Firebase", "Intent UID Passed in ViewSlam: $intent")
        if (intent != null) {
            readData(intent)
        }
        btnBack.setOnClickListener {
            val intent = Intent(this@ViewSlam_Activity, Homepage_Activity::class.java)
            startActivity(intent)
            finish()
        }

        btnDelete.setOnClickListener {
            if (intent != null) {
                showDeleteConfirmationDialog(intent)
            }
        }

        btnEdit.setOnClickListener {
            // Implement edit functionality here
            // You can navigate to the edit screen with the selected slam data
        }
    }

    private fun readData(userID: String){
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser
        val childID = currentUser?.uid
        Log.d("Firebase","parent id: $childID")
        Log.d("Firebase","child id: $userID")
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDatabase.getReference("Answers").child(userID).addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("Firebase","snapshot: ${snapshot}")
                if(snapshot.exists()){
                        Log.d("Firebase","datasnapshot: ${snapshot}")
                        val nickname = snapshot.child("nickname").getValue(String::class.java)
                        val q2 = snapshot.child("question2").getValue(String::class.java)
                        val q3 = snapshot.child("question3").getValue(String::class.java)
                        val q4 = snapshot.child("question4").getValue(String::class.java)
                        val q5 = snapshot.child("question5").getValue(String::class.java)
                        val q6 = snapshot.child("question6").getValue(String::class.java)
                        val q7 = snapshot.child("question7").getValue(String::class.java)
                        val q8 = snapshot.child("question8").getValue(String::class.java)
                        val q9 = snapshot.child("question9").getValue(String::class.java)
                        Log.e("Firebase","Results Found: $nickname")
                        binding.GetAnswer1.setText(nickname)
                        binding.getQ2.setText(q2)
                        binding.getQ3.setText(q3)
                        binding.getQ4.setText(q4)
                        binding.getQ5.setText(q5)
                        binding.getQ6.setText(q6)
                        binding.getQ7.setText(q7)
                        binding.getQ8.setText(q8)
                        binding.getQ9.setText(q9)
                } else{
                    Log.d("Firebase","Doesn't Exist")
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun showDeleteConfirmationDialog(userID: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Slam")
        builder.setMessage("Are you sure you want to delete this slam?")

        builder.setPositiveButton("Yes") { dialog, which ->
            deleteSlam(userID)
        }

        builder.setNegativeButton("No") { dialog, which ->
            // Do nothing, cancel deletion
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun deleteSlam(userID: String) {
        firebaseDatabase.getReference("Answers").child(firebaseAuth.currentUser?.uid!!)
            .child(userID)
            .removeValue()
            .addOnSuccessListener {
                Toast.makeText(this, "Slam deleted successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@ViewSlam_Activity, Homepage_Activity::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Unable to delete the slam", Toast.LENGTH_SHORT).show()
            }
    }
}


//
//    private fun updateSlam(
//        uid: String,
//        nickname: String,
//        q2: String,
//        q3: String,
//        q4: String,
//        q5: String,
//        q6: String,
//        q7: String,
//        q8: String,
//        q9: String
//    ) {
//        firebaseDatabase = FirebaseDatabase.getInstance()
//        val slamUser = mapOf(
//            "nickname" to nickname,
//            "question2" to q2,
//            "question3" to q3,
//            "question4" to q4,
//            "question5" to q5,
//            "question6" to q6,
//            "question7" to q7,
//            "question8" to q8,
//            "question9" to q9
//        )
//
//        firebaseDatabase.getReference("Answers").child(uid)
//            .setValue(slamUser)
//            .addOnSuccessListener {
//                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
//            }
//            .addOnFailureListener {
//                Toast.makeText(this, "Unable to Update", Toast.LENGTH_SHORT).show()
//            }
//    }
//}
