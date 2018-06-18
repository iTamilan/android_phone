package com.itamilan.phone.model

class ContactFilter(contacts: ArrayList<Contact>) {

    private val contacts: ArrayList<Contact>
    private var filteredContacts: ArrayList<Contact>? = null
    private var searchString: String? = null

    init {

        this.contacts = contacts
    }

    fun fileterContacts(searchString: String?): ArrayList<Contact> {

        val text: String =  searchString?: ""

        if (filteredContacts != null && text == this.searchString) {

            return filteredContacts!!
        }

        var filteringContacts = ArrayList<Contact>()

        for (contact in contacts) {
            if(contact.numbers.size < 1){
                continue
            }
            if(contact.name.contains(other = text,ignoreCase = true)) {
                filteringContacts.add(contact)
            } else {
                for(phone in contact.numbers) {
                    if(phone.contains(other = text,ignoreCase = true)) {
                        filteringContacts.add(contact)
                        break
                    }
                }
            }
        }
        this.searchString = text
        if(text.length == 0) {
            filteringContacts = ArrayList<Contact>()
            filteringContacts.addAll(contacts)
        }
        filteredContacts = filteringContacts
        return filteringContacts
    }
}