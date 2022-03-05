package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var edtName:EditText
    private lateinit var edtEmail:EditText
    private lateinit var edtPass:EditText
    private lateinit var btnSignup:Button
    private lateinit var mDbRef:DatabaseReference

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edtName = findViewById(R.id.edtname)
        edtEmail = findViewById(R.id.edtemail)
        edtPass = findViewById(R.id.edtpass)
        btnSignup = findViewById(R.id.btnsignup)

        btnSignup.setOnClickListener {
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPass.text.toString()

            if (edtPass.text.toString().length < 8){
                Toast.makeText(this,"password Must Be 8 Char..",Toast.LENGTH_LONG).show()
            }

            else if(edtName.text.toString().trim().isNotEmpty() && edtEmail.text.toString().trim().isNotEmpty() && edtPass.text.toString().trim().isNotEmpty()){
                signup(name,email,password)
            }else{
                Toast.makeText(this,"Enter Name, Email & Password",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun signup(name:String,email: String, password: String) {
        //logic of create user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for jump to home
                    addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                    val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@SignUpActivity,"Some Error",Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun addUserToDatabase(name: String, email: String, uid: String?) {
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid!!).setValue(User(name,email,uid))
    }
}