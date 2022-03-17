package com.example.project_matcher

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rocketreserver.RepoListQuery

class RepoAdapter(private val context: Context, private val dataset : List<RepoListQuery.Edge?>?
) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    class RepoViewHolder(private val view: View):RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvTitle)
        val description: TextView = view.findViewById(R.id.tvDescription)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return RepoViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = dataset?.get(position)
        holder.title.text = item?.node?.nameWithOwner
        holder.description.text = item?.node?.description
    }

    override fun getItemCount() = dataset?.size ?: 0
}