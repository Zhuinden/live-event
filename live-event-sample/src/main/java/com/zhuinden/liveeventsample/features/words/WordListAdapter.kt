package com.zhuinden.liveeventsample.features.words

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhuinden.liveeventsample.R
import com.zhuinden.liveeventsample.utils.inflate
import java.util.*

class WordListAdapter : RecyclerView.Adapter<WordListAdapter.ViewHolder>() {
    private var list: List<String> = Collections.emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.word_list_item))

    override fun getItemCount(): Int = list.size

    fun updateWords(list: List<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
        private val text = containerView.findViewById<TextView>(R.id.text)

        fun bind(word: String) {
            text.text = word
        }
    }
}