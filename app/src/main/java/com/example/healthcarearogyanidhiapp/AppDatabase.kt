package com.example.healthcarearogyanidhiapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.healthcarearogyanidhiapp.document.DocumentDao
import com.example.healthcarearogyanidhiapp.document.DocumentEntity

@Database(
    entities = [
        SchemeEntity::class,
        DocumentEntity::class
    ],
    version = 2
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun schemeDao(): SchemeDao

    abstract fun documentDao(): DocumentDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "arogya_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}