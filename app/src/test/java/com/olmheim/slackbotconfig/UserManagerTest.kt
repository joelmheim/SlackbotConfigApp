package com.olmheim.slackbotconfig

import com.olmheim.slackbotconfig.api.SlackbotConfigUserResponse
import com.olmheim.slackbotconfig.api.UserAPI
import com.olmheim.slackbotconfig.commons.SlackbotUsers
import com.olmheim.slackbotconfig.features.user.UserManager
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import retrofit2.Call
import retrofit2.Response
import rx.observers.TestSubscriber

class UserManagerTest {

    var testSub = TestSubscriber<SlackbotUsers>()
    var apiMock = mock<UserAPI>()
    var callMock = mock<Call<List<SlackbotConfigUserResponse>>>()

    @Before
    fun setup() {
        testSub = TestSubscriber<SlackbotUsers>()
        apiMock = mock<UserAPI>()
        callMock = mock<Call<List<SlackbotConfigUserResponse>>>()
        `when`(apiMock.users()).thenReturn(callMock)
    }

    @Test
    fun testSuccess_basic() {
        // prepare
        val slackbotConfigUserResponse = listOf(SlackbotConfigUserResponse("name", "slackusername", "email", "mobile", "serialnumber", "_id"))
        val response = Response.success(slackbotConfigUserResponse)

        `when`(callMock.execute()).thenReturn(response)

        // call
        val userManager = UserManager(apiMock)
        userManager.users().subscribe(testSub)

        // assert
        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertCompleted()
    }

    @Test
    fun testSuccess_checkOneNews() {
        // prepare
        val userData = SlackbotConfigUserResponse(
                "name",
                "slackusername",
                "email",
                "mobile",
                "serialnumber",
                "_id"
        )
        val slackbotUserResponse = listOf(userData)
        val response = Response.success(slackbotUserResponse)

        `when`(callMock.execute()).thenReturn(response)

        // call
        val userManager = UserManager(apiMock)
        userManager.users().subscribe(testSub)

        // assert
        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertCompleted()

        assert(testSub.onNextEvents[0].users[0].name == userData.name)
        assert(testSub.onNextEvents[0].users[0].slackusername == userData.slackusername)
    }

    @Test
    fun testError() {
        // prepare
        val responseError = Response.error<List<SlackbotConfigUserResponse>>(500,
                ResponseBody.create(MediaType.parse("application/json"), ""))

        `when`(callMock.execute()).thenReturn(responseError)

        // call
        val userManager = UserManager(apiMock)
        userManager.users().subscribe(testSub)

        // assert
        assert(testSub.onErrorEvents.size == 1)
    }
}

inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)
