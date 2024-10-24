package dev.sanjaygangwar.tempproject.repository.offline

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.sanjaygangwar.tempproject.models.localdbclass.userData
import dev.sanjaygangwar.tempproject.repository.offline.dao.mainDao

@Database(entities = [userData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mainDao(): mainDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, appContext.applicationInfo.name)
                .fallbackToDestructiveMigration()
                .build()
    }

}