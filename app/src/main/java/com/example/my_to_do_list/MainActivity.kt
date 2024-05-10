package com.example.my_to_do_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.my_to_do_list.databinding.ActivityMainBinding // Import the generated binding class
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var database: myDatabase
    private lateinit var binding: ActivityMainBinding // Declare the binding variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Initialize the binding variable
        setContentView(binding.root) // Set the content view to the root of the binding class

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        binding.add.setOnClickListener { // Use binding to reference the add button
            val intent = Intent(this, CreateCard::class.java)
            startActivity(intent)
        }

        binding.deleteAll.setOnClickListener { // Use binding to reference the deleteAll button
            DataObject.deleteAll()
            GlobalScope.launch {
                database.dao().deleteAll()
            }
            setRecycler()
        }

        setRecycler()
    }

    private fun setRecycler() {
        binding.recyclerView.adapter = Adapter(DataObject.getAllData()) // Use binding to reference the RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
