package com.example.project_matcher

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.internal.Constants
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthCredential
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AuthActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        // Initialize Firebase Auth
        auth = Firebase.auth
    }

    public override fun onStart() {
        super.onStart()

        findViewById<Button>(R.id.btnLogin).setOnClickListener { logIn() }
    }

    private fun logIn() {
        val scopes = listOf("public_repo")
        val provider = OAuthProvider.newBuilder("github.com")
        provider.scopes = scopes
        auth
            .startActivityForSignInWithProvider( /* activity= */this, provider.build())
            .addOnSuccessListener {
                val credential = it.credential as OAuthCredential;
                val token = credential.accessToken
                saveToken(token)
                redirectToMain()
            }
            .addOnFailureListener(
                OnFailureListener {
                    // Handle failure.
                }
            )
        }

    private fun saveToken(token: String?) {
        val sharedPref = getSharedPreferences(
           "shared_prefs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply {
            putString("user_token", token)
        }.apply()
    }

    private fun redirectToMain() {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }
}