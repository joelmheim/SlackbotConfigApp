package com.olmheim.slackbotconfig.api

import retrofit2.Call
import retrofit2.http.GET

interface SlackbotConfigApi {
    @GET("/api/users")
    fun users(): Call<List<SlackbotConfigUserResponse>>
}
