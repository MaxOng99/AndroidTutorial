package com.example.tutorial2.models

import android.os.Parcel
import android.os.Parcelable

class PolicyOwner(
    var name:String?,
    var nricNo:String?,
    var policyNo:String?,
    var agent:String?,
    var process:String?,
    var lifeAssuredList:ArrayList<LifeAssured>?

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readArrayList(LifeAssured::class.java.classLoader) as ArrayList<LifeAssured>?
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(nricNo)
        parcel.writeString(policyNo)
        parcel.writeString(agent)
        parcel.writeString(process)
        parcel.writeList(lifeAssuredList)
    }

    fun addAssured(assuredName:String):LifeAssured {
        val newAssured = LifeAssured(assuredName, ArrayList())
        lifeAssuredList?.add(newAssured)
        return newAssured
    }

    fun getAssuredList():ArrayList<LifeAssured>? {
        return lifeAssuredList
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PolicyOwner> {
        override fun createFromParcel(parcel: Parcel): PolicyOwner {
            return PolicyOwner(parcel)
        }

        override fun newArray(size: Int): Array<PolicyOwner?> {
            return arrayOfNulls(size)
        }
    }

}
