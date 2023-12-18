package com.example.iykyk_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class SlamCollection_Activity : AppCompatActivity() {

    private lateinit var UsersDatabaseReference: DatabaseReference
    private lateinit var AnswersDatabaseReference: DatabaseReference
    private lateinit var usernamerecyclerView: RecyclerView
    private lateinit var slamDataList: ArrayList<SlamBookData>
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slam_collection)
        FirebaseApp.initializeApp(this)

        val gotoHome: ImageView = findViewById(R.id.backImg)

        gotoHome.setOnClickListener {
            val intent = Intent(this@SlamCollection_Activity, Homepage_Activity::class.java)
            startActivity(intent)
        }

        // Initialize slamDataList and RecyclerView
        usernamerecyclerView = findViewById(R.id.slamCollection)
        usernamerecyclerView.layoutManager = LinearLayoutManager(this)
        usernamerecyclerView.setHasFixedSize(true)
        firebaseAuth = FirebaseAuth.getInstance()

        slamDataList = arrayListOf()
        initializeDatabaseReferences()
        listDatabase()
    }

    private fun initializeDatabaseReferences() {
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            val userUID = currentUser.uid
            AnswersDatabaseReference = FirebaseDatabase.getInstance().getReference("Answers")
            UsersDatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userUID)
        }
    }

    private fun listDatabase() {
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            initializeDatabaseReferences()
            AnswersDatabaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(userSnapshot: DataSnapshot) {
                    if (userSnapshot.exists()) {
                        slamDataList.clear()
                        val slamCollectionAdapter = SlamCollection_Adapter(slamDataList)
                        for (userSnapshot in userSnapshot.children) {
                            if (userSnapshot.child("UID").value.toString() == currentUser.uid) {
                                val UID = currentUser.uid
                                val userID = userSnapshot.child("UID").value.toString()
                                Log.d("Firebase", "Passing Key: ${UID}")
                                val userKey = userSnapshot.key // This is the unique ID
                                val nickname =
                                    userSnapshot.child("nickname").getValue(String::class.java)
                                val slamUserData = SlamBookData(UID = userID, q1 = nickname)
                                Log.d("Firebase", "User Key: $userKey")
                                slamDataList.add(slamUserData)
                            } else {
                                Log.e("Firebase", "Snapshot does not exist")
                            }
                            slamCollectionAdapter.setOnItemClickListener(object :
                                SlamCollection_Adapter.OnItemClickListener {
                                override fun onItemClick(slamBookData: SlamBookData) {
                                    Toast.makeText(
                                        this@SlamCollection_Activity,
                                        "Checking ${slamBookData.q1}'s Slambook",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val intent = Intent(
                                        this@SlamCollection_Activity,
                                        ViewSlam_Activity::class.java
                                    )
                                    val userKey = userSnapshot.key
                                    intent.putExtra(
                                        "key",
                                        userKey
                                    ) // Pass the userKey to the next activity
                                    startActivity(intent)
                                }

                            })
                            usernamerecyclerView.adapter = slamCollectionAdapter
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e("Firebase", "Error loading data from Firebase: ${databaseError.message}")
                }
            })
        }
    }


    private fun Any.putExtra(s: String, userKey: Any?) {

}
}


