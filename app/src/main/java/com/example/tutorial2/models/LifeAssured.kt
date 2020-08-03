package com.example.tutorial2.models

import android.os.Parcel
import android.os.Parcelable
import java.text.Normalizer

class LifeAssured(
    var name: String?,
    private var forms: ArrayList<PolicyForm>?

):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readArrayList(PolicyForm::class.java.classLoader) as ArrayList<PolicyForm>?
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeList(forms)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun addForm(title:String, description:String, date:String, fileSize:String, page:String, action:String) {
        val newForm = PolicyForm(title, description, date, fileSize, page, action)
        forms?.add(newForm)
    }

    fun getFormList():ArrayList<PolicyForm>? {
        return forms
    }

    fun getForm(position:Int): PolicyForm? {
        return forms?.get(position)
    }

    companion object CREATOR : Parcelable.Creator<LifeAssured> {
        override fun createFromParcel(parcel: Parcel): LifeAssured {
            return LifeAssured(parcel)
        }

        override fun newArray(size: Int): Array<LifeAssured?> {
            return arrayOfNulls(size)
        }
    }

}