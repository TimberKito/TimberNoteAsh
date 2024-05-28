package com.sunling.softapp.timbernoteash.db

import androidx.room.Room
import com.sunling.softapp.timbernoteash.App
import com.sunling.softapp.timbernoteash.entity.Note
import com.sunling.softapp.timbernoteash.tools.AppConstString.DATABASE_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DataBaseManager {

    val notesDatabase: NotesDatabase by lazy {
        Room.databaseBuilder(
            App.appContext,
            NotesDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    fun closeDB() {
        notesDatabase.run {
            close()
        }
    }

    fun insertNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            notesDatabase.noteDao()
                .insertNote(note)
        }
    }

    fun updateNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            notesDatabase.noteDao()
                .updateNote(note)
        }
    }

    fun deleteNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            notesDatabase.noteDao()
                .deleteNote(note)
        }
    }


}