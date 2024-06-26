package com.sunling.softapp.timbernoteash.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "t_notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var content: String?,
    var color: Int,
    var collect: Boolean = false
) : Serializable {
}