package com.sunling.softapp.timbernoteash.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "t_notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    var title: String,
    var content: String?,
    var color: Int = 0,
    var collect: Boolean = false
) : Serializable