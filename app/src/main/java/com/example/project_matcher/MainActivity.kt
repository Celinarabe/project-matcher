package com.example.project_matcher

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.example.project_matcher.databinding.ActivityMainBinding

class MainActivity : FirebaseAuthProvider() {
    lateinit var binding: ActivityMainBinding
    lateinit var optionsMenu : Menu

//    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true);
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        handleIntent(intent)

        Log.d("LaunchList", "YOYOYOYOYOY")

        if(auth.currentUser == null){
            redirect(this, AuthActivity::class.java)
        }

    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.search_menu, menu)
//    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.search_menu, menu)
////        optionsMenu = menu
////        optionsMenu.findItem(R.id.search).expandActionView()
//        // Associate searchable configuration with the SearchView
//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        (menu.findItem(R.id.btnSearchIcon).actionView as android.widget.SearchView).apply {
//            setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        }
//        return true
//    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query: String? = intent.getStringExtra(SearchManager.QUERY)
            query?.let { setQuery(query) }
//            Log.d("LaunchList", query.toString())

//            query?.let { handleSearch(query) }
        }
//        var amount: Nothing? = null
//        val bundle = bundleOf("amount" to amount)
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
//        val navController = this.findNavController(R.id.nav_host_fragment)
////        this.findNavController(R.id.nav_host_fragment).navigate(R.id.action_repoListFragment_to_repoDetailFragment)
    }

//    override fun onStart() {
//        super.onStart()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//    }
    private fun setQuery(query: String) {
    val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
    with(sharedPref.edit()) {
        putString(getString(R.string.user_query_key), query)
        apply()
    }
}

}
