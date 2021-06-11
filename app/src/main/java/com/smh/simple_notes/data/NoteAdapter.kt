package com.smh.simple_notes.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smh.simple_notes.databinding.CardLayoutBinding
import com.smh.simple_notes.entities.Note

class NoteAdapter(private val noteList: List<Note>, var clickListener: OnClickListener) :
    RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    inner class NoteHolder(val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteHolder {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return NoteHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        with(holder) {
            binding.apply {
                cardTitle.text = noteList[position].title
                cardText.text = noteList[position].text
            }
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}

interface OnClickListener {
    fun onItemClick(position: Int)
}
