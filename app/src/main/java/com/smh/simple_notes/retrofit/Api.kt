package com.smh.simple_notes.retrofit

import com.smh.simple_notes.entities.Note
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Api {

    companion object {
        private var api: Api? = null
        fun getInstance(): Api {
            if (api == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Utils.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                api = retrofit.create(Api::class.java)
            }
            return api!!
        }
    }

    @Headers(
        "X-Parse-Application-Id: XzJlGSyReMF3gC1cOshwYStrfnCoSHVls46GTBmM",
        "X-Parse-REST-API-Key: fNQLr4sIbWL6HAfuz015Dpk37YIfDXcOOCiIGIY3"
    )
    @POST("classes/Note")
    fun createNote(
        @Body note: Note
    ): Call<AddResponse>

    @Headers(
        "X-Parse-Application-Id: XzJlGSyReMF3gC1cOshwYStrfnCoSHVls46GTBmM",
        "X-Parse-REST-API-Key: fNQLr4sIbWL6HAfuz015Dpk37YIfDXcOOCiIGIY3"
    )
    @GET("classes/Note")
    fun getNotes(): Call<Response>

    @Headers(
        "X-Parse-Application-Id: XzJlGSyReMF3gC1cOshwYStrfnCoSHVls46GTBmM",
        "X-Parse-REST-API-Key: fNQLr4sIbWL6HAfuz015Dpk37YIfDXcOOCiIGIY3"
    )
    @PUT("classes/Note/{id}")
    fun updateNote(
        @Path("id") id: String,
        @Body note: Note?
    ): Call<UpdateResponse>

    @Headers(
        "X-Parse-Application-Id: XzJlGSyReMF3gC1cOshwYStrfnCoSHVls46GTBmM",
        "X-Parse-REST-API-Key: fNQLr4sIbWL6HAfuz015Dpk37YIfDXcOOCiIGIY3"
    )
    @DELETE("classes/Note/{id}")
    fun deleteNote(
        @Path("id") id: String
    ): Call<Void>
}