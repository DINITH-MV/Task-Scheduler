package com.example.my_to_do_list

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entity::class], version = 1)
abstract class myDatabase : RoomDatabase() {
    abstract fun dao(): DAO

    companion object {
        @Volatile
        private var INSTANCE: myDatabase? = null

        fun getInstance(context: Context): myDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        myDatabase::class.java,
                        "To_Do"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
