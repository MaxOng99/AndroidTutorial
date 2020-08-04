package com.example.tutorial2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorial2.models.PolicyOwner
import kotlinx.android.synthetic.main.e_doc_fragment.*
import java.util.*
import kotlin.collections.ArrayList

class NewBusinessFragment(private val data: ArrayList<PolicyOwner>?, private val searchView: SearchView) : Fragment(), CaseRecyclerAdaptor.OnCaseListener {

    private lateinit var caseAdapter: CaseRecyclerAdaptor
    private lateinit var cases: ArrayList<PolicyOwner>
    private lateinit var filteredCases: ArrayList<PolicyOwner>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.e_doc_fragment, container, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initSearchView()
    }

    private fun initSearchView() {
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText!!.isNotEmpty()) {
                    filteredCases.clear()
                    val searchQuery = newText?.toLowerCase(Locale.getDefault())

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

                    when (filteredCases.size) {
                        0 -> {
                            no_result_image.visibility = View.VISIBLE
                            no_result_text.visibility = View.VISIBLE
                            case_recycler_view.visibility = View.GONE
                            pending_cases.visibility = View.GONE
                        }
                        else -> {
                            no_result_text.visibility = View.GONE
                            no_result_image.visibility = View.GONE
                            case_recycler_view.visibility = View.VISIBLE
                            pending_cases.visibility = View.VISIBLE
                        }
                    }

                    caseAdapter.notifyDataSetChanged()
                    pending_cases.text = "Showing ${filteredCases.size.toString()} pending case(s)"
                }

                else{
                    filteredCases.clear()
                    filteredCases.addAll(cases)

                    when (filteredCases.size) {
                        0 -> {
                            no_result_image.visibility = View.VISIBLE
                            no_result_text.visibility = View.VISIBLE
                            case_recycler_view.visibility = View.GONE
                            pending_cases.visibility = View.GONE
                        }
                        else -> {
                            no_result_text.visibility = View.GONE
                            no_result_image.visibility = View.GONE
                            case_recycler_view.visibility = View.VISIBLE
                            pending_cases.visibility = View.VISIBLE
                        }
                    }

                    caseAdapter.notifyDataSetChanged()
                    pending_cases.text = "Showing ${filteredCases.size.toString()} pending case(s)"
                }
                return true
            }

        })
    }
    private fun initRecyclerView() {
        case_recycler_view.apply {
            val topSpacingItemDecoration = SpacingItemDecoration(30)
            if (data != null) {
                cases = data
            }
            filteredCases = ArrayList(cases)
            addItemDecoration(topSpacingItemDecoration)
            layoutManager = LinearLayoutManager(activity)
            caseAdapter = CaseRecyclerAdaptor(filteredCases, this@NewBusinessFragment)
            adapter = caseAdapter
            pending_cases.text = "Showing ${filteredCases.size} pending case(s)"
        }
    }

    override fun onCaseClick(position: Int) {
        val intent = Intent(activity, CaseUiActivity::class.java)
        val policyOwner = cases[position]
        intent.putExtra("selected_policy_owner", policyOwner)
        startActivity(intent)
    }
}