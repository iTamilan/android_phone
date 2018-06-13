package com.itamilan.phone.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.itamilan.phone.R

class ContactsRecyclerViewAdapter(context: Context, names: ArrayList<String>): RecyclerView.Adapter<ContactsRecyclerViewAdapter.ContactsViewHolder>() {

    private var layoutInflater: LayoutInflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var names: ArrayList<String> = ArrayList()

    init {
        this.names = names
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = layoutInflater.inflate(R.layout.item_contacts_view, parent, false)
        return ContactsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.textView.text = names.get(position)
    }

    public fun updateData(names: ArrayList<String>) {
        this.names = names
        this.notifyDataSetChanged()
    }

    class ContactsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView

        init {
            textView = itemView!!.findViewById(R.id.textView)
        }
    }
}
