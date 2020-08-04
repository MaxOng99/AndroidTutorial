package com.example.tutorial2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorial2.models.Agent
import com.example.tutorial2.models.PolicyOwner
import kotlinx.android.synthetic.main.search_ui.*
import java.util.*
import kotlin.collections.ArrayList


class SearchUiActivity : AppCompatActivity(), PolicyNumberRecyclerAdapter.OnPolicyNumberListener, AgentNameRecyclerAdapter.OnAgentNameListener {

    private lateinit var agentNameAdapter: AgentNameRecyclerAdapter
    private lateinit var policyNumberAdapter: PolicyNumberRecyclerAdapter
    private lateinit var cases: ArrayList<PolicyOwner>
    private lateinit var agents: ArrayList<Agent>
    private lateinit var filteredCases: ArrayList<PolicyOwner>
    private lateinit var filteredAgents: ArrayList<Agent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_ui)
        var toolbar = findViewById<Toolbar>(R.id.cancel_toolbar)
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_close_clear_cancel)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        cases = intent.getParcelableArrayListExtra<PolicyOwner>("data_source") as ArrayList<PolicyOwner>
        agents = intent.getParcelableArrayListExtra<Agent>("agents") as ArrayList<Agent>
        filteredCases = ArrayList(cases)
        filteredAgents = ArrayList(agents)

        initAdapters()
        initRecyclerView()
        initSearchView()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down)
    }

    private fun initAdapters() {
        agentNameAdapter = AgentNameRecyclerAdapter(filteredAgents, this)
        policyNumberAdapter = PolicyNumberRecyclerAdapter(filteredCases, this)
    }

    private fun initRecyclerView() {
        search_ui_recycler_view.apply {
            val topSpacingItemDecoration = SpacingItemDecoration(30)
            addItemDecoration(topSpacingItemDecoration)
            layoutManager = LinearLayoutManager(this@SearchUiActivity)
        }
    }

    private fun initSearchView() {
        search_ui_search_view.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText!!.isNotEmpty()) {
                    filteredCases.clear()
                    filteredAgents.clear()
                    val searchQuery = newText?.toLowerCase(Locale.getDefault())
                    find_text_helper.visibility = View.GONE
                    find_image.visibility = View.GONE
                    search_ui_recycler_view.visibility = View.VISIBLE

                    if (searchQuery.startsWith("tl")) {
                        search_ui_recycler_view.adapter = policyNumberAdapter
                        (search_ui_recycler_view.adapter as PolicyNumberRecyclerAdapter).notifyDataSetChanged()

                        for (owner in cases) {
                            val nameCondition = owner?.name?.toLowerCase()
                            val policyNumberCondition = owner?.policyNo?.toLowerCase()
                            val idCondition = owner?.nricNo?.toLowerCase()

                            if (nameCondition?.contains(searchQuery)!!
                                || policyNumberCondition?.contains(searchQuery)!!
                                || idCondition?.contains(searchQuery)!!)  {
                                filteredCases.add(owner)
                            }
                        }
                        if (filteredCases.size > 0) {
                            results_count.text = "Showing ${filteredCases.size} pending case(s)"
                            results_count.visibility = View.VISIBLE
                        }
                        else {
                            results_count.visibility = View.GONE
                        }

                    }
                    else {
                        search_ui_recycler_view.adapter = agentNameAdapter
                        (search_ui_recycler_view.adapter as AgentNameRecyclerAdapter).notifyDataSetChanged()
                        for (agent in agents) {
                            val agentNameCondition = agent?.name?.toLowerCase()

                            if (agentNameCondition?.contains(searchQuery)!!)  {
                                filteredAgents.add(agent)
                            }
                        }
                        if (filteredAgents.size > 0) {
                            results_count.text = "Showing ${filteredAgents.size} agent(s)"
                            results_count.visibility = View.VISIBLE
                        }
                        else {
                            results_count.visibility = View.GONE
                        }
                    }
                    search_ui_recycler_view.adapter?.notifyDataSetChanged()
                }

                else{
                    filteredCases.clear()
                    filteredAgents.clear()
                    search_ui_recycler_view.adapter?.notifyDataSetChanged()
                    find_image.visibility = View.VISIBLE
                    find_text_helper.visibility = View.VISIBLE
                    results_count.visibility = View.GONE
                    search_ui_recycler_view.visibility = View.GONE
                }
                return true
            }
        })
    }

    override fun onPolicyNumberClicked(position: Int) {
        val intent = Intent(this, CaseUiActivity::class.java)
        val policyOwner:PolicyOwner = filteredCases[position]
        intent.putExtra("selected_policy_owner", policyOwner)
        startActivity(intent)
    }

    override fun onAgentNameClicked(position: Int) {
        val intent = Intent(this, AgentActivity::class.java)
        val agent:Agent = filteredAgents[position]
        agent?.name?.let { Log.d("name", it) }
        intent.putExtra("selected_agent", agent)
        startActivity(intent)
    }
}