package com.example.project_matcher

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import apolloClient
import com.example.project_matcher.base.MainContract
import com.example.project_matcher.databinding.FragmentRepoListBinding
import com.example.rocketreserver.RepoListQuery
import com.example.rocketreserver.type.OrderDirection
import com.example.rocketreserver.type.RepositoryOrder
import com.example.rocketreserver.type.RepositoryOrderField
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



class RepoListFragment : Fragment(), MainContract.View {
    //reference to the view binding object
    private var _binding: FragmentRepoListBinding? = null
    //non null assertion when you know its not null
    private val binding get() = _binding!!

    private lateinit var mPresenter: MainContract.Presenter

    var repoList:RepoListQuery.Repositories? = null

    override fun setPresenter(presenter: MainContract.Presenter) {
        mPresenter = presenter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPresenter(MainPresenter(this, requireContext()))
        mPresenter.onViewCreated()

            //api call - need to move this to presenter?
//        lifecycleScope.launchWhenResumed {
//            val response = apolloClient(requireContext()).query(RepoListQuery(
//                order_input = RepositoryOrder(
//                    RepositoryOrderField.STARGAZERS,
//                    OrderDirection.DESC
//                ),
//                name_input = "android",
//                label_input = "help wanted"
//            )).execute()
//            Log.d("LaunchList", "Success ${response.data?.topic?.repositories?.edges?.elementAt(2)?.node}")
//        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener {
            Firebase.auth.signOut()
        }
        binding.btnData.setOnClickListener {
            Log.d("LaunchList", "eeek")
            repoList = mPresenter.onUserSearch("dis topic")
            Log.d("LaunchList", "eeek$repoList")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun displayRepos(repoList: RepoListQuery.Repositories) {

    }
}