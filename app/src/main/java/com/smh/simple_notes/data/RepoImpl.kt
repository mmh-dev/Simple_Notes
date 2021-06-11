package com.smh.simple_notes.data

import com.smh.simple_notes.entities.Note
import com.smh.simple_notes.retrofit.Response
import com.smh.simple_notes.retrofit.AddResponse
import com.smh.simple_notes.retrofit.UpdateResponse
import retrofit2.Call

interface RepoImpl {
    suspend fun getNotes(): Call<Response>
    suspend fun createNote(note: Note): Call<AddResponse>
    suspend fun updateNote(note: Note): Call<UpdateResponse>
    suspend fun deleteNote(note: Note)
}