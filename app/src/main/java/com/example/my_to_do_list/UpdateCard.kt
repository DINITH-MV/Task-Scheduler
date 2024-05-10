package com.example.my_to_do_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.my_to_do_list.databinding.ActivityUpdateCardBinding // Import the generated binding class
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateCard : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateCardBinding // Declare the binding variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateCardBinding.inflate(layoutInflater) // Initialize the binding variable
        setContentView(binding.root) // Set the content view to the root of the binding class



        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val title = DataObject.getData(pos).title
            val priority = DataObject.getData(pos).priority
            binding.createTitle.setText(title) // Use binding to reference the title EditText
            binding.createPriority.setText(priority) // Use binding to reference the priority EditText

            binding.deleteButton.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {

                }
                myIntent()
            }

            binding.updateButton.setOnClickListener {
                DataObject.updateData(
                    pos,
                    binding.createTitle.text.toString(),
                    binding.createPriority.text.toString()
                )
                GlobalScope.launch {

                }
                myIntent()
            }
        }
    }

    private fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
