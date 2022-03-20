package com.example.project_matcher.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.project_matcher.TopicListAdapter
import com.example.project_matcher.data.Topics
import com.example.project_matcher.databinding.FragmentTopicListBinding
import com.example.project_matcher.model.Topic


class TopicListFragment : Fragment() {
    private var _binding: FragmentTopicListBinding? = null
    private val binding get() = _binding!!

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
        val topicList = Topics().loadTopics()
        val recyclerView = binding.rvTopicList
        recyclerView.adapter = TopicListAdapter(requireContext(), topicList) { onTopicClicked(it) }
    }

    /**
     * Handles user click on specific topic item
     */
    private fun onTopicClicked(topic: Topic) {
        val action = TopicListFragmentDirections.actionTopicListFragmentToRepoListFragment(requireContext().getString(topic.title))
        this.findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}