package com.example.project_matcher.model

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

class RepoDetail(
    val nameWithOwner: String?,
    val description: String?,
    val openGraphImageUrl: String?,
    val issueList: List<Issue?>?
    ) : Parcelable {
    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(Issue.CREATOR)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nameWithOwner)
        parcel.writeString(description)
        parcel.writeString(openGraphImageUrl)
        parcel.writeTypedList(issueList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RepoDetail> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): RepoDetail {
            return RepoDetail(parcel)
        }
        override fun newArray(size: Int): Array<RepoDetail?> {
            return arrayOfNulls(size)
        }
    }
}

