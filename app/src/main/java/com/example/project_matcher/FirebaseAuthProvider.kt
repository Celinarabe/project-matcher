package com.example.project_matcher

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

open class FirebaseAuthProvider : AppCompatActivity() {
    protected lateinit var auth: FirebaseAuth
    val scopes = listOf("public_repo")
    val provider = OAuthProvider.newBuilder("github.com")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase Auth Service
        auth = Firebase.auth
        provider.scopes = scopes
    }

    fun <T : AppCompatActivity> redirect(from: Context, to: Class<T>) {
        val intent = Intent(from, to)
        this.startActivity(intent)
    }
}
