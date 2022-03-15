package com.example.project_matcher

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import com.example.project_matcher.base.MainContract
import com.example.project_matcher.databinding.ActivityMainBinding
import com.example.rocketreserver.RepoListQuery

class MainActivity : FirebaseAuthProvider() {
    lateinit var binding: ActivityMainBinding
    lateinit var optionsMenu : Menu

    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(auth.currentUser == null){
            redirect(this, AuthActivity::class.java)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        optionsMenu = menu
//        optionsMenu.findItem(R.id.search).expandActionView()
        return true
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}
