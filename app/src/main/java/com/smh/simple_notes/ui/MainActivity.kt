package com.smh.simple_notes.ui


import android.content.Intent
import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager

import com.smh.simple_notes.entities.Note
import com.smh.simple_notes.data.NoteAdapter
import com.smh.simple_notes.data.OnClickListener
import com.smh.simple_notes.databinding.ActivityMainBinding
import com.smh.simple_notes.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.getNotes()
        noteViewModel.getAllNotesLiveData.observe(this, { notes ->
            configureList(notes)
        })

        binding.fab.setOnClickListener{
            val intent = Intent(this,NoteActivity::class.java)
            intent.putExtra("key", "new")
            startActivity(intent)
        }
    }

    private fun configureList(notes: List<Note>){
        binding.apply {
            adapter = NoteAdapter(notes, this@MainActivity)
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this,NoteActivity::class.java)
        intent.putExtra("note", noteViewModel.editNote()[position])
        intent.putExtra("key", "edit")
        startActivity(intent)
    }
}