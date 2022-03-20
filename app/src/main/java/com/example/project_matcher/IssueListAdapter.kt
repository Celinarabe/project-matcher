package com.example.project_matcher

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_matcher.model.Issue

class IssueListAdapter(private val context: Context, private val dataset :  List<Issue?>?) : RecyclerView.Adapter<IssueListAdapter.IssueViewHolder>() {

    class IssueViewHolder(private val view: View):RecyclerView.ViewHolder(view) {

        val title: TextView = view.findViewById(R.id.tvIssueTitle)
        val number: TextView = view.findViewById(R.id.tvIssueNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.issue_item, parent, false)
        return IssueViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        val item = dataset?.get(position)
        holder.title.text = item?.title
        holder.number.text = "#${item?.number.toString()}"
    }

    override fun getItemCount() = dataset?.size ?: 0
}