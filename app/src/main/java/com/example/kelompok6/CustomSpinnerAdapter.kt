package com.example.kelompok6

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomSpinnerAdapter(context: Context, resource: Int, items: List<String?>) :
    ArrayAdapter<String>(context, resource, items.filterNotNull()) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false)

        val textView = rowView.findViewById<TextView>(android.R.id.text1)
        textView.text = getItem(position)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 23F) // Set the text size here

        return rowView
    }
}
