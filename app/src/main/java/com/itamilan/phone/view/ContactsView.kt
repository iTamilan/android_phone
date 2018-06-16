package com.itamilan.phone.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.itamilan.phone.R
import com.itamilan.phone.adapter.ContactsRecyclerViewAdapter
import com.itamilan.phone.model.Contact
import com.itamilan.phone.model.ContactFetcher
import kotlinx.android.synthetic.main.view_list.view.*

class ContactsView(context: Context) : LinearLayout(context) {

    private val layoutInflator: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var contacts = ArrayList<Contact>()
    private var contactsAdapter: ContactsRecyclerViewAdapter? = null

    init {
        setUpView()
    }

    private fun setUpView() {
        val contactsView = layoutInflator.inflate(R.layout.view_list, this)
        val recyclerView = contactsView.list
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        contactsAdapter = ContactsRecyclerViewAdapter(context)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = contactsAdapter
    }

    fun refreshContacts() {
        contacts = ContactFetcher(context).fetchAll()
        contactsAdapter?.updateData(contacts)
    }
}
