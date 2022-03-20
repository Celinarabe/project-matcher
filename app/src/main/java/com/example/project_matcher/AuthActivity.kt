package com.example.project_matcher

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.OAuthCredential


class AuthActivity : FirebaseAuthProvider() {
    private var token :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        findViewById<Button>(R.id.btnLogin).setOnClickListener { logIn() }
    }

    private fun logIn() {
        auth
            .startActivityForSignInWithProvider(this, provider.build())
            .addOnSuccessListener {
                val credential = it.credential as OAuthCredential;
                token = credential.accessToken
                saveToken(token)
                redirect(this, MainActivity::class.java)
            }
            .addOnFailureListener {
                findViewById<Button>(R.id.btnLogin).let {
                    Snackbar.make(it, R.string.login_error, Snackbar.LENGTH_SHORT)
                        .show()
                }
                token = null
            }
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
