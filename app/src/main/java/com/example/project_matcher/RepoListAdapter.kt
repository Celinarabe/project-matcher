package com.example.project_matcher

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rocketreserver.RepoListQuery

class RepoListAdapter(private val context: Context, private val dataset : List<RepoListQuery.Edge?>?
) : RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>() {

    class RepoViewHolder(private val view: View):RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvTitle)
        val description: TextView = view.findViewById(R.id.tvDescription)
        val image : ImageView = view.findViewById(R.id.imgRepo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_item, parent, false)
        return RepoViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = dataset?.get(position)
        holder.title.text = item?.node?.nameWithOwner
        holder.description.text = item?.node?.description
        val imageUrl = item?.node?.openGraphImageUrl
        imageUrl?.let {
            Glide.with(context).load(imageUrl).into(holder.image)
        }
    }


    override fun getItemCount() = dataset?.size ?: 0
}