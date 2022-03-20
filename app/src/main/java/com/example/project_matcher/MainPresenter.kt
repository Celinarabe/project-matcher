package com.example.project_matcher

import android.content.Context
import com.example.project_matcher.base.MainContract
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainPresenter(private var view: MainContract.View?, private val context: Context ): MainContract.Presenter, CoroutineScope
     {
        private val job = Job() // keeps track of the state of a coroutine/cancel it
        override val coroutineContext:CoroutineContext
            get() = Dispatchers.Main + job //dispatcher decides which thread the coroutine will run on

        private lateinit var repository: RepoRepository

    override fun onViewCreated() {
        repository = RepoRepository(context)
    }

    override fun onUserSearch(query: String) {

        launch {
//            //deferred is an interface that extends Job and will wait for the result from the coroutine
//            //async allows us to obtain a value returned by the coroutine block
//            val deferred: Deferred<RepoListQuery.Repositories?> = async {
//                return@async repository.getRepos(query)
//            }
//            val repoList : RepoListQuery.Repositories? = deferred.await()
            view?.displayRepos(repository.getRepos(query))
        }
    }


    override fun onDestroy() {
        this.view = null
    }
}