package com.nikitak.multinotebook

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.nikitak.multinotebook.models.Note
import com.nikitak.multinotebook.viewmodels.NoteViewModel

class AddNoteActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_note)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.add_note_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)


        val titleEditText: EditText = findViewById(R.id.titleEditText)
        val contentEditText: EditText = findViewById(R.id.contentEditText)
        val saveButton: Button = findViewById(R.id.saveButton)

        //Editing form check
        if (intent.hasExtra("note_id") && intent.hasExtra("note_title") && intent.hasExtra("note_content")) {
            noteId = intent.getIntExtra("note_id", -1)
            titleEditText.setText(intent.getStringExtra("note_title"))
            contentEditText.setText(intent.getStringExtra("note_content"))
        }

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val content = contentEditText.text.toString()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                if (noteId != -1) {
                    // Если это редактирование существующей заметки
                    val updatedNote = Note(id = noteId, title = title, content = content)
                    noteViewModel.update(updatedNote)
                } else {
                    // Если это новая заметка
                    val newNote = Note(title = title, content = content)
                    noteViewModel.insert(newNote)
                }
                finish()
            } else {
                Toast.makeText(this, "Please enter title and content", Toast.LENGTH_SHORT).show()
            }
        }
    }
}