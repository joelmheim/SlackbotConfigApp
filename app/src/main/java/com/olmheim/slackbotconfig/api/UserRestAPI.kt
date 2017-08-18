package com.olmheim.slackbotconfig.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class UserRestAPI(): UserAPI {

    private val slackbotConfigApi: SlackbotConfigApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://slackbotconfig.herokuapp.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        slackbotConfigApi = retrofit.create(SlackbotConfigApi::class.java)
    }

    override fun users(): Call<List<SlackbotConfigUserResponse>> {
        return slackbotConfigApi.users()
    }
}