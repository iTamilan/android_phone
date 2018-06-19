package com.itamilan.phone

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import com.itamilan.phone.view.ContactsView
import com.itamilan.phone.view.PlaceholderView
import kotlinx.android.synthetic.main.activity_tab.*

class TabActivity : AppCompatActivity() {

    var contactsView: ContactsView? = null
    var placeholderView: PlaceholderView? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        hideKeyboard()
        when (item.itemId) {

            R.id.navigation_favourites -> {
                setUpTootBarTitle(R.string.title_favourites)
                enableContactView(false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_recents -> {
                setUpTootBarTitle(R.string.title_recents)
                enableContactView(false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_contacts -> {
                enableContactView(true)
                setUpTootBarTitle(R.string.title_contacts)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_keypad -> {
                enableContactView(false)
                setUpTootBarTitle(R.string.title_keypad)
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }

    private fun enableContactView(enable: Boolean) {
        if(enable) {
            contactsView?.visibility = View.VISIBLE
            searchEditText.visibility = View.VISIBLE
            placeholderView?.visibility = View.GONE
        } else {
            contactsView?.visibility = View.GONE
            searchEditText.visibility = View.GONE
            placeholderView?.visibility = View.VISIBLE
        }
}

    private fun hideKeyboard() {
        val imm= getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(searchEditText.windowToken, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

        contactsView = ContactsView(this)
        container.addView(contactsView)

        placeholderView = PlaceholderView(this)
        container.addView(placeholderView)

        contactsView?.refreshContacts("")
        enableContactView(true)

        searchEditText.addTextChangedListener(MyTextWatcher(contactsView))

        navigation.selectedItemId = R.id.navigation_contacts
        setUpTootBarTitle(R.string.title_recents)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun setUpTootBarTitle(id: Int){
        supportActionBar!!.setTitle(id)
    }

    class MyTextWatcher(contactsView: ContactsView?) : TextWatcher {

        private var contactsView: ContactsView?

        init {
            this.contactsView = contactsView
        }

        override fun afterTextChanged(s: Editable?) {
            contactsView?.refreshContacts(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

    }
}
