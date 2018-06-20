package com.itamilan.phone.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.itamilan.phone.R
import com.itamilan.phone.model.Contact
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView.ViewHolder
import com.itamilan.phone.R.id.list


class ContactsRecyclerViewAdapter(context: Context): RecyclerView.Adapter<ContactsRecyclerViewAdapter.ContactsViewHolder>() {

    private var layoutInflater: LayoutInflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    var contacts =  ArrayList<Contact>()

    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = layoutInflater.inflate(R.layout.item_contacts_view, parent, false)

        return ContactsViewHolder(view)
    }

    override fun getItemCount(): Int {
        println("Contacts size: ${contacts.size}")
        return contacts.size
    }


    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {

        if (contacts.size > position) {
            val name  = contacts[position].name
            println("contact name: $name at position: $position")

            val phoneNumbers = contacts[position].numbers
            if (phoneNumbers.size > 0) {
                val phonenumber = phoneNumbers.first()
                holder.phoneTextView.text = phonenumber
                print("Phone number: $phonenumber at position: $position")
                holder.itemView.setOnClickListener {
                    onClick(position)
                }
            } else {
                holder.phoneTextView.text = ""
            }

            holder.nameTextView.text = name

        } else {
            holder.nameTextView.text = ""
            holder.phoneTextView.text = ""
            println("Contacts size: ${contacts.size} at position: $position")
        }
    }

    public fun updateData(contacts: ArrayList<Contact>) {
        this.contacts = contacts
        this.notifyDataSetChanged()
    }

    @SuppressLint("MissingPermission")
    private fun onClick(position: Int) {
        try {
            if(position < contacts.size) {
                val phone_numbers = contacts[position].numbers
                if(phone_numbers.size > 0) {
                    val phone_number = phone_numbers.first()
                    val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phone_number"))
                    layoutInflater.context.startActivity(intent)
                }
            }
        } catch (e: Exception) {
            print(e)
            //TODO smth
        }

    }

    class ContactsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView
        val phoneTextView: TextView
        init {
            nameTextView = itemView!!.findViewById(R.id.nameTextView)
            phoneTextView = itemView!!.findViewById(R.id.phoneTextView)
        }
    }
}
