package com.example.project_matcher

import android.os.Bundle
import android.view.Menu
import android.widget.Button
import com.example.project_matcher.base.MainContract
import com.example.project_matcher.databinding.ActivityMainBinding
import com.example.rocketreserver.RepoListQuery

class MainActivity : FirebaseAuthProvider(), MainContract.View {
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

       setPresenter(MainPresenter(this, applicationContext))
        presenter.onViewCreated()

        findViewById<Button>(R.id.btnData).setOnClickListener { presenter.onUserSearch("hi") }

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

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }
//    override fun setPresenter(presenter: MainPresenter) {
//        setPresenter1(presenter)
//    }

    override fun displayRepos(repoList: RepoListQuery.Repositories) {

    }
}
