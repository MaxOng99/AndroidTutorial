package com.example.tutorial2.data

import com.example.tutorial2.models.Agent
import com.example.tutorial2.models.PolicyOwner

class CasesDataSource {

    private var policyOwners: ArrayList<PolicyOwner> = ArrayList()
    private var agents: ArrayList<Agent> = ArrayList()

    private fun addCases(name:String, nricNo:String, policyNo:String, agent: String?, process:String):PolicyOwner {
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

    fun getAgents():ArrayList<Agent> {
        return agents
    }

    fun populateAgent() {
        for (x in 0..5) {
            val agent = Agent("Joseph$x", "${x}579", ArrayList())
            agents.add(agent)
        }
    }

    fun populateData() {
        for (x in 0..5) {
            val agent = agents[x]
            val owner = addCases("Alex Yeoh$x", "990920-10-9088", "TL2073123109", agent.name, "Non-Financial Alteration")
            agent.addOwners(owner)
            val assured1 = owner.addAssured("John")
            val assured2 = owner.addAssured("Cassie")

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

        val agent = Agent("Chang Ying Ying", "801293", ArrayList())
        val name = agent.name
        val owner = addCases("martin", "990728-10-9999", "TL2073123109", name, "Non-Financial Alteration")
        agent.addOwners(owner)
        val assured1 = owner.addAssured("Amanda")
        val assured2 = owner.addAssured("Joseph")

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