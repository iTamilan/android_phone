package com.itamilan.phone.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.itamilan.phone.R

class PlaceholderView(context: Context): LinearLayout(context) {
    private val layoutInflator: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    init {
        setUpView()
    }

    private fun setUpView() {
        layoutInflator.inflate(R.layout.view_placeholder,this)
    }

}