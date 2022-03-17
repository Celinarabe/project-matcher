package com.example.project_matcher

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_matcher.model.Topic
import com.example.rocketreserver.RepoListQuery
import org.w3c.dom.Text

class TopicAdapter(private val context: Context, private val dataset : List<Topic>
) : RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {

    class TopicViewHolder(private val view: View):RecyclerView.ViewHolder(view) {

        val title: TextView = view.findViewById(R.id.tvTopicTitle)
        val description: TextView = view.findViewById(R.id.tvTopicDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.topic_item, parent, false)
        return TopicViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val item = dataset?.get(position)
        holder.title.text = context.resources.getString(item.title)
        holder.description.text = context.resources.getString(item.description)
    }

    override fun getItemCount() = dataset.size ?: 0
}