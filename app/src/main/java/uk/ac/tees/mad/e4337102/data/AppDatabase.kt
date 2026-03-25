package uk.ac.tees.mad.e4337102.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [HabitEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
}
