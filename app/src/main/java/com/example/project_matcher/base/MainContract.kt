package com.example.project_matcher.base

import com.example.project_matcher.model.RepoDetail
import com.example.rocketreserver.RepoListQuery

interface MainContract {
    interface Presenter : BasePresenter {
        fun onViewCreated()

        /**
         * function to execute user search query
         * @param query - user's search query
         */
        fun onUserSearch(query: String)
    }

    interface View : BaseView<Presenter> {
        fun displayRepos(repoList: List<RepoDetail>?)
        fun handleBadResponse()
    }
}
