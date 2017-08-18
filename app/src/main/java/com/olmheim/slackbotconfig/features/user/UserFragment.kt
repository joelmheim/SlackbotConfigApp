package com.olmheim.slackbotconfig.features.user

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.olmheim.slackbotconfig.R
import com.olmheim.slackbotconfig.commons.RxBaseFragment
import com.olmheim.slackbotconfig.commons.SlackbotUserItem
import com.olmheim.slackbotconfig.commons.SlackbotUsers
import com.olmheim.slackbotconfig.commons.extensions.inflate
import com.olmheim.slackbotconfig.features.user.adapter.UserAdapter
import kotlinx.android.synthetic.main.user_fragment.*
import rx.schedulers.Schedulers

/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class UserFragment : RxBaseFragment() {

    companion object {
        private val KEY_SLACKBOT_USERS = "slackbotUsers"
    }

    private var slackbotUsers: SlackbotUsers? = null
    private val userManager by lazy { UserManager() }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.user_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        user_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }

        initAdapter()

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_SLACKBOT_USERS)) {
            slackbotUsers = savedInstanceState.get(KEY_SLACKBOT_USERS) as SlackbotUsers
            (user_list.adapter as UserAdapter).clearAndAddUsers(slackbotUsers!!.users)
        } else {
            requestUsers()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val users = (user_list.adapter as UserAdapter).getUsers()
        if (slackbotUsers != null && users.size > 0) {
            outState.putParcelable(KEY_SLACKBOT_USERS, slackbotUsers?.copy(users = users))
        }
    }

    private fun requestUsers() {
        val subscription = userManager.users()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { retrievedUsers ->
                            (user_list.adapter as UserAdapter).addUsers(retrievedUsers.users)
                        },
                        { e ->
                            Snackbar.make(user_list, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )

    }

    private fun initAdapter() {
        if (user_list.adapter == null) {
            user_list.adapter = UserAdapter()
        }
    }

}
