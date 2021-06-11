package com.smh.simple_notes.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.smh.simple_notes.databinding.ActivityMainBinding
import com.smh.simple_notes.databinding.ActivityNoteBinding
import com.smh.simple_notes.entities.Note
import com.smh.simple_notes.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        var key = intent.getStringExtra("key")
        note = if (key == "edit"){
            intent.getSerializableExtra("note") as Note
        } else{
            Note()
        }


        binding.apply {

            noteTitle.setText(note.title)
            noteText.setText(note.text)

            backBtn.setOnClickListener {
                startActivity(Intent(this@NoteActivity, MainActivity::class.java))
            }

            saveNoteBtn.setOnClickListener {
                note.title = binding.noteTitle.text.toString()
                note.text = binding.noteText.text.toString()
                Log.i("note", note.text)
                noteViewModel.updateNote(note)
                startActivity(Intent(this@NoteActivity, MainActivity::class.java))
            }

            deleteBtn.setOnClickListener {
                noteViewModel.deleteNote(note)
                startActivity(Intent(this@NoteActivity, MainActivity::class.java))
            }

            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
            val currentDate = sdf.format(Date())
            lastEditedDate.text = currentDate
        }
    }
}