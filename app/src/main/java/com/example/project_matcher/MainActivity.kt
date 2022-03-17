package com.example.project_matcher

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.project_matcher.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : FirebaseAuthProvider() {
    lateinit var binding: ActivityMainBinding
    lateinit var optionsMenu : Menu

//    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true);
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        handleIntent(intent)

        Log.d("LaunchList", "YOYOYOYOYOY")

        if(auth.currentUser == null){
            redirect(this, AuthActivity::class.java)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_logout -> {
                handleLogout()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }




//    override fun onStart() {
//        super.onStart()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        binding = null
//    }
    private fun setQuery(query: String) {
    val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
    with(sharedPref.edit()) {
        putString(getString(R.string.user_query_key), query)
        apply()
    }
}
    fun handleLogout() {
        Firebase.auth.signOut()
        redirect(this, AuthActivity::class.java)
    }

}
