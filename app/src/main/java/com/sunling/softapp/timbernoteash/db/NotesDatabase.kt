package com.sunling.softapp.timbernoteash.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sunling.softapp.timbernoteash.dao.NoteDao
import com.sunling.softapp.timbernoteash.entity.Note
import com.sunling.softapp.timbernoteash.tools.AppConstString.DATABASE_VERSION


@Database(
    entities = [Note::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}