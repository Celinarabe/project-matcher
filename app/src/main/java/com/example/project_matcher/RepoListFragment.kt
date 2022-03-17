package com.example.project_matcher

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.project_matcher.base.MainContract
import com.example.project_matcher.databinding.FragmentRepoListBinding
import com.example.rocketreserver.RepoListQuery
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RepoListFragment : Fragment(), MainContract.View {
    //reference to the view binding object
    private var _binding: FragmentRepoListBinding? = null
    //non null assertion when you know its not null
    private val binding get() = _binding!!

    private lateinit var presenter: MainContract.Presenter
    private val navigationArgs: RepoListFragmentArgs by navArgs()

    var repoList:List<RepoListQuery.Edge?>? = listOf()

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPresenter(MainPresenter(this, requireContext()))
        setHasOptionsMenu(true);
        val query = navigationArgs.topicTitle
        handleSearch(query.lowercase())
//        activity?.title = "{$query.replaceFirstChar{it.uppercase()}} Repositories"
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
        presenter.onViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
        _binding = null
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.search_menu, menu)
//        val sv = SearchView(context)
//        val item = menu.findItem(R.id.btnSearchIcon)
//        MenuItemCompat.setShowAsAction(
//            item,
//            MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItemCompat.SHOW_AS_ACTION_IF_ROOM
//        )
//        MenuItemCompat.setActionView(item, sv)
//        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                Log.d("LaunchList", query!!)
//                handleSearch(query!!)
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                Log.d("LaunchList", "tap")
//                return false
//            }
//        })
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.btnSearch -> {
////                handleIntent(in)
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    override fun displayRepos(repoList: List<RepoListQuery.Edge?>?) {
        binding.liRepoList.hide()
        val recyclerView = binding.rvRepoList
        recyclerView.adapter = RepoListAdapter(requireContext(), repoList)
        Log.d("LaunchList", "eeek${repoList}")
//        view.findNavController().navigate()
    }

//    fun onNewIntent(intent: Intent) {
//        handleIntent(intent)
//    }

//    private fun handleIntent(intent: Intent) {
//        if (Intent.ACTION_SEARCH == intent.action) {
//            val query: String? = intent.getStringExtra(SearchManager.QUERY)
//            query?.let { handleSearch(query) }
//        }
//    }

    private fun handleSearch(query: String) {
        Log.d("Lunch", query)
        presenter.onUserSearch(query.lowercase())
    }


}