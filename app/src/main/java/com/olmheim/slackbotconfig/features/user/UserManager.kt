package com.olmheim.slackbotconfig.features.user

import com.olmheim.slackbotconfig.api.UserAPI
import com.olmheim.slackbotconfig.api.UserRestAPI
import com.olmheim.slackbotconfig.commons.SlackbotUserItem
import com.olmheim.slackbotconfig.commons.SlackbotUsers
import rx.Observable

class UserManager(private val api: UserAPI = UserRestAPI()) {

    fun users(): Observable<SlackbotUsers> {
        return Observable.create { subscriber ->
            val callResponse = api.users()
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val users = response.body().map {
                    SlackbotUserItem(
                            name = it.name,
                            slackusername = it.slackusername,
                            email = it.email,
                            mobile = it.mobile,
                            serialnumber = it.serialnumber,
                            _id = it._id)
                }
                subscriber.onNext(SlackbotUsers(users))
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}