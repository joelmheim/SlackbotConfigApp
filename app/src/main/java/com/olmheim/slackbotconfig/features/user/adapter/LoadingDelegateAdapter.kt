package com.olmheim.slackbotconfig.features.user.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.olmheim.slackbotconfig.R
import com.olmheim.slackbotconfig.commons.adapter.ViewType
import com.olmheim.slackbotconfig.commons.adapter.ViewTypeDelegateAdapter
import com.olmheim.slackbotconfig.commons.extensions.inflate

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.user_item_loading)) {
    }
}