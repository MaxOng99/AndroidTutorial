package com.example.tutorial2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorial2.models.PolicyOwner
import kotlinx.android.synthetic.main.case_card_view.view.*

class PolicyNumberRecyclerAdapter(private val dataSet: ArrayList<PolicyOwner>, private val onPolicyNumberListener: OnPolicyNumberListener):
    RecyclerView.Adapter<PolicyNumberRecyclerAdapter.PolicyNumberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PolicyNumberViewHolder {
        return PolicyNumberViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.case_card_view, parent, false), onPolicyNumberListener)
    }

    override fun onBindViewHolder(holder: PolicyNumberViewHolder, position: Int) {
        when (holder) {
            is PolicyNumberViewHolder -> {
                holder.bind(dataSet[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class PolicyNumberViewHolder(itemView: View, private val onPolicyNumberListener: OnPolicyNumberListener)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(owner: PolicyOwner) {
            itemView.name.text = owner.name
            itemView.ic_no_field.text = owner.nricNo
            itemView.policy_no_field.text = owner.policyNo
            itemView.process_field.text = owner.process
        }

        override fun onClick(p0: View?) {
            onPolicyNumberListener.onPolicyNumberClicked(adapterPosition)
        }
    }

    interface OnPolicyNumberListener {
        fun onPolicyNumberClicked(position:Int)
    }
}