package com.example.iykyk_project

//import com.example.iykyk_project.databinding.ActivityRegisterBinding
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.iykyk_project.databinding.ActivityUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.firestore.auth.User

class User_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var Userid : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)



        firebaseAuth = FirebaseAuth.getInstance()

        Userid = firebaseAuth.currentUser?.uid.toString()
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        Log.d("FirebaseData", "UID: $Userid")
        loadUserDetails()

        /*if(uid.isNotEmpty()){
            loadUserDetails()
        }*/

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

    /*private fun loadUserDetails() {

        databaseReference.child(uid)
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    user = snapshot.getValue(User::class.java)!!
                    binding.profileUID.setText(user.id)
                    binding.profileEmail.setText(user.email)
                    binding.profileUsername.text = username
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("FirebaseError", "Error: $error")
                }
            })
    }*/


    /*override fun onResume() {
        super.onResume()

        // Get the current user
        val currentUser: FirebaseUser? = firebaseAuth.currentUser

        currentUser?.uid?.let { userId ->
            databaseReference.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val username = dataSnapshot.child("username").value as String
                        val email = dataSnapshot.child("email").value as String
                        val id = dataSnapshot.child("uid").value as String

                        binding.profileUID.text = id
                        binding.profileEmail.text = email
                        binding.profileUsername.text = username


                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    //
                }
            })
        }
    }*/



    private fun loadUserDetails() {
        val currentUser = firebaseAuth.currentUser
        if (currentUser == null) {
            // Redirect to login activity or handle unauthenticated user
            return
        }
        //db ref to fetch user info
        val userRef = databaseReference.child(Userid)

        userRef.addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                        Log.d("FirebaseData", "Snapshot: $snapshot")

                        val email = snapshot.child("email").getValue(String::class.java).toString()
                        val username = snapshot.child("username").value.toString()
                        val uid = snapshot.child("id").value.toString()

                        Log.d("FirebaseData", "Email: $email, Username: $username, UID: $uid")

                        binding.profileUID.text = uid
                        binding.profileEmail.text = email
                        binding.profileUsername.text = username

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("FirebaseError", "Error: $error")
                }
        })
    }

    /*private fun loadUserDetails() {
        //db ref to fetch user info
        val dbRef = FirebaseDatabase.getInstance().getReference("Users")

        dbRef.child(firebaseAuth.uid!!)
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (usersnapshot in snapshot.children) {
                        //Log.d("FirebaseData", "Snapshot: $snapshot")

                        val email = usersnapshot.child("email").value.toString()
                        val username = usersnapshot.child("username").value.toString()
                        val uid = usersnapshot.child("id").value.toString()

                        //Log.d("FirebaseData", "Email: $email, Username: $username, UID: $uid")

                        binding.profileUID.text = uid
                        binding.profileEmail.text = email
                        binding.profileUsername.text = username
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("FirebaseError", "Error: $error")
                }
            })
    }*/
}