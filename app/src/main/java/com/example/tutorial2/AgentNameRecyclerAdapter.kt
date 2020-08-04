package com.example.tutorial2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorial2.models.Agent
import kotlinx.android.synthetic.main.agent_recycler_view_item.view.*

class AgentNameRecyclerAdapter(private val dataSet: ArrayList<Agent>, private val onAgentNameListener: OnAgentNameListener):
    RecyclerView.Adapter<AgentNameRecyclerAdapter.AgentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        return AgentViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.agent_recycler_view_item, parent, false), onAgentNameListener)
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        when (holder) {
            is AgentViewHolder -> {
                holder.bind(dataSet[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class AgentViewHolder(itemView: View, private val onAgentNameListener: OnAgentNameListener)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(owner: Agent) {
            itemView.agent_name.text = owner.name
            itemView.agent_code.text = "Agent Code: ${owner.code}"
        }

        override fun onClick(p0: View?) {
            onAgentNameListener.onAgentNameClicked(adapterPosition)
        }
    }

    interface OnAgentNameListener {
        fun onAgentNameClicked(position:Int)
    }
}