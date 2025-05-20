package com.empresa.mmedidor.data
import androidx.room.Database
import androidx.room.RoomDatabase



@Database(entities = [Medicion::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun medicionDao(): MedicionDao
}
