package com.smh.simple_notes.retrofit

import com.smh.simple_notes.entities.Note

data class Response(
    val results: List<Note>
)