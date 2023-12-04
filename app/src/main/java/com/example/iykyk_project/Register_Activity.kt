package com.example.iykyk_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.iykyk_project.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Register_Activity : AppCompatActivity() {

    //binding for the contentView
    private lateinit var binding:ActivityRegisterBinding

    //Firebase Authentication
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //for database interaction
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("Users")

        //for authentication using firebase for register page| initializing
        firebaseAuth = FirebaseAuth.getInstance()


        binding.RegBtn.setOnClickListener(){
            //variables from Register Page
            val RegEmail = binding.RegEmailTxt.text.toString()
            var RegUsername= binding.RegUsernameTxt.text.toString()
            var RegPassword= binding.RegPwordTxt.text.toString()


            if (RegEmail.isNotEmpty() && RegPassword.isNotEmpty() && RegUsername.isNotEmpty()) {
              //registerUser(RegEmail,RegUsername,RegPassword)
                firebaseAuth.createUserWithEmailAndPassword(RegEmail, RegPassword).addOnCompleteListener{
                    if (it.isSuccessful){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

            }

            else{
                Toast.makeText(this@Register_Activity, "fill up the damn form! *nagdabog*",Toast.LENGTH_SHORT).show()
            }

        }

        //To go back to Login Page
        val backImg: ImageView = findViewById(R.id.backImg)
        backImg.setOnClickListener {
            val intent = Intent(this@Register_Activity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }





    }

    private fun registerUser(email: String, username: String, password: String) {
        databaseReference.orderByChild("username").equalTo(username)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (!dataSnapshot.exists()){
                        val id = databaseReference.push().key
                        val userData = UserData(id, email, username, password)
                        databaseReference.child(id!!).setValue(userData)
                        Toast.makeText(this@Register_Activity, "You Have successfully signed up!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@Register_Activity,MainActivity::class.java))
                        finish()
                    }

                    else{
                        Toast.makeText(this@Register_Activity, "Sorry, this Human has been living before you ", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@Register_Activity, "Database Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })

    }
}


