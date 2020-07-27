package com.example.tutorial2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tutorial2.models.PolicyOwner
import kotlinx.android.synthetic.main.case_details.view.*

class CaseDetailFragment : Fragment() {

    private var selectedPolicyOwner:PolicyOwner? = null
    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        selectedPolicyOwner = activity?.intent?.getParcelableExtra("selected_policy_owner")
        return inflater.inflate(R.layout.case_details, container, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.owner_name.text = selectedPolicyOwner?.name
        view.assured_name.text = selectedPolicyOwner?.name
    }
}