package com.example.tutorial2

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorial2.models.LifeAssured
import com.example.tutorial2.models.PolicyOwner
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.form.view.*
import kotlinx.android.synthetic.main.scan_doc.*

class ScanDocFragment : Fragment(), LifeAssuredRecyclerAdapter.OnLifeAssuredListener{

    private lateinit var lifeAssuredRecyclerAdapter: LifeAssuredRecyclerAdapter
    private var assureds:ArrayList<LifeAssured>? = ArrayList()
    private var policyOwner:PolicyOwner? = null
    private lateinit var snackBar:Snackbar
    private var submitButtonClicked = false
    private lateinit var rootView:View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.scan_doc, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        policyOwner = activity?.intent?.getParcelableExtra("selected_policy_owner")
        assureds = policyOwner?.getAssuredList()
        initRecyclerView()
        rootView = view
        setUpSubmitButton()
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun selectingSnackBarView() {
        snackBar = Snackbar.make(rootView, "Submit Selected", Snackbar.LENGTH_INDEFINITE)
        val textView = snackBar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        snackBar.view.setBackgroundResource(R.drawable.snackbar_selecting_state)
        snackBar.view.minimumHeight = 150
        snackBar.duration = Snackbar.LENGTH_INDEFINITE

        textView.textSize = 15F
        textView.gravity = Gravity.CENTER_HORIZONTAL
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        textView.setTextColor(Color.parseColor("#757678"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun notifyFormSelected() {
        selectedSnackBarView()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun successfulSnackBarView() {
        val textView = snackBar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.text = "Documents Submitted Successfully"
        snackBar.view.setBackgroundResource(R.drawable.snackbar_successful_state)
        snackBar.setAction("X") {snackBar.duration = Snackbar.LENGTH_LONG}
        textView.gravity = Gravity.CENTER_HORIZONTAL
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        enterDeselectMode()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("ResourceAsColor")
    private fun selectedSnackBarView() {
        val textView = snackBar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.text = "Submit Selected"
        textView.setTextColor(Color.parseColor("#ffffff"))
        snackBar.view.setBackgroundResource(R.drawable.snackbar_selected_state)
        snackBar.view.setOnClickListener {successfulSnackBarView()}
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun enterSelectMode() {
        if (assureds != null) {
            for (x in 0 until assureds!!.size) {
                val view = scan_doc_recycler_view.layoutManager?.findViewByPosition(x)
                val assuredList = view?.findViewById<ConstraintLayout>(R.id.assured_list)
                val assured = assureds!![x]

                if (assuredList != null) {
                    for (y in 0 until assured.getFormList()?.size!!) {
                        val childCardView = assuredList[y] as CardView

                        if (!assured.getForm(y)?.action.equals("Camera")) {
                            childCardView.isClickable = false
                            childCardView.setCardBackgroundColor(Color.parseColor("#EBEBEB"))
                        }
                        else{
                            childCardView.isClickable = true
                        }
                    }
                }
            }
        }
        selectingSnackBarView()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun enterDeselectMode() {
        if (assureds != null) {
            for (x in 0 until assureds!!.size) {
                val view = scan_doc_recycler_view.layoutManager?.findViewByPosition(x)
                val assuredList = view?.findViewById<ConstraintLayout>(R.id.assured_list)
                val assured = assureds!![x]

                if (assuredList != null) {
                    for (y in 0 until assured.getFormList()?.size!!) {
                        val childCardView = assuredList[y] as CardView
                        if (!assured.getForm(y)?.action.equals("Camera")) {
                            childCardView.setCardBackgroundColor(Color.parseColor("#ffffff"))
                        }
                        else{
                            childCardView.setBackgroundResource(R.drawable.card_view_unselected_background)
                            childCardView.action_button.setImageResource(R.drawable.upload_icon)
                            assured.getForm(y)?.action = "Uploaded"
                        }
                        childCardView.isClickable = false
                    }
                }
            }
        }
        submit_button.text = "Submit"
        info_message_container.visibility = View.GONE
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun setUpSubmitButton() {
        submit_button.setOnClickListener {
            when (submit_button.text) {
                "Cancel" -> {
                    submit_button.text = "Submit"
                    submitButtonClicked = false
                    lifeAssuredRecyclerAdapter.notifySubmitButtonClicked(false)
                    enterDeselectMode()
                }

                "Submit" -> {
                    submit_button.text = "Cancel"
                    enterSelectMode()
                    submitButtonClicked = true
                    lifeAssuredRecyclerAdapter.notifySubmitButtonClicked(true)
                    snackBar.show()
                }
            }
        }
    }

    private fun initRecyclerView() {
        scan_doc_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            lifeAssuredRecyclerAdapter = LifeAssuredRecyclerAdapter(assureds, this@ScanDocFragment)
            adapter = lifeAssuredRecyclerAdapter
        }
    }

    override fun onLifeAssuredClick(position: Int) {

    }
}