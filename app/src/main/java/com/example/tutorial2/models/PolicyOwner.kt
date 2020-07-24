package com.example.tutorial2.models

class PolicyOwner (
    var name:String,
    var nricNo:String,
    var policyNo:String,
    var agent:String,
    var process:String
){

    private var lifeAssuredList: ArrayList<LifeAssured> = ArrayList()

}