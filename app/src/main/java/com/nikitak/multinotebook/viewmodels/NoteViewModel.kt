package com.nikitak.multinotebook.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.nikitak.multinotebook.NoteDatabase
import com.nikitak.multinotebook.models.Note
import com.nikitak.multinotebook.repositories.NoteRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        allNotes = repository.allNotes
    }

    suspend fun insert(note: Note) = repository.insert(note)

    suspend fun delete(note: Note) = repository.delete(note)
}