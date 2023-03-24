package id.ac.unpas.pendataanprsensiguru.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.pendataanprsensiguru.model.Dataguru

@Database(entities = [Dataguru::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dataguruDao(): DataGuruDao
}