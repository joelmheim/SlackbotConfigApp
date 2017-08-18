package com.olmheim.slackbotconfig.api

import retrofit2.Call

interface UserAPI {
    fun users(): Call<List<SlackbotConfigUserResponse>>
}