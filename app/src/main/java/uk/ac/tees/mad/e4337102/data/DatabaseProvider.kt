package uk.ac.tees.mad.e4337102.data

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    @Volatile private var INSTANCE: AppDatabase? = null

    fun get(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "habit_tracker.db"
            ).build()
            INSTANCE = db
            db
        }
    }
}
