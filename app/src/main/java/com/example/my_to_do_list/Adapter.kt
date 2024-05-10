package com.example.my_to_do_list

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my_to_do_list.databinding.ViewBinding // Import the generated binding class
import com.example.todo.CardInfo

class Adapter(var data: List<Note>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            when (data[position].priority.lowercase()) {
                "high" -> mylayout.setBackgroundColor(Color.parseColor("#F05454"))
                "medium" -> mylayout.setBackgroundColor(Color.parseColor("#EDC988"))
                else -> mylayout.setBackgroundColor(Color.parseColor("#00917C"))
            }

            title.text = data[position].title
            priority.text = data[position].priority
            root.setOnClickListener {
                val intent = Intent(root.context, UpdateCard::class.java)
                intent.putExtra("id", position)
                root.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
