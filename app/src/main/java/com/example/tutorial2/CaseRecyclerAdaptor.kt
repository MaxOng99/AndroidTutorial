package com.example.tutorial2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorial2.models.PolicyOwner
import kotlinx.android.synthetic.main.case_card_view.view.*

class CaseRecyclerAdaptor(private val dataSet: ArrayList<PolicyOwner>, private val onCaseListener: OnCaseListener):
    RecyclerView.Adapter<CaseRecyclerAdaptor.CaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaseViewHolder {
        return CaseViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.case_card_view, parent, false), onCaseListener)
    }

    override fun onBindViewHolder(holder: CaseViewHolder, position: Int) {
        when (holder) {
            is CaseViewHolder -> {
                holder.bind(dataSet[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class CaseViewHolder(itemView: View, private val onCaseListener: OnCaseListener)
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
            onCaseListener.onCaseClick(adapterPosition)
        }
    }

    interface OnCaseListener {
        fun onCaseClick(position:Int)
    }
}