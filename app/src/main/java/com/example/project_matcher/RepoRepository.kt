package com.example.project_matcher

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import apolloClient
import com.example.rocketreserver.RepoListQuery
import com.example.rocketreserver.type.OrderDirection
import com.example.rocketreserver.type.RepositoryOrder
import com.example.rocketreserver.type.RepositoryOrderField

class RepoRepository (private val context: Context) {
    var repoList: RepoListQuery.Repositories? = null

    //suspend keyword forces this method to be called from within a coroutine scope
    suspend fun getRepos(query: String): RepoListQuery.Repositories? {
        //api call - need to move this to presenter?
        val response = apolloClient(context).query(
            RepoListQuery(
                order_input = RepositoryOrder(
                    RepositoryOrderField.STARGAZERS,
                    OrderDirection.DESC
                ),
                name_input = "android",
                label_input = "help wanted"
            )
        ).execute()
        return response.data?.topic?.repositories
    }

}


