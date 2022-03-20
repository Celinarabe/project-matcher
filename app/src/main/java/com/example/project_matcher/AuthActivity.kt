package com.example.project_matcher

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.OAuthCredential


class AuthActivity : FirebaseAuthProvider() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        findViewById<Button>(R.id.btnLogin).setOnClickListener { logIn() }
    }


    private fun logIn() {
        auth
            .startActivityForSignInWithProvider(this, provider.build())
            .addOnSuccessListener {
                redirect(this, MainActivity::class.java)
                val credential = it.credential as OAuthCredential;
                val token = credential.accessToken
                saveToken(token)

            }
            .addOnFailureListener {
                findViewById<Button>(R.id.btnLogin).let {
                    Snackbar.make(it, R.string.login_error, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
    }

    // Save user token using Encrypted Shared Preferences
    fun saveToken(token: String?) {
        val masterKeyAlias: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val sharedPreferences = EncryptedSharedPreferences.create(
            "user_token",
            masterKeyAlias,
            this,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("user_token", token)
        }.apply()
    }
}
