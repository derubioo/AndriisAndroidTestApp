package com.example.andriispasswordmanager

import android.arch.persistence.room.Database

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [AccountRecord::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountRecordDao(): AccountRecordDao

    companion object {
        private const val DatabaseName = "PasswordsDatabase"
        private var instance: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DatabaseName).build()
                this.instance = instance
                instance
            }
        }

        fun destroyDataBase(){
            instance = null
        }
    }
}