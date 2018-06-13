package com.itamilan.phone.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.itamilan.phone.R
import com.itamilan.phone.adapter.ContactsRecyclerViewAdapter
import kotlinx.android.synthetic.main.view_list.view.*

class ContactsView(context: Context) : LinearLayout(context) {

    private val layoutInflator: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var names: ArrayList<String> = ArrayList()
    private var contactsAdapter: ContactsRecyclerViewAdapter? = null

    init {
        loadNames()
        setUpView()
    }

    private fun setUpView() {
        val contactsView = layoutInflator.inflate(R.layout.view_list, this)
        val recyclerView = contactsView.list
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        contactsAdapter = ContactsRecyclerViewAdapter(context, names)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = contactsAdapter
    }

    private fun loadNames() {
        names.add("Tamil")
        names.add("Sarath")
        names.add("Kapil")
    }
}
