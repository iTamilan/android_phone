package com.itamilan.phone.model


public class Contact(id: String, name: String) {
    public val id: String
    public val name: String
    public var emails = ArrayList<String>()
    public var numbers = ArrayList<String>()

    init {
        this.id = id
        this.name = name
    }
}