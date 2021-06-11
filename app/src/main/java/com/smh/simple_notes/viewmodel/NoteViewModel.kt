package com.smh.simple_notes.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.smh.simple_notes.entities.Note
import com.smh.simple_notes.data.NoteRepository
import com.smh.simple_notes.retrofit.Api
import com.smh.simple_notes.retrofit.UpdateResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoteViewModel() : ViewModel() {

    val getAllNotesLiveData = MutableLiveData<List<Note>>()
    private val repository = NoteRepository(Api.getInstance())
    private var notes: List<Note> = listOf()

    fun getNotes() {
        viewModelScope.launch {
            repository.api.getNotes()
                .enqueue(object : Callback<com.smh.simple_notes.retrofit.Response> {
                    override fun onResponse(
                        call: Call<com.smh.simple_notes.retrofit.Response>,
                        response: Response<com.smh.simple_notes.retrofit.Response>
                    ) {
                        if (response.isSuccessful) {
                            getAllNotesLiveData.value = response.body()?.results
                            notes = response.body()?.results!!
                        }
                    }

                    override fun onFailure(
                        call: Call<com.smh.simple_notes.retrofit.Response>,
                        t: Throwable
                    ) {
                        Log.i("MYTAG", t.message.toString())
                    }
                })
        }
    }

    fun createNote(note: Note) {
        viewModelScope.launch {
            repository.createNote(note)
                .enqueue(object : Callback<com.smh.simple_notes.retrofit.AddResponse> {
                    override fun onResponse(
                        call: Call<com.smh.simple_notes.retrofit.AddResponse>,
                        addResponse: Response<com.smh.simple_notes.retrofit.AddResponse>
                    ) {
                        if (addResponse.isSuccessful) {
                            Log.i("MyTag", addResponse.body().toString())
                        }
                    }

                    override fun onFailure(
                        call: Call<com.smh.simple_notes.retrofit.AddResponse>,
                        t: Throwable
                    ) {
                        Log.i("MyTag", t.message.toString())
                    }
                })
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note)
                .enqueue(object : Callback<UpdateResponse> {
                    override fun onResponse(
                        call: Call<UpdateResponse>,
                        updateResponce: Response<UpdateResponse>
                    ) {
                        if (updateResponce.isSuccessful) {
                            Log.i("MyTag", updateResponce.body().toString())
                        }
                    }

                    override fun onFailure(
                        call: Call<UpdateResponse>,
                        t: Throwable
                    ) {
                        Log.i("MyTag", t.message.toString())
                    }
                })
        }
    }

    fun editNote(): List<Note> {
        return notes
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }
}