package com.example.tutorial2.data

import com.example.tutorial2.models.PolicyOwner

class CasesDataSource {

    private var policyOwners: ArrayList<PolicyOwner> = ArrayList()

    private fun addCases(name:String, nricNo:String, policyNo:String, agent:String, process:String):PolicyOwner {
        val owner = PolicyOwner(name, nricNo, policyNo, agent, process, ArrayList())
        policyOwners.add(owner)
        return owner
    }

    fun removeCase(case: PolicyOwner) {
        policyOwners.remove(case)
    }

    fun getCases(): ArrayList<PolicyOwner>{
        return policyOwners
    }

    fun populateData() {
        for (x in 0..5) {
            val owner = addCases("Alex Yeoh", "990920-10-9088", "TL2073123109", "Joseph", "Non-Financial Alteration")
            val assured1 = owner.addAssured("Doge")
            val assured2 = owner.addAssured("Cate")

            assured1.addForm("HLA Blood Profile 3 - Lipids Profile",
                "Lorem ipsum dolor sit amet" +
                        "HLA Blood Profile 3 - Lipids Profile" +
                        "HLA Blood Profile 3 - Lipids Profile" +
                        "Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet",
            "22 Oct 2018",
            "1.2 MB",
            "12 pg.",
            "Camera")
            assured1.addForm("HLA Blood Profile 3 - Lipids Profile",
                "Lorem ipsum dolor sit amet" +
                        "HLA Blood Profile 3 - Lipids Profile" +
                        "HLA Blood Profile 3 - Lipids Profile" +
                        "Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet",
                "22 Oct 2018",
                "1.2 MB",
                "12 pg.",
                "Uploaded")
            assured2.addForm("HLA Blood Profile 3 - Lipids Profile",
                "Lorem ipsum dolor sit amet" +
                        "HLA Blood Profile 3 - Lipids Profile" +
                        "HLA Blood Profile 3 - Lipids Profile" +
                        "Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet",
                "22 Oct 2018",
                "1.2 MB",
                "12 pg.",
                "Important")
            assured2.addForm("HLA Blood Profile 3 - Lipids Profile",
                "Lorem ipsum dolor sit amet" +
                           "HLA Blood Profile 3 - Lipids Profile" +
                           "HLA Blood Profile 3 - Lipids Profile" +
                           "Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet",
                "22 Oct 2018",
                "1.2 MB",
                "12 pg.",
                "Success")
        }
    }
}