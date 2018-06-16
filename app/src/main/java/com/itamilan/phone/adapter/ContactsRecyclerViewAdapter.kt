package com.itamilan.phone.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.itamilan.phone.R
import com.itamilan.phone.model.Contact

class ContactsRecyclerViewAdapter(context: Context): RecyclerView.Adapter<ContactsRecyclerViewAdapter.ContactsViewHolder>() {

    private var layoutInflater: LayoutInflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var contacts =  ArrayList<Contact>()

    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = layoutInflater.inflate(R.layout.item_contacts_view, parent, false)
        return ContactsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val name  = contacts[position].name
        println("contact name: $name")
        holder.textView.text = contacts[position].name
    }

    public fun updateData(contacts: ArrayList<Contact>) {
        this.contacts = contacts
        this.notifyDataSetChanged()
    }

    class ContactsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView

        init {
            textView = itemView!!.findViewById(R.id.textView)
        }
    }
}
