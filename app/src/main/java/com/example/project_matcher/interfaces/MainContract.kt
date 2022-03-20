package com.example.project_matcher.interfaces

import com.example.project_matcher.models.RepoDetail

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
