package com.example.tutorial2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorial2.models.LifeAssured
import com.example.tutorial2.models.PolicyOwner
import kotlinx.android.synthetic.main.info.*

class InfoFragment : Fragment(), LifeAssuredRecyclerAdapter.OnLifeAssuredListener{

    private lateinit var lifeAssuredRecyclerAdapter: LifeAssuredRecyclerAdapter
    private var assureds:ArrayList<LifeAssured>? = ArrayList()
    private var policyOwner:PolicyOwner? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        policyOwner = activity?.intent?.getParcelableExtra("selected_policy_owner")
        assureds = policyOwner?.getAssuredList()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        info_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            lifeAssuredRecyclerAdapter = LifeAssuredRecyclerAdapter(assureds, this@InfoFragment)
            adapter = lifeAssuredRecyclerAdapter
        }
    }

    override fun onLifeAssuredClick(position: Int) {

    }

}