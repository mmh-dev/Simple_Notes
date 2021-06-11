package com.smh.simple_notes.data

import com.smh.simple_notes.entities.Note
import com.smh.simple_notes.retrofit.Response
import com.smh.simple_notes.retrofit.Api
import com.smh.simple_notes.retrofit.AddResponse
import com.smh.simple_notes.retrofit.UpdateResponse
import retrofit2.Call

class NoteRepository(val api: Api) : RepoImpl {
    override suspend fun getNotes(): Call<Response> {
        return api.getNotes()
    }

    override suspend fun createNote(note: Note): Call<AddResponse> {
        return api.createNote(note)
    }

    override suspend fun updateNote(note: Note): Call<UpdateResponse> {
        return api.updateNote(note.objectId, note)
    }

    override suspend fun deleteNote(note: Note) {
        api.deleteNote(note.objectId)
    }
}