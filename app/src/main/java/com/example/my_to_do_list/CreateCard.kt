package com.example.my_to_do_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.my_to_do_list.databinding.ActivityCreateCardBinding // Import the generated binding class
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateCard : AppCompatActivity() {
    private lateinit var  db: NotesDatabaseHelper
    private lateinit var binding: ActivityCreateCardBinding // Declare the binding variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateCardBinding.inflate(layoutInflater) // Initialize the binding variable
        setContentView(binding.root) // Set the content view to the root of the binding class

        db = NotesDatabaseHelper(this)

        binding.saveButton.setOnClickListener { // Use binding to reference the save button
            val title = binding.createTitle.text.toString().trim() // Use binding to reference the title EditText
            val priority = binding.createPriority.text.toString().trim() // Use binding to reference the priority EditText

            if (title.isNotEmpty() && priority.isNotEmpty()) {
                DataObject.setData(title, priority)
                GlobalScope.launch {
                    val note = Note(0,title,priority)
                    db.insertNote(note)
                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                // Optionally, show a toast message if the fields are empty
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
