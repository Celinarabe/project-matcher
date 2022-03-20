package com.example.project_matcher

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_matcher.model.Topic

class TopicListAdapter(private val context: Context,
                       private val dataset : List<Topic>,
                       private val onTopicClicked: (topic:Topic) -> Unit
) : RecyclerView.Adapter<TopicListAdapter.TopicViewHolder>() {

    class TopicViewHolder(private val view: View):RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvTopicTitle)
        val description: TextView = view.findViewById(R.id.tvTopicDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.topic_item, parent, false)
        return TopicViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val item = dataset?.get(position)
        holder.title.text = context.resources.getString(item.title)
        holder.description.text = context.resources.getString(item.description)
        holder.itemView.setOnClickListener { onTopicClicked(item) }
    }

    override fun getItemCount() = dataset.size ?: 0
}