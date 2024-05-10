package com.example.my_to_do_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.my_to_do_list.databinding.ActivityCreateCardBinding // Import the generated binding class
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateCard : AppCompatActivity() {
    private lateinit var binding: ActivityCreateCardBinding // Declare the binding variable
    private lateinit var database: myDatabase // Declare the Room database variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateCardBinding.inflate(layoutInflater) // Initialize the binding variable
        setContentView(binding.root) // Set the content view to the root of the binding class

        // Get an instance of the Room database
        database = myDatabase.getInstance(applicationContext)

        binding.saveButton.setOnClickListener { // Use binding to reference the save button
            val title = binding.createTitle.text.toString().trim() // Use binding to reference the title EditText
            val priority = binding.createPriority.text.toString().trim() // Use binding to reference the priority EditText

            if (title.isNotEmpty() && priority.isNotEmpty()) {
                // Define the data to be inserted into the database
                val entity = Entity(0, title, priority)

                // Use a coroutine to perform the database operation asynchronously
                GlobalScope.launch {
                    if (::database.isInitialized) { // Check if database is initialized before using it
                        database.dao().insertTask(entity)
                    }
                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                // Show a toast message if the fields are empty
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
