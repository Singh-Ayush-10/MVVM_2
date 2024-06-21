package com.mastercodint.mvvm_2

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes:LiveData<List<Note>> = noteDao.getNote()

    suspend fun addNote(note: Note){
        return noteDao.insert(note)
    }
    suspend fun deleteNote(note: Note){
        return noteDao.delete(note)
    }
}