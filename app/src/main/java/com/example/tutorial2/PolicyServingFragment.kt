package com.example.tutorial2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorial2.data.CasesDataSource
import com.example.tutorial2.models.PolicyOwner
import kotlinx.android.synthetic.main.e_doc_fragment.*

class PolicyServingFragment(private val data:CasesDataSource) : Fragment(), CaseRecyclerAdaptor.OnCaseListener{

    private lateinit var caseAdapter:CaseRecyclerAdaptor
    private lateinit var cases:ArrayList<PolicyOwner>

    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.e_doc_fragment, container, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        case_recycler_view.apply {
            val topSpacingItemDecoration = SpacingItemDecoration(30)
            var dataSource = data
            cases = dataSource.getCases()
            addItemDecoration(topSpacingItemDecoration)
            layoutManager = LinearLayoutManager(activity)
            caseAdapter = CaseRecyclerAdaptor(cases, this@PolicyServingFragment)
            adapter = caseAdapter
            pending_cases.text = "Showing ${cases.size.toString()} pending case(s)"
        }
    }

    override fun onCaseClick(position: Int) {
        val intent = Intent(activity, CaseUiActivity::class.java)
        val policyOwner = cases.get(position)
        val assuredList = policyOwner.getAssuredList()
        intent.putExtra("selected_policy_owner", policyOwner)
        startActivity(intent)
    }

}