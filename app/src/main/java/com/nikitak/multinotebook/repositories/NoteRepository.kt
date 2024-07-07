package com.nikitak.multinotebook.repositories

import androidx.lifecycle.LiveData
import com.nikitak.multinotebook.dao.NoteDao
import com.nikitak.multinotebook.models.Note

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
}