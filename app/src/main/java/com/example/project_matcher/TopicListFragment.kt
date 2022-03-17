package com.example.project_matcher

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.project_matcher.data.Topics
import com.example.project_matcher.databinding.FragmentTopicListBinding
import com.example.project_matcher.model.Topic


class TopicListFragment : Fragment() {
    private var _binding: FragmentTopicListBinding? = null
    private val binding get() = _binding!! //we know _bindin will have a value after it's assigned in onCreateView()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopicListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        load data
        val topicList = Topics().loadTopics()
        val recyclerView = binding.rvTopicList
        recyclerView.adapter = TopicListAdapter(requireContext(), topicList) { onTopicClicked(it) }


        Log.d("Lunch", topicList.toString())
//        binding.rvTopicList.setHasFixedSize(true)
    }
    private fun onTopicClicked(topic: Topic) {
        val action = TopicListFragmentDirections.actionTopicListFragmentToRepoListFragment(requireContext().getString(topic.title).lowercase())
        this.findNavController().navigate(action)
    }
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.search_menu, menu)
//
//        val layoutButton = menu.findItem(R.id.action_switch_layout)
//        setIcon(layoutButton)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}