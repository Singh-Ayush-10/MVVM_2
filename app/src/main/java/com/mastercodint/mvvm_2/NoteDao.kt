package com.mastercodint.mvvm_2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao{
    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("select * from notes_table order by id ASC")
     fun getNote():LiveData<List<Note>>
}