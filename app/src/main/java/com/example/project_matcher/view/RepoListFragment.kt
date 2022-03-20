package com.example.project_matcher.view

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.project_matcher.R
import com.example.project_matcher.RepoListAdapter
import com.example.project_matcher.base.MainContract
import com.example.project_matcher.databinding.FragmentRepoListBinding
import com.example.project_matcher.model.RepoDetail
import com.example.project_matcher.presenter.MainPresenter
import com.google.android.material.snackbar.Snackbar


class RepoListFragment : Fragment(), MainContract.View {
    private var _binding: FragmentRepoListBinding? = null
    private val binding get() = _binding
    private lateinit var presenter: MainContract.Presenter
    private val navigationArgs: RepoListFragmentArgs by navArgs()
    private var cachedRepos:List<RepoDetail>? = null

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPresenter(MainPresenter(this, requireContext()))
        setHasOptionsMenu(true);
        val query = navigationArgs.topicTitle
        handleSearch(query.lowercase())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        cachedRepos?.let{
            displayCachedRepos()}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
        _binding = null
    }

    private fun displayCachedRepos() {
        displayRepos(cachedRepos)
    }

    override fun displayRepos(repoList: List<RepoDetail>?) {
        cachedRepos = repoList
        binding?.liRepoList?.hide()
        val recyclerView = binding?.rvRepoList
        recyclerView?.adapter = RepoListAdapter(requireContext(), repoList) { onRepoClicked(it) }
    }

    override fun handleBadResponse() {
        binding?.rvRepoList?.let {
            Snackbar.make(it, R.string.api_error, Snackbar.LENGTH_SHORT)
                .show()
        }
        this.findNavController().navigate(R.id.action_repoListFragment_to_topicListFragment)
    }

    private fun handleSearch(query: String) {
        presenter.onUserSearch(query.lowercase())
    }

    /**
     * Handles user click on a specific repository. Creates a bundle with the repository parcelable object.
     */
    private fun onRepoClicked(repo :RepoDetail) {
        setFragmentResult("requestKey", bundleOf("repoDetails" to repo))
        val action = RepoListFragmentDirections.actionRepoListFragmentToRepoDetailFragment(repo.nameWithOwner!!)
        this.findNavController().navigate(action)
    }
}