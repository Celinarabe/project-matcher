package com.example.project_matcher

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.bumptech.glide.Glide
import com.example.project_matcher.databinding.FragmentRepoDetailBinding
import com.example.project_matcher.model.RepoDetail


class RepoDetailFragment : Fragment() {
    private var _binding: FragmentRepoDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("requestKey") { requestKey, bundle ->
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
        // Inflate the layout for this fragment
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

//        repoDetail.issueList?.forEachIndexed { index, issueObj ->
//            val numBind = "binding.tvIssueNumber1${index}"
//            numBind.toView
//        }
//        val issueNum1 = "#${repoDetail.issueList?.elementAt(0)?.number}"
//        val issueNum2 = "#${repoDetail.issueList?.elementAt(1)?.number}"
//        val issueNum3 = "#${repoDetail.issueList?.elementAt(2)?.number}"
//        binding.tvIssueNumber1.text = issueNum1
//        binding.tvIssueNumber2.text = issueNum2
//        binding.tvIssueNumber3.text = issueNum3
//        binding.tvIssueTitle1.text = repoDetail.issueList?.elementAt(0)?.title
//        binding.tvIssueTitle2.text = repoDetail.issueList?.elementAt(1)?.title
//        binding.tvIssueTitle3.text = repoDetail.issueList?.elementAt(2)?.title

    }
}