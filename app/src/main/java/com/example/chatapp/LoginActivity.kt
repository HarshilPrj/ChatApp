package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var edtEmail:EditText
    private lateinit var edtPass:EditText
    private lateinit var btnLogin:Button
    private lateinit var btnSignup:Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edtemail)
        edtPass = findViewById(R.id.edtpass)
        btnLogin = findViewById(R.id.btnlogin)
        btnSignup = findViewById(R.id.btnsignup)

        btnSignup.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPass.text.toString()

            if(edtEmail.text.toString().trim().isNotEmpty() && edtPass.text.toString().trim().isNotEmpty()){
                login(email,password);
            }else{
                Toast.makeText(this,"Enter Email & Password",Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun login(email:String,password:String){
        //login for logging user

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for logging in user
                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity,"User Dose Not Exist",Toast.LENGTH_LONG).show()
                }
            }
    }
}