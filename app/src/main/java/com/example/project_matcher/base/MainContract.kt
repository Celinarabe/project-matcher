package com.example.project_matcher.base

import com.example.rocketreserver.RepoListQuery

interface MainContract {
    interface Presenter : BasePresenter {
        fun onViewCreated()
        fun onUserSearch(query: String)
    }

    interface View : BaseView<Presenter> {
        fun displayRepos(repoList: RepoListQuery.Repositories?)
    }
}
