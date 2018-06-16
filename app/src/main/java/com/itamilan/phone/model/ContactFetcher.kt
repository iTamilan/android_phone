package com.itamilan.phone.model

import android.content.Context
import android.provider.ContactsContract
import android.support.v4.content.CursorLoader
import java.util.HashMap

class ContactFetcher(context: Context) {
    private val context: Context

    init {
        this.context = context
    }

    // Public functions

    fun fetchAll(): ArrayList<Contact> {
        val projectionFields = arrayOf(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME)
        var listContacts = ArrayList<Contact>()
        val cursorLoader = CursorLoader(context,
                ContactsContract.Contacts.CONTENT_URI,
                projectionFields, // the selection args (none)
                null // the sort order (default)
                , null, null
        )// the columns to retrieve
        // the selection criteria (none)

        val c = cursorLoader.loadInBackground()

        val contactsMap = HashMap<String, Contact>(c!!.count)

        if (c.moveToFirst()) {

            val idIndex = c.getColumnIndex(ContactsContract.Contacts._ID)
            val nameIndex = c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)

            do {
                val contactId = c.getString(idIndex)
                val contactDisplayName = c.getString(nameIndex)
                val contact = Contact(contactId, contactDisplayName)
                contactsMap[contactId] = contact
                listContacts.add(contact)
            } while (c.moveToNext())
        }

        c.close()

        matchContactNumbers(contactsMap)
        matchContactEmails(contactsMap)

        return listContacts
    }

    fun matchContactNumbers(contactsMap: Map<String, Contact>) {
        // Get numbers
        val numberProjection = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.CONTACT_ID)

        val phone = CursorLoader(context,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                numberProjection, null, null, null).loadInBackground()

        if (phone!!.moveToFirst()) {
            val contactNumberColumnIndex = phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val contactTypeColumnIndex = phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE)
            val contactIdColumnIndex = phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)

            while (!phone.isAfterLast) {
                val number = phone.getString(contactNumberColumnIndex)
                val contactId = phone.getString(contactIdColumnIndex)
                val contact = contactsMap[contactId] ?: continue
                val type = phone.getInt(contactTypeColumnIndex)
                val customLabel = "Custom"
                val phoneType = ContactsContract.CommonDataKinds.Phone.getTypeLabel(context.resources, type, customLabel)
                contact.numbers.add(number)
                phone.moveToNext()
            }
        }

        phone.close()
    }

    fun matchContactEmails(contactsMap: Map<String, Contact>) {
        // Get email
        val emailProjection = arrayOf(ContactsContract.CommonDataKinds.Email.DATA, ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.CONTACT_ID)

        val email = CursorLoader(context,
                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                emailProjection, null, null, null).loadInBackground()

        if (email!!.moveToFirst()) {
            val contactEmailColumnIndex = email.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)
            val contactTypeColumnIndex = email.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE)
            val contactIdColumnsIndex = email.getColumnIndex(ContactsContract.CommonDataKinds.Email.CONTACT_ID)

            while (!email.isAfterLast) {
                val address = email.getString(contactEmailColumnIndex)
                val contactId = email.getString(contactIdColumnsIndex)
                val type = email.getInt(contactTypeColumnIndex)
                val customLabel = "Custom"
                val contact = contactsMap[contactId] ?: continue
                val emailType = ContactsContract.CommonDataKinds.Email.getTypeLabel(context.resources, type, customLabel)
                contact.emails.add(address)
                email.moveToNext()
            }
        }

        email.close()
    }
}