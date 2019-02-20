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
        var INSTANCE: AppDatabase? = null

        fun getDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DatabaseName).build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}