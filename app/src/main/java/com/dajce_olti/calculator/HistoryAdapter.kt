package com.dajce_olti.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class HistoryAdapter(private val historyStack: List<String>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>(){

    class HistoryViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_item_layout, parent, false) as TextView
        return HistoryViewHolder(textView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.textView.text = historyStack[position]
    }

    override fun getItemCount() = historyStack.size

}