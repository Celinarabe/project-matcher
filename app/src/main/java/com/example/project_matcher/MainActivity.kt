package com.example.project_matcher

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import com.example.project_matcher.base.MainContract
import com.example.project_matcher.databinding.ActivityMainBinding
import com.example.rocketreserver.RepoListQuery

class MainActivity : FirebaseAuthProvider(), MainContract.View {
    lateinit var binding: ActivityMainBinding
    lateinit var optionsMenu : Menu
    var repoList:RepoListQuery.Repositories? = null

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

        findViewById<Button>(R.id.btnData).setOnClickListener {
            repoList = presenter.onUserSearch("hi") }
            Log.d("LaunchList", "yooooo$repoList")
            var bundle : Bundle = Bundle()
            var myMessage = "hi from celina"
            bundle.putString("repo_list", myMessage)

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

    override fun displayRepos(repoList: RepoListQuery.Repositories) {

    }
}
