package com.mastercodint.mvvm_2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application) {
    val allNote:LiveData<List<Note>>
    private val repository:NoteRepository
    init {
        val dao=NoteDatabase.getDatabaseInstance(application).getNoteDao()
        repository=NoteRepository(dao)
        allNote=repository.allNotes
    }

    fun deleteNote(note: Note)=viewModelScope.launch(Dispatchers.IO){
        repository.deleteNote(note)
    }
    fun addNote(note: Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.addNote(note)
    }
}