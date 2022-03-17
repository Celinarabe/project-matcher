package com.example.project_matcher

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import apolloClient
import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.ApolloClient
import com.example.rocketreserver.RepoListQuery
import com.example.rocketreserver.type.OrderDirection
import com.example.rocketreserver.type.RepositoryOrder
import com.example.rocketreserver.type.RepositoryOrderField
import okhttp3.Call
import java.util.*
import java.util.concurrent.Callable

class RepoRepository (private val context: Context) {
    var repoList: RepoListQuery.Repositories? = null

    //suspend keyword forces this method to be called from within a coroutine scope
    suspend fun getRepos(query: String): List<RepoListQuery.Edge?>? {
//        api call - need to move this to presenter?

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
        return response.data?.topic?.repositories?.edges
//     val client = apolloClient(context)
//        client.query(
//            RepoListQuery(
//                order_input = RepositoryOrder(
//                    RepositoryOrderField.STARGAZERS,
//                    OrderDirection.DESC
//                ),
//                name_input = "android",
//                label_input = "help wanted"
//            )
//        )
//
//        var call : ApolloCall.Callback<RepoListQuery.Data> = apolloClient(context).query(
//            RepoListQuery(
//                order_input = RepositoryOrder(
//                    RepositoryOrderField.STARGAZERS,
//                    OrderDirection.DESC
//                ),
//                name_input = "android",
//                label_input = "help wanted"
//            )
//        )
//        call.

    }

}


