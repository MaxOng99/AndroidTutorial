package com.example.tutorial2
import android.annotation.SuppressLint
import android.os.Build.VERSION_CODES.LOLLIPOP
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorial2.models.LifeAssured
import kotlinx.android.synthetic.main.form.view.*
import kotlinx.android.synthetic.main.life_assured_recycler_view_item.view.*
import kotlinx.android.synthetic.main.scan_doc.view.*

class LifeAssuredRecyclerAdapter(private val dataSet: ArrayList<LifeAssured>?,
                                 private val onLifeAssuredListener: OnLifeAssuredListener):
    RecyclerView.Adapter<LifeAssuredRecyclerAdapter.LifeAssuredViewHolder>() {

    private lateinit var inflater: LayoutInflater
    var submitButtonClicked = false

    fun notifySubmitButtonClicked(state:Boolean) {
        submitButtonClicked = state
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LifeAssuredViewHolder {
        inflater = LayoutInflater.from(parent.context)
        return LifeAssuredViewHolder(inflater
            .inflate(R.layout.life_assured_recycler_view_item, parent, false), inflater, onLifeAssuredListener, dataSet)
    }

    @RequiresApi(LOLLIPOP)
    override fun onBindViewHolder(holder: LifeAssuredViewHolder, position: Int) {
        when (holder) {
            is LifeAssuredViewHolder -> {
                dataSet?.get(position)?.let { holder.bind(it) }
            }
        }
    }

    override fun getItemCount(): Int {
        return when (dataSet) {
            null -> 0
            else -> dataSet.size
        }
    }

    inner class LifeAssuredViewHolder(itemView: View,
                                private val inflater: LayoutInflater,
                                private val onLifeAssuredListener: OnLifeAssuredListener,
                                private val dataSet: ArrayList<LifeAssured>?)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        private val toggleDetailsBtn = itemView.collapse_button
        private val assuredListContainer = itemView.assured_list

        @SuppressLint("SetTextI18n")
        @RequiresApi(LOLLIPOP)
        fun bind(assured: LifeAssured) {
            val id = dataSet?.indexOf(assured)
            itemView.life_assured_id.text = "Life Assured ${id?.plus(1)}"
            itemView.assured_name.text = assured.name

            toggleDetailsBtn.setOnCheckedChangeListener { _, isChecked ->
                toggleDetails(isChecked)
            }

            val list = assured.getFormList()
            val parentLayout = assuredListContainer
            var currentTopConstraintView:View = itemView.header
            val constraintSet = ConstraintSet()

            if (list != null) {
                for (form in list) {

                    val formView = inflater.inflate(R.layout.form, null) as CardView
                    formView.form_title.text = form.title
                    formView.date.text = form.date
                    formView.description.text = form.description
                    formView.file_size.text = form.fileSize
                    formView.page.text = form.page

                    when (form.action) {
                        "Camera" -> formView.action_button.setImageResource(R.drawable.camera_icon)
                        "Uploaded" -> formView.action_button.setImageResource(R.drawable.upload_icon)
                        "Important" -> formView.action_button.setImageResource(R.drawable.important_mark_icon)
                        "Success" -> formView.action_button.setImageResource(R.drawable.success_icon)
                    }

                    formView.action_button.setOnClickListener {
                        val parent = itemView.parent.parent as ConstraintLayout
                        if (form.action.equals("Camera")) {
                            parent.info_message_container.visibility = View.VISIBLE
                        }
                    }

                    formView.setOnClickListener {

                        if (submitButtonClicked) {
                            formView.setBackgroundResource(R.drawable.card_view_selected_background)
                            val scanDocFragment = onLifeAssuredListener as ScanDocFragment
                            scanDocFragment.notifyFormSelected()
                        }
                    }

                    formView.id = View.generateViewId()
                    parentLayout.addView(formView, list.indexOf(form))
                    constraintSet.clone(parentLayout)

                    constraintSet.connect(formView.id, ConstraintSet.TOP, currentTopConstraintView.id, ConstraintSet.BOTTOM,24)
                    constraintSet.connect(formView.id, ConstraintSet.START, parentLayout.id, ConstraintSet.START, 24)
                    constraintSet.connect(formView.id, ConstraintSet.END, parentLayout.id, ConstraintSet.END,24)

                    constraintSet.applyTo(parentLayout)
                    currentTopConstraintView = formView

                    formView.more_description.visibility = View.VISIBLE
                    formView.more_description.text = "\n".plus("more >")

                    formView.more_description.setOnClickListener {

                        if (formView.more_description.text.contains("less <")) {
                            formView.description.maxLines = 2
                            formView.more_description.text = "\n".plus("more >")
                        }
                        else {
                            formView.description.maxLines = Int.MAX_VALUE
                            formView.more_description.text = "less <"
                        }
                    }
                }

                val dummyFormView = inflater.inflate(R.layout.dummy_view, null)
                dummyFormView.id = View.generateViewId()
                dummyFormView.layoutParams = ViewGroup.LayoutParams(0,1)
                parentLayout.addView(dummyFormView, list.size)
                constraintSet.clone(parentLayout)

                constraintSet.connect(dummyFormView.id, ConstraintSet.TOP, currentTopConstraintView.id, ConstraintSet.BOTTOM,24)
                constraintSet.connect(dummyFormView.id, ConstraintSet.START, parentLayout.id, ConstraintSet.START, 24)
                constraintSet.connect(dummyFormView.id, ConstraintSet.END, parentLayout.id, ConstraintSet.END,24)
                constraintSet.connect(currentTopConstraintView.id, ConstraintSet.BOTTOM, dummyFormView.id, ConstraintSet.TOP)
                constraintSet.applyTo(parentLayout)
            }
        }

        override fun onClick(p0: View?) {
            onLifeAssuredListener.onLifeAssuredClick(adapterPosition)
        }

        private fun toggleDetails(checked: Boolean) {
            when (checked) {
                true -> {
                    assuredListContainer.visibility = View.GONE
                }
                false -> {
                    assuredListContainer.visibility = View.VISIBLE
                }
            }
        }
    }

    interface OnLifeAssuredListener {
        fun onLifeAssuredClick(position:Int)
    }
}