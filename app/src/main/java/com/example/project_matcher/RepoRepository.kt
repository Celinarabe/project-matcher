package com.example.project_matcher

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import apolloClient
import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.example.project_matcher.model.Issue
import com.example.project_matcher.model.RepoDetail
import com.example.rocketreserver.RepoListQuery
import com.example.rocketreserver.type.OrderDirection
import com.example.rocketreserver.type.RepositoryOrder
import com.example.rocketreserver.type.RepositoryOrderField
import okhttp3.Call
import java.util.*
import java.util.concurrent.Callable

class RepoRepository (private val context: Context) {
    var repoList: RepoListQuery.Repositories? = null

    suspend fun getRepos(query: String): List<RepoDetail>? {
        try {
            val response = apolloClient(context).query(
                RepoListQuery(
                    order_input = RepositoryOrder(
                        RepositoryOrderField.STARGAZERS,
                        OrderDirection.DESC
                    ),
                    name_input = query,
                    label_input = "help wanted"
                )
            ).execute()
            val reposWithLabel = response?.data?.topic?.repositories?.edges?.filter { it?.node?.label != null  }

            if (reposWithLabel != null) {
                return convertRepos(reposWithLabel.filter { it!!.node?.label?.issues?.edges?.size!! >= 3 }.map{it!!})
            }
            return null
        } catch (exception: Exception) {
            return null
        }
    }

    private fun convertRepos(repositories: List<RepoListQuery.Edge>): List<RepoDetail>? {
        val parcelableRepos = repositories.map{ repo ->
            val issueList = repo.node?.label?.issues?.edges!!
            val issueListParsed = issueList.map { issue -> Issue(issue?.node?.number!!, issue?.node.title) }
            RepoDetail(repo.node?.nameWithOwner, repo.node?.description, repo.node?.openGraphImageUrl.toString(), issueListParsed )
        }
        return parcelableRepos
    }
}




