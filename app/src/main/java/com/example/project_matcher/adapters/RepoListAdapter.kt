package com.example.project_matcher.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_matcher.R
import com.example.project_matcher.models.RepoDetail

class RepoListAdapter(private val context: Context, private val dataset: List<RepoDetail>?, private val onRepoClicked: (repo : RepoDetail) -> Unit
) : RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>() {

    class RepoViewHolder(private val view: View):RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvTitle)
        val description: TextView = view.findViewById(R.id.tvDescription)
        val image : ImageView = view.findViewById(R.id.imgRepo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_item, parent, false)
        return RepoViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = dataset?.get(position)
        holder.title.text = item?.nameWithOwner
        holder.description.text = item?.description
        val imageUrl = item?.openGraphImageUrl
        imageUrl?.let {
            Glide.with(context).load(imageUrl).into(holder.image)
        }
        holder.itemView.setOnClickListener {
            item?.let { it1 -> onRepoClicked(it1) }
        }
    }
    override fun getItemCount() = dataset?.size ?: 0
}