package com.itamilan.phone

import android.opengl.Visibility
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.LinearLayout
import com.itamilan.phone.view.ContactsView
import kotlinx.android.synthetic.main.activity_tab.*

class TabActivity : AppCompatActivity() {

    private var contactsView: ContactsView? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_favourites -> {
                setUpTootBarTitle(R.string.title_favourites)
                contactsView?.visibility = View.INVISIBLE
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_recents -> {
                setUpTootBarTitle(R.string.title_recents)
                contactsView?.visibility = View.INVISIBLE
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_contacts -> {
                contactsView?.visibility = View.VISIBLE
                setUpTootBarTitle(R.string.title_contacts)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_keypad -> {
                contactsView?.visibility = View.INVISIBLE
                setUpTootBarTitle(R.string.title_keypad)
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

        contactsView = ContactsView(this)
        container.addView(contactsView)
        contactsView?.refreshContacts()

        navigation.selectedItemId = R.id.navigation_contacts
        setUpTootBarTitle(R.string.title_recents)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun setUpTootBarTitle(id: Int){
        supportActionBar!!.setTitle(id)
    }
}
