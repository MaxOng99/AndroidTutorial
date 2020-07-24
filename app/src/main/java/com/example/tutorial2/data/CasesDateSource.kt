package com.example.tutorial2.data

import com.example.tutorial2.models.PolicyOwner

class CasesDateSource {

    private var policyOwners: ArrayList<PolicyOwner> = ArrayList()

    private fun addCases(name:String, nricNo:String, policyNo:String, agent:String, process:String) {
        val owner = PolicyOwner(name, nricNo, policyNo, agent, process)
        policyOwners.add(owner)
    }

    fun removeCase(case: PolicyOwner) {
        policyOwners.remove(case)
    }

    fun getCases(): ArrayList<PolicyOwner>{
        return policyOwners
    }

    fun populateData() {
        for (x in 0..5) {
            addCases("Alex Yeoh", "990920-10-9088", "TL2073123109", "Joseph", "Non-Financial Alteration")
        }
    }
}