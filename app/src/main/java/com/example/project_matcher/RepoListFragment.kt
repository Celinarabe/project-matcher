package com.example.project_matcher

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
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

    var repoList:List<RepoListQuery.Edge?>? = listOf()

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPresenter(MainPresenter(this, requireContext()))
        setHasOptionsMenu(true);
        Log.d("LaunchList", "fragggg")
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val queryString = sharedPref.getString(getString(R.string.user_query_key), "android")
//        handleIntent(intent)
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

        binding.btnLogout.setOnClickListener {handleLogout()}
        binding.btnData.setOnClickListener {handleSearch("android") }
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
        val recyclerView = binding.rvRepoList
        recyclerView.adapter = RepoAdapter(requireContext(), repoList)
        Log.d("LaunchList", "eeek${repoList}")
        binding.tvGetStarted.visibility = View.GONE
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
        presenter.onUserSearch(query.lowercase())
    }

    fun handleLogout() {
        Firebase.auth.signOut()
    }
}