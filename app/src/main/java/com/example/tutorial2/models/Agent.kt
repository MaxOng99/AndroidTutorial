package com.example.tutorial2.models

import android.os.Parcel
import android.os.Parcelable

class Agent(
    var name:String?,
    var code:String?,
    var policyOwners:ArrayList<PolicyOwner>?

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readArrayList(PolicyOwner::class.java.classLoader) as ArrayList<PolicyOwner>?
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(code)
        parcel.writeList(policyOwners)
    }

    fun addOwners(policyOwner: PolicyOwner):PolicyOwner {
        policyOwners?.add(policyOwner)
        return policyOwner
    }

    fun getPolicyOwnerList():ArrayList<PolicyOwner>? {
        return policyOwners
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Agent> {
        override fun createFromParcel(parcel: Parcel): Agent {
            return Agent(parcel)
        }
        override fun newArray(size: Int): Array<Agent?> {
            return arrayOfNulls(size)
        }
    }
}
