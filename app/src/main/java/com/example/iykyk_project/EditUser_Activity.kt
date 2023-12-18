package com.example.iykyk_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iykyk_project.databinding.ActivityEditUserBinding
import com.example.iykyk_project.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditUser_Activity : AppCompatActivity() {

    //binding for the contentView
    private lateinit var binding:ActivityEditUserBinding

    //Firebase Authentication
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var DbRef: DatabaseReference
    private lateinit var Userid : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //db instantiations
        firebaseAuth = FirebaseAuth.getInstance()
        Userid = firebaseAuth.currentUser?.uid.toString()
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        //buttons
        binding.backImg.setOnClickListener(){
            val intent = Intent(this, User_Activity::class.java)
            startActivity(intent)
            finish()
        }

        binding.saveImg.setOnClickListener(){
            val UpdateName = binding.GetName.text.toString()
            val UpdateUsername = binding.GetUsername.text.toString()

            if(UpdateUsername.isEmpty() && UpdateName.isEmpty() ){
                Toast.makeText(this@EditUser_Activity, "At least update one field -_- ", Toast.LENGTH_SHORT).show()
            }

            else{
                UpdateUser()
                val intent = Intent(this, User_Activity::class.java)
                startActivity(intent)
                finish()
            }


        }


    }
    private fun UpdateUser(){
        //variables here from text fields
        val UpdateName = binding.GetName.text.toString()
        val UpdateUsername = binding.GetUsername.text.toString()

        //id of current user
        val userRef = databaseReference.child(Userid)

        if(UpdateUsername.isEmpty()){
            var NameUpdate = mapOf<String,String>("fullname" to  UpdateName)
            userRef.updateChildren(NameUpdate).addOnSuccessListener {
                Toast.makeText(this@EditUser_Activity, "yo name is updated boi", Toast.LENGTH_SHORT).show()
            }

        }
        else if(UpdateName.isEmpty()){
            var UsernameUpdate = mapOf<String,String>("username" to  UpdateUsername)
            userRef.updateChildren(UsernameUpdate).addOnSuccessListener {
                Toast.makeText(this@EditUser_Activity, "yo username is updated boi", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            var BothUpdate = mapOf<String,String>("fullname" to  UpdateName, "username" to  UpdateUsername)
            userRef.updateChildren(BothUpdate).addOnSuccessListener {
                Toast.makeText(this@EditUser_Activity, "Both things are updated boi", Toast.LENGTH_SHORT).show()
            }
        }



    }

}