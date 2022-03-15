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


class AuthActivity : FirebaseAuthProvider() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        findViewById<Button>(R.id.btnLogin).setOnClickListener { logIn() }
    }

    public override fun onStart() {
        super.onStart()
    }

    private fun logIn() {
        auth
            .startActivityForSignInWithProvider(this, provider.build())
            .addOnSuccessListener {
                val credential = it.credential as OAuthCredential;
                val token = credential.accessToken
                saveToken(token)
                redirect(this, MainActivity::class.java)
            }
            .addOnFailureListener(
                OnFailureListener {
                    // Handle failure.
                }
            )
    }

    fun saveToken(token: String?) {
        val sharedPref = getSharedPreferences(
            "shared_prefs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply {
            putString("user_token", token)
        }.apply()
    }
}
