package com.olmheim.slackbotconfig.commons

import android.os.Parcel
import android.os.Parcelable
import com.olmheim.slackbotconfig.commons.adapter.AdapterConstants
import com.olmheim.slackbotconfig.commons.adapter.ViewType
import com.olmheim.slackbotconfig.commons.extensions.createParcel

data class SlackbotUsers(
        val users: List<SlackbotUserItem>
): Parcelable {

    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = createParcel { SlackbotUsers(it) }
    }

    protected constructor(parcelIn: Parcel) : this(
            mutableListOf<SlackbotUserItem>().apply {
                parcelIn.readTypedList(this, SlackbotUserItem.CREATOR)
            }
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeTypedList(users)
    }

    override fun describeContents() = 0
}

data class SlackbotUserItem(
        val name: String,
        val slackusername: String,
        val email: String?,
        val mobile: String?,
        val serialnumber: String,
        val _id: String
): ViewType, Parcelable {

    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = createParcel { SlackbotUserItem(it) }
    }

    protected constructor(parcelIn: Parcel): this (
        parcelIn.readString(),
        parcelIn.readString(),
        parcelIn.readString(),
        parcelIn.readString(),
        parcelIn.readString(),
        parcelIn.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(slackusername)
        dest.writeString(email)
        dest.writeString(mobile)
        dest.writeString(serialnumber)
        dest.writeString(_id)
    }

    override fun describeContents() = 0

    override fun getViewType() = AdapterConstants.USER
}
