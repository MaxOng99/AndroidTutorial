package com.example.tutorial2.models

import android.os.Parcel
import android.os.Parcelable

class PolicyForm(var title:String?,
                 var description:String?,
                 var date:String?,
                 var fileSize:String?,
                 var page:String?,
                 var action:String?
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(date)
        parcel.writeString(fileSize)
        parcel.writeString(page)
        parcel.writeString(action)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PolicyForm> {
        override fun createFromParcel(parcel: Parcel): PolicyForm {
            return PolicyForm(parcel)
        }

        override fun newArray(size: Int): Array<PolicyForm?> {
            return arrayOfNulls(size)
        }
    }
}