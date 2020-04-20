package com.example.authentication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.authentication.R
import com.example.authentication.api.RetrofitClient
import com.example.authentication.models.DefaultResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import simplifiedcoding.net.kotlinretrofittutorial.storage.SharedPrefManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewLogin.setOnClickListener{
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }

        buttonSignUp.setOnClickListener {

            val email = editTextEmail.text.toString().trim()
            val name = editTextName.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val confirmPassword = editConfirmPassword.text.toString().trim()
            val username = editTextUsername.text.toString().trim()

            if (email.isEmpty()) {
                editTextEmail.error = "Email required"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                editTextPassword.error = "Password required"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }

            if (name.isEmpty()) {
                editTextName.error = "Name required"
                editTextName.requestFocus()
                return@setOnClickListener
            }

            if (username.isEmpty()) {
                editTextUsername.error = "Username required"
                editTextUsername.requestFocus()
                return@setOnClickListener
            }

            if (confirmPassword.isEmpty()) {
                editConfirmPassword.error = "confirm the password"
                editConfirmPassword.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.createUser(email, name, password, username, confirmPassword)
                .enqueue(object: Callback<DefaultResponse> {
                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                        Toast.makeText(applicationContext, response.body()?.name, Toast.LENGTH_LONG).show()
                    }

                })
        }
    }
    override fun onStart() {
        super.onStart()

        if(SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, ProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }
}
