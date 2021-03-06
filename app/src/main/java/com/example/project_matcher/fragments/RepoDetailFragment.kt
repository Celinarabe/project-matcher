package com.example.project_matcher.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.bumptech.glide.Glide
import com.example.project_matcher.adapters.IssueListAdapter
import com.example.project_matcher.databinding.FragmentRepoDetailBinding
import com.example.project_matcher.models.RepoDetail


class RepoDetailFragment : Fragment() {
    private var _binding: FragmentRepoDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("requestKey") { _, bundle ->
            val result = bundle.getParcelable<RepoDetail>("repoDetails")
            if (result != null) {
                displayRepoDetails(result)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayRepoDetails(repoDetail: RepoDetail) {
        binding.tvDescription.text = repoDetail.description
        binding.tvTitle.text = repoDetail.nameWithOwner
        Glide.with(requireContext()).load(repoDetail.openGraphImageUrl).into(binding.imgRepo)
            val recyclerView = binding.rvIssueList
            recyclerView.adapter = IssueListAdapter(repoDetail.issueList)
    }
}