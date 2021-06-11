package com.smh.simple_notes.entities

import java.io.Serializable


data class Note(
    val objectId: String = "",
    var updatedAt: String = "",
    var title: String = "",
    var text: String = ""
) : Serializable

