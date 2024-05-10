package com.example.my_to_do_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.my_to_do_list.databinding.ActivityUpdateCardBinding // Import the generated binding class
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateCard : AppCompatActivity() {
    private lateinit var  db: NotesDatabaseHelper
    private lateinit var binding: ActivityUpdateCardBinding // Declare the binding variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateCardBinding.inflate(layoutInflater) // Initialize the binding variable
        setContentView(binding.root) // Set the content view to the root of the binding class

        db = NotesDatabaseHelper(this)

        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
//            val title = DataObject.getData(pos).title
//            val priority = DataObject.getData(pos).priority

            val note = db.getNoteByID(pos)

            binding.createTitle.setText(note.title) // Use binding to reference the title EditText
            binding.createPriority.setText(note.priority) // Use binding to reference the priority EditText

            binding.deleteButton.setOnClickListener {
                    GlobalScope.launch {
                    db.deleteNote(pos)
                }
                myIntent()
            }

            binding.updateButton.setOnClickListener {
                val title = binding.createTitle.text.toString()
                val priority = binding.createPriority.text.toString()


//                DataObject.updateData(pos, title, priority)

                // Launching a coroutine to insert data asynchronously
                GlobalScope.launch {
                    val updatedNote = Note(pos, title, priority)
                    db.updateNote(updatedNote)
                }

                Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
                myIntent()
            }
        }
    }

    private fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
