package com.example.project_matcher

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.project_matcher.databinding.ActivityMainBinding
import com.google.firebase.ktx.Firebase

class MainActivity : FirebaseAuthProvider() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // redirect to Auth Activity if user isn't authenticated - is there a more elegant way to redirect?
        if(auth.currentUser == null){
            redirect(this, AuthActivity::class.java)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }
}
