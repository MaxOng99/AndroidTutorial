package com.example.tutorial2.data

import com.example.tutorial2.models.Case

class CasesDateSource {

    private var cases: ArrayList<Case> = ArrayList()

    private fun addCases(name:String, nricNo:String, policyNo:String, agent:String, process:String) {
        val case = Case(name, nricNo, policyNo, agent, process)
        cases.add(case)
    }

    fun removeDeveloper(case: Case) {
        cases.remove(case)
    }

    fun getCases(): ArrayList<Case>{
        return cases
    }

    fun populateData() {
        for (x in 0..5) {
            addCases("Alex Yeoh", "990920-10-9088", "TL2073123109", "Joseph", "Non-Financial Alteration")
        }
    }
}