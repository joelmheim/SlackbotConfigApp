package com.olmheim.slackbotconfig.features.user.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.olmheim.slackbotconfig.R
import com.olmheim.slackbotconfig.commons.SlackbotUserItem
import com.olmheim.slackbotconfig.commons.adapter.ViewType
import com.olmheim.slackbotconfig.commons.adapter.ViewTypeDelegateAdapter
import com.olmheim.slackbotconfig.commons.extensions.inflate
import kotlinx.android.synthetic.main.user_item.view.*

class UserDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as SlackbotUserItem)
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.user_item)) {

        fun bind(item: SlackbotUserItem) = with(itemView) {
            name.text = item.name
            slackusername.text = item.slackusername
        }
    }
}