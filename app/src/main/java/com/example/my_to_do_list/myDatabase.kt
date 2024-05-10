package com.example.my_to_do_list

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Entity::class],version=1)
abstract class myDatabase : RoomDatabase() {
    abstract fun dao():DAO
}