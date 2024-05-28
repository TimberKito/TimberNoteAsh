package com.sunling.softapp.timbernoteash.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sunling.softapp.timbernoteash.entity.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(vararg item: Note)

    @Update
    suspend fun updateNote(vararg item: Note)

    @Delete
    suspend fun deleteNote(vararg item: Note)

    @Query("DELETE FROM t_notes")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM t_notes ORDER BY id DESC")
    fun getAllNotes(): List<Note>

}